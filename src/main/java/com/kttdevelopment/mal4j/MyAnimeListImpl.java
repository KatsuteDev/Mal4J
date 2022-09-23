/*
 * Copyright (C) 2021-2022 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.APIStruct.Response;
import com.kttdevelopment.mal4j.anime.*;
import com.kttdevelopment.mal4j.anime.property.AnimeRankingType;
import com.kttdevelopment.mal4j.anime.property.time.Season;
import com.kttdevelopment.mal4j.forum.*;
import com.kttdevelopment.mal4j.manga.*;
import com.kttdevelopment.mal4j.manga.property.MangaRankingType;
import com.kttdevelopment.mal4j.property.ExperimentalFeature;
import com.kttdevelopment.mal4j.query.*;
import com.kttdevelopment.mal4j.user.User;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.kttdevelopment.mal4j.Json.*;
import static com.kttdevelopment.mal4j.MyAnimeListSchema_Anime.*;
import static com.kttdevelopment.mal4j.MyAnimeListSchema_Forum.*;
import static com.kttdevelopment.mal4j.MyAnimeListSchema_Manga.*;
import static com.kttdevelopment.mal4j.MyAnimeListSchema_User.*;

final class MyAnimeListImpl extends MyAnimeList {

    private transient String token     = null;
    private transient String client_id = null;
    private final boolean isTokenAuth;

    private MyAnimeListAuthenticator authenticator;

    private final MyAnimeListService service = MyAnimeListService.create();

    MyAnimeListImpl(final String token_or_client, final boolean isToken){
        Objects.requireNonNull(token_or_client, (isToken ? "OAuth token" : "Client ID") + " can not be null");
        this.isTokenAuth = isToken;
        if(isToken){
            if(!token_or_client.startsWith("Bearer "))
                throw new InvalidTokenException("OAuth token should start with 'Bearer'");
            this.token = token_or_client;
        }else
            this.client_id = token_or_client;
        Logging.addMask(token_or_client);
    }

    MyAnimeListImpl(final MyAnimeListAuthenticator authenticator){
        Objects.requireNonNull(authenticator, "Authenticator cannot be null");
        this.authenticator = authenticator;
        this.token         = authenticator.getAccessToken().getToken();
        this.isTokenAuth   = true;
    }

    @Override
    public synchronized final void refreshToken(){
        if(authenticator == null)
            throw new UnsupportedOperationException("OAuth token refresh can only be used with authorization");
        this.token = authenticator.refreshAccessToken().getToken();
    }

    @Override @Deprecated
    public synchronized final void refreshOAuthToken(){
        Logging.getLogger().warning("The use of this method is deprecated, please use `refreshToken()");
        refreshToken();
    }

// experimental features

    // features that are no longer experimental (make sure to deprecate)
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    private final List<ExperimentalFeature> nativeFeatures = Arrays.asList();

    // experimental features that are enabled
    private final List<ExperimentalFeature> enabledFeatures = new ArrayList<>();

    @SuppressWarnings("SameParameterValue")
    final void checkExperimentalFeatureEnabled(final ExperimentalFeature feature) {
        if(nativeFeatures.contains(feature) || enabledFeatures.contains(feature) || enabledFeatures.contains(ExperimentalFeature.ALL))
            return;

        throw new ExperimentalFeatureException("The feature " + feature.name() + " is an experimental feature and must be enabled using the enableExperimentalFeature method");
    }

    @Override
    public final void enableExperimentalFeature(final ExperimentalFeature feature){
        if(nativeFeatures.contains(feature))
            Logging.getLogger().warning("The feature " + feature.name() + " is no longer an experimental feature, you do not have to enable it anymore");
        else if(!enabledFeatures.contains(feature))
            enabledFeatures.add(feature);
    }

    final void clearExperimentalFeatures(){
        enabledFeatures.clear();
    }

// provider

    @Override
    public final AnimeSearchQuery getAnime(){
        return new AnimeSearchQuery() {

            @Override
            public final List<AnimePreview> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnime(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        query,
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<AnimePreview> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimePreview> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getAnime(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        query,
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    ),
                    iterator -> asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))
                );
            }
        };
    }

    @Override
    public final Anime getAnime(final long id){
        return getAnime(id, (String[]) null);
    }

    @Override
    public final Anime getAnime(final long id, final String... fields){
        return asAnime(this,
        handleResponse(
            () -> service.getAnime(
                isTokenAuth ? token : null,
                !isTokenAuth ? client_id : null,
                id,
                convertFields(Fields.anime, fields)
            )
        ));
    }

    @Override
    public final AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType){
        return getAnimeRanking(Objects.requireNonNull(rankingType, "Ranking type cannot be null").field());
    }

    @Override
    public final AnimeRankingQuery getAnimeRanking(final String rankingType){
        return new AnimeRankingQuery(Objects.requireNonNull(rankingType, "Ranking type cannot be null")) {

            @Override
            public final List<AnimeRanking> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnimeRanking(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        rankingType,
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<AnimeRanking> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnimeRanking(MyAnimeListImpl.this, iterator));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimeRanking> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getAnimeRanking(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        rankingType,
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    ),
                    iterator -> asAnimeRanking(MyAnimeListImpl.this, iterator)
                );
            }
        };
    }

    @Override
    public final AnimeSeasonQuery getAnimeSeason(final int year, final Season season){
        return new AnimeSeasonQuery(year, Objects.requireNonNull(season, "Season cannot be null")) {

            @Override
            public final List<AnimePreview> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnimeSeason(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        year,
                        season.field(),
                        sort,
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<AnimePreview> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimePreview> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getAnimeSeason(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        year,
                        season.field(),
                        sort,
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    ),
                    iterator -> asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))
                );
            }

        };
    }

    @Override
    public final AnimeSuggestionQuery getAnimeSuggestions(){
        return new AnimeSuggestionQuery() {

            @Override
            public final List<AnimePreview> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnimeSuggestions(
                        Objects.requireNonNull(token, "Client ID not supported for this endpoint, create a MyAnimeList object with either an Authenticator or Token"),
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<AnimePreview> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimePreview> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getAnimeSuggestions(
                        Objects.requireNonNull(token, "Client ID not supported for this endpoint, create MyAnimeList object with either an Authenticator or Token"),
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    ),
                    iterator -> asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))
                );
            }

        };
    }

    @Override
    public final AnimeListUpdate updateAnimeListing(final long id){
        return new AnimeListUpdate(id) {

            @Override
            public synchronized final AnimeListStatus update(){
                final JsonObject response = handleResponse(
                    () -> service.updateAnimeListing(
                        Objects.requireNonNull(token, "Client ID not supported for this endpoint, create MyAnimeList object with either an Authenticator or Token"),
                        id,
                        status,
                        rewatching,
                        score,
                        MyAnimeListSchema.asYMD(startDate),
                        MyAnimeListSchema.asYMD(finishDate),
                        watchedEpisodes,
                        priority,
                        timesRewatched,
                        rewatchValue,
                        toCommaSeparatedString(tags),
                        comments
                    )
                );
                if(response == null) return null;

                return asAnimeListStatus(MyAnimeListImpl.this, response, id);
            }

        };
    }

    @Override
    public synchronized final void deleteAnimeListing(final long id){
        try{
            handleVoidResponse(
                () -> service.deleteAnimeListing(
                    Objects.requireNonNull(token, "Client ID not supported for this endpoint, create MyAnimeList object with either an Authenticator or Token"),
                    (int) id
                )
            );
        }catch(final HttpException e){
            if(e.code() != 404)
                throw e;
        }
    }

    @Override
    public final UserAnimeListQuery getUserAnimeListing(){
        return getUserAnimeListing("@me");
    }

    @Override
    public final UserAnimeListQuery getUserAnimeListing(final String username){
        return new UserAnimeListQuery(Objects.requireNonNull(username, "Username cannot be null" )) {

            @Override
            public final List<AnimeListStatus> search(){
                final JsonObject response = handleResponse(
                    () -> service.getUserAnimeListing(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        username.equals("@me") ? "@me" : APICall.encodeUTF8(username),
                        status,
                        sort,
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<AnimeListStatus> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnimeListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimeListStatus> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getUserAnimeListing(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        username.equals("@me") ? "@me" : APICall.encodeUTF8(username),
                        status,
                        sort,
                        limit,
                        offset,
                        convertFields(Fields.anime, fields),
                        nsfw
                    ),
                    iterator -> asAnimeListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node")))
                );
            }

        };
    }

    @Override
    public final List<ForumCategory> getForumBoards(){
        final JsonObject response = handleResponse(
            () -> service.getForumBoards(
                isTokenAuth ? token : null,
                !isTokenAuth ? client_id : null
            )
        );
        if(response == null) return null;

        final List<ForumCategory> categories = new ArrayList<>();
        for(final JsonObject iterator : response.getJsonArray("categories"))
            categories.add(asForumCategory(MyAnimeListImpl.this, iterator));
        return categories;
    }

    @Override
    public final ForumTopicDetail getForumTopicDetail(final long id){
        return getForumTopicDetail(id, null, null);
    }

    @Override
    public final ForumTopicDetail getForumTopicDetail(final long id, final Integer limit){
        return getForumTopicDetail(id, limit, null);
    }

    @Override
    public final ForumTopicDetail getForumTopicDetail(final long id, final Integer limit, final Integer offset){
        final JsonObject response = handleResponse(
            () -> service.getForumBoard(
                isTokenAuth ? token : null,
                !isTokenAuth ? client_id : null,
                id,
                limit,
                offset
            )
        );
        if(response == null) return null;

        return asForumTopic(MyAnimeListImpl.this, response.getJsonObject("data"), id);
    }

    @Override
    public final ForumTopicDetailPostQuery getForumTopicDetailPostQuery(final long id){
        return new ForumTopicDetailPostQuery() {

            @Override
            public final List<Post> search(){
                final JsonObject response = handleResponse(
                    () -> service.getForumBoard(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        id,
                        limit,
                        offset
                    )
                );
                if(response == null) return null;

                final List<Post> posts = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonObject("data").getJsonArray("posts"))
                    posts.add(asPost(MyAnimeListImpl.this, iterator, id));
                return posts;
            }

            @Override
            public final PaginatedIterator<Post> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getForumBoard(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        id,
                        limit,
                        offset
                    ),
                    iterator -> asPost(MyAnimeListImpl.this, iterator, id)
                );
            }

        };
    }

    @Override
    public final ForumSearchQuery getForumTopics(){
        return new ForumSearchQuery() {

            @Override
            public final List<ForumTopic> search(){
                final JsonObject response = handleResponse(
                    () -> service.getForumTopics(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        boardId,
                        subboardId,
                        limit,
                        offset,
                        sort.field(),
                        query,
                        topicUsername,
                        username
                    )
                );
                if(response == null) return null;

                final List<ForumTopic> topics = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    topics.add(asForumTopicDetail(MyAnimeListImpl.this, iterator, boardId, subboardId));
                return topics;
            }

            @Override
            public final PaginatedIterator<ForumTopic> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getForumTopics(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        boardId,
                        subboardId,
                        limit,
                        offset,
                        sort.field(),
                        query,
                        topicUsername,
                        username
                    ),
                    iterator -> asForumTopicDetail(MyAnimeListImpl.this, iterator, boardId, subboardId)
                );
            }

        };
    }

    @Override
    public final MangaSearchQuery getManga(){
        return new MangaSearchQuery() {

            @Override
            public final List<MangaPreview> search(){
                final JsonObject response = handleResponse(
                    () -> service.getManga(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        query,
                        limit,
                        offset,
                        convertFields(Fields.manga, fields),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<MangaPreview> manga = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    manga.add(asMangaPreview(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return manga;
            }

            @Override
            public final PaginatedIterator<MangaPreview> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getManga(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        query,
                        limit,
                        offset,
                        convertFields(Fields.manga, fields),
                        nsfw
                    ),
                    iterator -> asMangaPreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))
                );
            }

        };
    }

    @Override
    public final Manga getManga(final long id){
        return getManga(id, (String[]) null);
    }

    @Override
    public final Manga getManga(final long id, final String... fields){
        return asManga(this,
        handleResponse(
            () -> service.getManga(
                isTokenAuth ? token : null,
                !isTokenAuth ? client_id : null,
                id,
                convertFields(Fields.manga, fields)
            )
        ));
    }

    @Override
    public final MangaRankingQuery getMangaRanking(final MangaRankingType rankingType){
        return getMangaRanking(Objects.requireNonNull(rankingType, "Ranking type cannot be null").field());
    }

    @Override
    public final MangaRankingQuery getMangaRanking(final String rankingType){
        return new MangaRankingQuery(Objects.requireNonNull(rankingType, "Ranking type cannot be null")) {

            @Override
            public final List<MangaRanking> search(){
                final JsonObject response = handleResponse(
                    () -> service.getMangaRanking(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        rankingType,
                        limit,
                        offset,
                        convertFields(Fields.manga, fields),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<MangaRanking> manga = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    manga.add(asMangaRanking(MyAnimeListImpl.this, iterator));
                return manga;
            }

            @Override
            public final PaginatedIterator<MangaRanking> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getMangaRanking(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        rankingType,
                        limit,
                        offset,
                        convertFields(Fields.manga, fields),
                        nsfw
                    ),
                    iterator -> asMangaRanking(MyAnimeListImpl.this, iterator)
                );
            }

        };
    }

    @Override
    public final MangaListUpdate updateMangaListing(final long id){
        return new MangaListUpdate(id) {

            @Override
            public synchronized final MangaListStatus update(){
                final JsonObject response = handleResponse(
                    () -> service.updateMangaListing(
                        Objects.requireNonNull(token, "Client ID not supported for this endpoint, create MyAnimeList object with either an Authenticator or Token"),
                        id,
                        status,
                        rereading,
                        score,
                        MyAnimeListSchema.asYMD(startDate),
                        MyAnimeListSchema.asYMD(finishDate),
                        volumesRead,
                        chaptersRead,
                        priority,
                        timesReread,
                        rereadValue,
                        toCommaSeparatedString(tags),
                        comments
                    )
                );
                if(response == null) return null;

                return asMangaListStatus(MyAnimeListImpl.this, response, id);
            }

        };
    }

    @Override
    public synchronized final void deleteMangaListing(final long id){
        try{
            handleVoidResponse(
                () -> service.deleteMangaListing(
                    Objects.requireNonNull(token, "Client ID not supported for this endpoint, create MyAnimeList object with either an Authenticator or Token"),
                    id
                )
            );
        }catch(final HttpException e){
            if(e.code() != 404)
                throw e;
        }
    }

    @Override
    public final UserMangaListQuery getUserMangaListing(){
        return getUserMangaListing("@me");
    }

    @Override
    public final UserMangaListQuery getUserMangaListing(final String username){
        return new UserMangaListQuery(Objects.requireNonNull(username, "Username cannot be null")) {

            @Override
            public final List<MangaListStatus> search(){
                final JsonObject response = handleResponse(
                    () -> service.getUserMangaListing(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        username.equals("@me") ? "@me" : APICall.encodeUTF8(username),
                        status,
                        sort,
                        limit,
                        offset,
                        convertFields(Fields.manga, fields),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<MangaListStatus> manga = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    manga.add(asMangaListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asMangaPreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))));
                return manga;
            }

            @Override
            public final PaginatedIterator<MangaListStatus> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getUserMangaListing(
                        isTokenAuth ? token : null,
                        !isTokenAuth ? client_id : null,
                        username.equals("@me") ? "@me" : APICall.encodeUTF8(username),
                        status,
                        sort,
                        limit,
                        offset,
                        convertFields(Fields.manga, fields),
                        nsfw
                    ),
                    iterator -> asMangaListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asMangaPreview(MyAnimeListImpl.this, iterator.getJsonObject("node")))
                );
            }

        };
    }

    @Override
    public final User getAuthenticatedUser(){
        return getUser("@me", (String[]) null);
    }

    @Override
    public final User getAuthenticatedUser(final String... fields){
        return getUser("@me", fields);
    }

    @Override
    public final User getUser(final String username){
        return getUser(username, (String[]) null);
    }

    @Override
    public final User getUser(final String username, final String... fields){
        Objects.requireNonNull(username, "Username cannot be null");
        return asUser(this,
        handleResponse(
            () -> service.getUser(
                Objects.requireNonNull(token, "Client ID not supported for this endpoint, create MyAnimeList object with either an Authenticator or Token"),
                username.equals("@me") ? "@me" : APICall.encodeUTF8(username),
                convertFields(Fields.user, fields)
            )
        ));
    }

// handle response

    private static void handleVoidResponse(final ExceptionSupplier<Response<?>,IOException> supplier){
        handleResponseCodes(supplier);
    }

    private static JsonObject handleResponse(final ExceptionSupplier<Response<?>,IOException> supplier){
        final Response<?> response = handleResponseCodes(supplier);
        return response.code() == HttpURLConnection.HTTP_OK ? (JsonObject) response.body() : null;
    }

    private static Response<?> handleResponseCodes(final ExceptionSupplier<Response<?>,IOException> supplier){
        try{
            final Response<?> response = supplier.get();
            switch(response.code()){
                case HttpURLConnection.HTTP_OK:
                    return response;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    throw new InvalidTokenException("The OAuth token provided is either invalid or expired");
                default:
                    try{
                        throw new HttpException(response.URL(), response.code(), (((JsonObject) response.body()).getString("message") + ' ' + ((JsonObject) response.body()).getString("error")).trim());
                    }catch(final Throwable ignored){
                        throw new HttpException(response.URL(), response.code(), response.raw());
                    }
            }
        }catch(final IOException e){ // client side failure
            throw new UncheckedIOException(e);
        }
    }

    private interface ExceptionSupplier<T,E extends Throwable>{

        T get() throws E;

    }

    @Override
    public final String toString(){
        return "MyAnimeList{" +
               "authenticator=" + authenticator +
               ", service=" + service +
               '}';
    }

// iterator

    private static class PagedIterator<T> extends PaginatedIterator<T> {

        private final Function<Integer,Response<JsonObject>> fullPageSupplier;
        private final Function<JsonObject,T> listAdapter;

        private final AtomicReference<Integer> nextOffset = new AtomicReference<>();

        PagedIterator(
            final Integer offset,
            final Function<Integer,Response<JsonObject>> fullPageSupplier,
            final Function<JsonObject,T> listAdapter
        ){
            this.fullPageSupplier   = fullPageSupplier;
            this.listAdapter        = listAdapter;

            // handle first page
            nextOffset.set(offset);
            list = getNextPage();
            size = list == null ? 0 : list.size();
        }

        @Override
        final boolean hasNextPage(){
            return nextOffset.get() != -1;
        }

        @Override
        synchronized final List<T> getNextPage(){
            final JsonObject response = handleResponse(() -> fullPageSupplier.apply(nextOffset.get()));

            if(response == null){
                nextOffset.set(-1);
                return null;
            }

            final List<T> list = new ArrayList<>();
            for(final JsonObject data :
                response.get("data") instanceof JsonObject && response.getJsonObject("data").containsKey("posts") // post iterator support
                ? response.getJsonObject("data").getJsonArray("posts")
                : response.getJsonArray("data")
            )
                list.add(listAdapter.apply(data));

            if(response.getJsonObject("paging").containsKey("next")){
                final Integer b4 = nextOffset.get();
                nextOffset.set((b4 == null ? 0 : b4) + list.size());
                return list;
            }
            nextOffset.set(-1);

            return list;
        }

    }

// formatter

    private static String toCommaSeparatedString(final List<String> fields){
        return toCommaSeparatedString(fields == null ? null : fields.toArray(new String[0]));
    }

    private static String toCommaSeparatedString(final String... fields){
        if(fields != null){
            if(fields.length == 0) return ""; // return blank for empty array

            final StringBuilder SB = new StringBuilder();
            for(final String field : fields)
                if(field.trim().length() > 0)
                    SB.append(field).append(',');
            return SB.toString().endsWith(",") ? SB.deleteCharAt(SB.length()-1).toString() : "";
        }
        return null;
    }

    private static final String inverted = "^%s$|^%s(?=,)|(?<=\\w)\\{%s}|(?:^|,)%s\\{.*?}|,%s|(?<=\\{)%s,";

    private static String convertFields(final String defaultFields, final List<String> fields){
        return convertFields(defaultFields, fields == null ? null : fields.toArray(new String[0]));
    }

    /**
     * Converts field array to comma separated string
     *
     * @param defaultFields default fields
     * @param fields fields
     * @return comma separated fields
     */
    private static String convertFields(final String defaultFields, final String... fields){
        if(fields == null || (fields.length == 1 && fields[0].equals(Fields.INVERTED)))
            return defaultFields;
        else if(fields.length == 0)
            return "";

        boolean inverted = false;
        for(final String field : fields){
            if(field.equals(Fields.INVERTED)){
                inverted = true;
                break;
            }
        }

        if(!inverted){
            return toCommaSeparatedString(fields);
        }else{
            String buffer = defaultFields;
            for(final String field : fields) // remove fields that are specified
                buffer = buffer.replaceAll(MyAnimeListImpl.inverted.replace("%s", Pattern.quote(field)), "");
            return buffer;
        }
    }

}
