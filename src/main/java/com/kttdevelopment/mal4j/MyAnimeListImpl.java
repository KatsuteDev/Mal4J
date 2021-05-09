/*
 * Copyright (C) 2021 Ktt Development
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
import com.kttdevelopment.mal4j.query.*;
import com.kttdevelopment.mal4j.user.User;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.kttdevelopment.mal4j.Json.*;
import static com.kttdevelopment.mal4j.MyAnimeListAPIResponseMapping.Anime.*;
import static com.kttdevelopment.mal4j.MyAnimeListAPIResponseMapping.Forum.*;
import static com.kttdevelopment.mal4j.MyAnimeListAPIResponseMapping.Manga.*;
import static com.kttdevelopment.mal4j.MyAnimeListAPIResponseMapping.User.*;

/**
 * Implements the {@link MyAnimeList} interface with the {@link MyAnimeListService}.
 *
 * @see MyAnimeList
 * @see MyAnimeListService
 * @since 1.0.0
 * @version 2.0.0
 * @author Ktt Development
 */
final class MyAnimeListImpl extends MyAnimeList{

    private transient String auth;
    private MyAnimeListAuthenticator authenticator;

    private final MyAnimeListService service = MyAnimeListService.create();

    MyAnimeListImpl(final String auth){
        Objects.requireNonNull(auth, "OAuth token cannot be null");
        if(!auth.startsWith("Bearer "))
            throw new IllegalArgumentException("Oauth token should start with 'Bearer'");
        this.auth = auth;
    }

    MyAnimeListImpl(final MyAnimeListAuthenticator authenticator){
        Objects.requireNonNull(authenticator, "Authenticator cannot be null");
        this.authenticator = authenticator;
        this.auth = authenticator.getAccessToken().getToken();
    }

    @Override
    public synchronized final void refreshOAuthToken(){
        if(authenticator == null)
            throw new UnsupportedOperationException("OAuth token refresh can only be used with authorization");
        this.auth = authenticator.refreshAccessToken().getToken();
    }

    //

    @Override
    public final AnimeSearchQuery getAnime(){
        return new AnimeSearchQuery() {

            @Override
            public final List<Anime> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnime(
                        auth,
                        query,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<Anime> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<Anime> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getAnime(
                        auth,
                        query,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
                        nsfw
                    ),
                    iterator -> asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node"))
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
                auth,
                id,
                asFieldList(toCommaSeparatedString(fields), Fields.anime)
            )
        ));
    }

    @Override
    public final AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType){
        return new AnimeRankingQuery(Objects.requireNonNull(rankingType, "Ranking type cannot be null")) {

            @Override
            public final List<AnimeRanking> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnimeRanking(
                        auth,
                        rankingType.field(),
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
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
                        auth,
                        rankingType.field(),
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
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
            public final List<Anime> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnimeSeason(
                        auth,
                        year,
                        season.field(),
                        sort != null ? sort.field() : null,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<Anime> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<Anime> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getAnimeSeason(
                        auth,
                        year,
                        season.field(),
                        sort != null ? sort.field() : null,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
                        nsfw
                    ),
                    iterator -> asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node"))
                );
            }

        };
    }

    @Override
    public final AnimeSuggestionQuery getAnimeSuggestions(){
        return new AnimeSuggestionQuery() {

            @Override
            public final List<Anime> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnimeSuggestions(
                        auth,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<Anime> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<Anime> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getAnimeSuggestions(
                        auth,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
                        nsfw
                    ),
                    iterator -> asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node"))
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
                        auth,
                        id,
                        status != null ? status.field() : null,
                        rewatching,
                        score,
                        asYMD(startDate),
                        asYMD(finishDate),
                        watchedEpisodes,
                        priority.value(),
                        timesRewatched,
                        rewatchValue.value(),
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
                    auth,
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
                        auth,
                        username.equals("@me") ? "@me" : Java9.URLEncoder.encode(username, StandardCharsets.UTF_8),
                        status != null ? status.field() : null,
                        sort != null ? sort.field() : null,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
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
                        auth,
                        username.equals("@me") ? "@me" : Java9.URLEncoder.encode(username, StandardCharsets.UTF_8),
                        status != null ? status.field() : null,
                        sort != null ? sort.field() : null,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.anime),
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
                auth)
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
                auth,
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
                        auth,
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
                        auth,
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
                        auth,
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
            public synchronized final PaginatedIterator<ForumTopic> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getForumTopics(
                        auth,
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
            public final List<Manga> search(){
                final JsonObject response = handleResponse(
                    () -> service.getManga(
                        auth,
                        query,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.manga),
                        nsfw
                    )
                );
                if(response == null) return null;

                final List<Manga> manga = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    manga.add(asManga(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return manga;
            }

            @Override
            public final PaginatedIterator<Manga> searchAll(){
                return new PagedIterator<>(
                    offset,
                    offset -> service.getManga(
                        auth,
                        query,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.manga),
                        nsfw
                    ),
                    iterator -> asManga(MyAnimeListImpl.this, iterator.getJsonObject("node"))
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
                auth,
                id,
                asFieldList(toCommaSeparatedString(fields), Fields.manga)
            )
        ));
    }

    @Override
    public final MangaRankingQuery getMangaRanking(final MangaRankingType rankingType){
        return new MangaRankingQuery(Objects.requireNonNull(rankingType, "Ranking type cannot be null")) {

            @Override
            public final List<MangaRanking> search(){
                final JsonObject response = handleResponse(
                    () -> service.getMangaRanking(
                        auth,
                        rankingType != null ? rankingType.field() : null,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.manga),
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
                        auth,
                        rankingType != null ? rankingType.field() : null,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.manga),
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
                        auth,
                        id,
                        status != null ? status.field() : null,
                        rereading,
                        score,
                        asYMD(startDate),
                        asYMD(finishDate),
                        volumesRead,
                        chaptersRead,
                        priority.value(),
                        timesReread,
                        rereadValue.value(),
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
                    auth,
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
                        auth,
                        username.equals("@me") ? "@me" : Java9.URLEncoder.encode(username, StandardCharsets.UTF_8),
                        status != null ? status.field() : null,
                        sort != null ? sort.field() : null,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.manga),
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
                        auth,
                        username.equals("@me") ? "@me" : Java9.URLEncoder.encode(username, StandardCharsets.UTF_8),
                        status != null ? status.field() : null,
                        sort != null ? sort.field() : null,
                        limit,
                        offset,
                        asFieldList(toCommaSeparatedString(fields), Fields.manga),
                        nsfw
                    ),
                    iterator -> asMangaListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asMangaPreview(MyAnimeListImpl.this, iterator.getJsonObject("node")))
                );
            }

        };
    }

    @Override
    public final User getMyself(){
        return getUser("@me", (String[]) null);
    }

    @Override
    public final User getMyself(final String... fields){
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
                auth,
                username.equals("@me") ? "@me" : Java9.URLEncoder.encode(username, StandardCharsets.UTF_8),
                asFieldList(toCommaSeparatedString(fields), Fields.user)
            )
        ));
    }

    //
    
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

            if(response.code() == HttpURLConnection.HTTP_OK)
                return response;
            else
                try{
                    throw new HttpException(response.URL(), response.code(), (((JsonObject) response.body()).getString("message") + ' ' + ((JsonObject) response.body()).getString("error")).trim());
                }catch(final Throwable ignored){
                    throw new HttpException(response.URL(), response.code(), response.raw());
                }
        }catch(final IOException e){ // client side failure
            throw new UncheckedIOException(e);
        }
    }

    private interface ExceptionSupplier<T,E extends Throwable>{

        T get() throws E;

    }

    @Override
    public String toString(){
        return "MyAnimeListImpl{" +
               "authenticator=" + authenticator +
               ", service=" + service +
               '}';
    }

    //

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

    //
    
    private static final String inverted = "(?:^%s$|(?<=\\w){%s}|(?:^|,)%s{.*?}|,%s|(?<={)%s,)";

    /**
     * Handles how fields are finally sent to the server.
     *
     * Current behavior sends all if fields are null and none if an empty array is supplied
     *
     * @param fields comma separated fields
     * @param fieldsIfNull fallback fields
     * @return fields
     */
    private static String asFieldList(final String fields, final String fieldsIfNull){
        return fields == null ? fieldsIfNull : fields;
    }

    private static String toCommaSeparatedString(final List<String> fields){
        return toCommaSeparatedString(fields == null ? null : fields.toArray(new String[0]));
    }

    private static String toCommaSeparatedString(final String... fields){
        if(fields != null){
            if(fields.length == 0) return ""; // return blank for empty array

            final StringBuilder SB = new StringBuilder();
            for(final String field : fields)
                if(!Java9.String.isBlank(field))
                    SB.append(field).append(',');

            final String str = SB.toString();
            if(!Java9.String.isBlank(str))
                return str.substring(0, str.length() -1);
        }
        return null;
    }

    private static String toFields(final String defaultFields, final boolean inverted, final String... fields){
        if(fields == null)
            return !inverted ? defaultFields : "";
        else if(fields.length == 0)
            return !inverted ? "" : defaultFields;

        if(!inverted){
            final StringBuilder OUT = new StringBuilder();
            for(final String field : fields)
                if(!Java9.String.isBlank((field)))
                    OUT.append(field).append(',');

            final String str = OUT.toString();
            return str.substring(0, str.length() - 1); // it is asserted that the length is at least 1
        }else{
            String buffer = defaultFields;
            for(final String field : fields)
                buffer = buffer.replaceAll(MyAnimeListImpl.inverted.replace("%s", Pattern.quote(field)), "");
            return buffer;
        }
    }

    private static String asISO8601(final Long millis){
        return millis == null ? null : new SimpleDateFormat(MyAnimeListAPIResponseMapping.ISO8601).format(new Date(millis));
    }

    private static String asYMD(final Long millis){
        return millis == null ? null : new SimpleDateFormat(MyAnimeListAPIResponseMapping.YMD).format(new Date(millis));
    }

}
