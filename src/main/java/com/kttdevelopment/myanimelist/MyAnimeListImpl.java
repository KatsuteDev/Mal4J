package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.APIStruct.Response;
import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.forum.*;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;
import com.kttdevelopment.myanimelist.query.*;
import com.kttdevelopment.myanimelist.user.User;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.kttdevelopment.myanimelist.Json.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponseMapping.Anime.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponseMapping.Forum.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponseMapping.Manga.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponseMapping.User.*;

/**
 * Implements the {@link MyAnimeList} interface with the {@link MyAnimeListService}.
 *
 * @see MyAnimeList
 * @see MyAnimeListService
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
final class MyAnimeListImpl extends MyAnimeList{

    private transient String auth;
    private MyAnimeListAuthenticator authenticator;

    private final MyAnimeListService service = MyAnimeListService.create();

    MyAnimeListImpl(final String auth){
        this.auth = auth;
    }

    MyAnimeListImpl(final MyAnimeListAuthenticator authenticator){
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

    @SuppressWarnings("SpellCheckingInspection")
    private static final String animeFields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,num_episodes,start_season,broadcast,source,average_episode_duration,rating,pictures,background,related_anime,related_manga,recommendations,studios,statistics,my_list_status{start_date,end_date,priority,num_times_rewatched,rewatch_value,tags,comments},list_status{start_date,end_date,priority,num_times_rewatched,rewatch_value,tags,comments}";

    @Override
    public final AnimeSearchQuery getAnime(){
        return new AnimeSearchQuery() {

            @Override
            public final List<Anime> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnime(
                        auth,
                        query,
                        between(0, limit, 100),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields),
                        nsfw)
                );
                if(response == null) return null;

                final List<Anime> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<Anime> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){ // required for first check (since no previous page has yet provided info on next paging)
                            final JsonObject response = handleResponse(
                                () -> service.getAnime(
                                    auth,
                                    query,
                                    between(0, limit, 100),
                                    between(0, current.get(), null),
                                    null,
                                    nsfw)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<Anime> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getAnime(
                                auth,
                                query,
                                between(0, limit, 100),
                                current.addAndGet(between(0, limit == null ? 100 : limit, 100)),
                                fields == null ? animeFields : asStringList(fields),
                                nsfw)
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<Anime> anime = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return anime;
                    }

                };
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
                fields == null ? animeFields : asStringList(fields))
        ));
    }

    @Override
    public final AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType){
        return new AnimeRankingQuery(Objects.requireNonNull(rankingType)) {

            @Override
            public final List<AnimeRanking> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnimeRanking(
                        auth,
                        rankingType.field(),
                        between(0, limit, 500),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields),
                        nsfw)
                );
                if(response == null) return null;

                final List<AnimeRanking> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnimeRanking(MyAnimeListImpl.this, iterator));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimeRanking> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){
                            final JsonObject response = handleResponse(
                                () -> service.getAnimeRanking(
                                    auth,
                                    rankingType.field(),
                                    between(0, limit, 500),
                                    between(0, current.get(), null),
                                    null,
                                    nsfw)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<AnimeRanking> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getAnimeRanking(
                                auth,
                                rankingType.field(),
                                between(0, limit, 500),
                                current.addAndGet(between(0, limit == null ? 500 : limit, 500)),
                                fields == null ? animeFields : asStringList(fields),
                                nsfw)
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<AnimeRanking> anime = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            anime.add(asAnimeRanking(MyAnimeListImpl.this, iterator));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return anime;
                    }

                };
            }
        };
    }

    @Override
    public final AnimeSeasonQuery getAnimeSeason(final int year, final Season season){
        return new AnimeSeasonQuery(year, Objects.requireNonNull(season)) {

            @Override
            public final List<Anime> search(){
                final JsonObject response = handleResponse(
                    () -> service.getAnimeSeason(
                        auth,
                        year,
                        season.field(),
                        sort != null ? sort.field() : null,
                        between(0, limit, 500),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields),
                        nsfw)
                );
                if(response == null) return null;

                final List<Anime> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<Anime> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){ // required for first check (since no previous page has yet provided info on next paging)
                            final JsonObject response = handleResponse(
                                () -> service.getAnimeSeason(
                                    auth,
                                    year,
                                    season.field(),
                                    sort != null ? sort.field() : null,
                                    between(0, limit, 500),
                                    between(0, current.get(), null),
                                    null,
                                    nsfw)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<Anime> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getAnimeSeason(
                                auth,
                                year,
                                season.field(),
                                sort != null ? sort.field() : null,
                                between(0, limit, 500),
                                current.addAndGet(between(0, limit == null ? 500 : limit, 500)),
                                fields == null ? animeFields : asStringList(fields),
                                nsfw)
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<Anime> anime = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return anime;
                    }

                };
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
                        between(0, limit, 100),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields),
                        nsfw)
                );
                if(response == null) return null;

                final List<Anime> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return anime;
            }

            @Override
            public final PaginatedIterator<Anime> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){ // required for first check (since no previous page has yet provided info on next paging)
                            final JsonObject response = handleResponse(
                                () -> service.getAnimeSuggestions(
                                    auth,
                                    between(0, limit, 100),
                                    between(0, current.get(), null),
                                    null,
                                    nsfw)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<Anime> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getAnimeSuggestions(
                                auth,
                                between(0, limit, 100),
                                current.addAndGet(between(0, limit == null ? 100 : limit, 100)),
                                fields == null ? animeFields : asStringList(fields),
                                nsfw)
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<Anime> anime = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            anime.add(asAnime(MyAnimeListImpl.this, iterator.getJsonObject("node")));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return anime;
                    }

                };
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
                        between(0, score, 10),
                        between(0, watchedEpisodes, null),
                        between(0, priority.value(), 2),
                        between(0, timesRewatched, null),
                        between(0, rewatchValue.value(), 5),
                        asStringList(tags),
                        comments)
                );
                if(response == null) return null;

                return asAnimeListStatus(MyAnimeListImpl.this, response, id);
            }

        };
    }

    @Override
    public synchronized final void deleteAnimeListing(final long id){
        handleVoidResponse(
            () -> service.deleteAnimeListing(
                auth,
                (int) id)
        );
    }

    @Override
    public final UserAnimeListQuery getUserAnimeListing(){
        return getUserAnimeListing("@me");
    }

    @Override
    public final UserAnimeListQuery getUserAnimeListing(final String username){
        return new UserAnimeListQuery(Objects.requireNonNull(username)) {

            @Override
            public final List<AnimeListStatus> search(){
                final JsonObject response = handleResponse(
                    () -> service.getUserAnimeListing(
                        auth,
                        username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                        status != null ? status.field() : null,
                        sort != null ? sort.field() : null,
                        between(0, limit, 1000),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields))
                );
                if(response == null) return null;

                final List<AnimeListStatus> anime = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    anime.add(asAnimeListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimeListStatus> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){ // required for first check (since no previous page has yet provided info on next paging)
                            final JsonObject response = handleResponse(
                                () -> service.getUserAnimeListing(
                                    auth,
                                    username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                                    status != null ? status.field() : null,
                                    sort != null ? sort.field() : null,
                                    between(0, limit, 1000),
                                    between(0, current.get(), null),
                                    null)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<AnimeListStatus> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getUserAnimeListing(
                                auth,
                                username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                                status != null ? status.field() : null,
                                sort != null ? sort.field() : null,
                                between(0, limit, 1000),
                                current.addAndGet(between(0, limit == null ? 1000 : limit, 1000)),
                                fields == null ? animeFields : asStringList(fields))
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<AnimeListStatus> anime = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            anime.add(asAnimeListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asAnimePreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return anime;
                    }

                };
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
    public final ForumTopic getForumTopicDetail(final long id){
        return getForumTopicDetail(id, -1, -1);
    }

    @Override
    public final ForumTopic getForumTopicDetail(final long id, final int limit){
        return getForumTopicDetail(id, limit, -1);
    }

    @Override
    public final ForumTopic getForumTopicDetail(final long id, final int limit, final int offset){
        final JsonObject response = handleResponse(
            () -> service.getForumBoard(
                auth,
                id,
                limit == -1 ? null : between(0, limit, 100),
                offset == -1 ? null : between(0, offset, null))
        );
        if(response == null) return null;

        return asForumTopic(MyAnimeListImpl.this, response.getJsonObject("data"));
    }

    @Override
    public final ForumSearchQuery getForumTopics(){
        return new ForumSearchQuery() {

            @Override
            public final List<ForumTopicDetail> search(){
                final JsonObject response = handleResponse(
                    () -> service.getForumTopics(
                        auth,
                        boardId,
                        subboardId,
                        between(0, limit, 100),
                        between(0, offset, null),
                        sort,
                        query,
                        topicUsername,
                        username)
                );
                if(response == null) return null;

                final List<ForumTopicDetail> topics = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    topics.add(asForumTopicDetail(MyAnimeListImpl.this, iterator));
                return topics;
            }

            @Override
            public synchronized final PaginatedIterator<ForumTopicDetail> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){ // required for first check (since no previous page has yet provided info on next paging)
                            final JsonObject response = handleResponse(
                                () -> service.getForumTopics(
                                    auth,
                                    boardId,
                                    subboardId,
                                    between(0, limit, 100),
                                    between(0, current.get(), null),
                                    sort,
                                    query,
                                    topicUsername,
                                    username)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<ForumTopicDetail> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getForumTopics(
                                auth,
                                boardId,
                                subboardId,
                                between(0, limit, 100),
                                current.addAndGet(between(0, limit == null ? 100 : limit, 100)),
                                sort,
                                query,
                                topicUsername,
                                username)
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<ForumTopicDetail> topics = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            topics.add(asForumTopicDetail(MyAnimeListImpl.this, iterator));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return topics;
                    }

                };
            }

        };
    }

    private static final String mangaFields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,num_volumes,num_chapters,authors{first_name,last_name},pictures,background,related_anime,related_manga,recommendations,serialization{name,role},my_list_status{start_date,finish_date,priority,num_times_reread,reread_value,tags,comments},list_status{start_date,finish_date,priority,num_times_reread,reread_value,tags,comments}";

    @Override
    public final MangaSearchQuery getManga(){
        return new MangaSearchQuery() {

            @Override
            public final List<Manga> search(){
                final JsonObject response = handleResponse(
                    () -> service.getManga(
                        auth,
                        query,
                        between(0, limit, 100),
                        between(0, offset, null),
                        fields == null ? mangaFields : asStringList(fields),
                        nsfw)
                );
                if(response == null) return null;

                final List<Manga> manga = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    manga.add(asManga(MyAnimeListImpl.this, iterator.getJsonObject("node")));
                return manga;
            }

            @Override
            public final PaginatedIterator<Manga> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){ // required for first check (since no previous page has yet provided info on next paging)
                            final JsonObject response = handleResponse(
                                () -> service.getManga(
                                    auth,
                                    query,
                                    between(0, limit, 100),
                                    between(0, current.get(), null),
                                    null,
                                    nsfw)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<Manga> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getManga(
                                auth,
                                query,
                                between(0, limit, 100),
                                current.addAndGet(between(0, limit == null ? 100 : limit, 100)),
                                fields == null ? mangaFields : asStringList(fields),
                                nsfw)
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<Manga> manga = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            manga.add(asManga(MyAnimeListImpl.this, iterator.getJsonObject("node")));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return manga;
                    }

                };
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
                fields == null ? mangaFields : asStringList(fields))
        ));
    }

    @Override
    public final MangaRankingQuery getMangaRanking(final MangaRankingType rankingType){
        return new MangaRankingQuery(Objects.requireNonNull(rankingType)) {

            @Override
            public final List<MangaRanking> search(){
                final JsonObject response = handleResponse(
                    () -> service.getMangaRanking(
                        auth,
                        rankingType != null ? rankingType.field() : null,
                        between(0, limit, 500),
                        between(0, offset, null),
                        fields == null ? mangaFields : asStringList(fields),
                        nsfw)
                );
                if(response == null) return null;

                final List<MangaRanking> manga = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    manga.add(asMangaRanking(MyAnimeListImpl.this, iterator));
                return manga;
            }

            @Override
            public final PaginatedIterator<MangaRanking> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){ // required for first check (since no previous page has yet provided info on next paging)
                            final JsonObject response = handleResponse(
                                () -> service.getMangaRanking(
                                    auth,
                                    rankingType != null ? rankingType.field() : null,
                                    between(0, limit, 500),
                                    between(0, offset, null),
                                    null,
                                    nsfw)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<MangaRanking> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getMangaRanking(
                                auth,
                                rankingType != null ? rankingType.field() : null,
                                between(0, limit, 500),
                                current.addAndGet(between(0, limit == null ? 500 : limit, 500)),
                                fields == null ? mangaFields : asStringList(fields),
                                nsfw)
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<MangaRanking> manga = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            manga.add(asMangaRanking(MyAnimeListImpl.this, iterator));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return manga;
                    }

                };
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
                        between(0, score, 10),
                        between(0, volumesRead, null),
                        between(0, chaptersRead, null),
                        between(0, priority.value(), 2),
                        between(0, timesReread, null),
                        between(0, rereadValue.value(), 5),
                        asStringList(tags),
                        comments)
                );
                if(response == null) return null;

                return asMangaListStatus(MyAnimeListImpl.this, response, id);
            }

        };
    }

    @Override
    public synchronized final void deleteMangaListing(final long id){
        handleVoidResponse(
            () -> service.deleteMangaListing(
                auth,
                id)
        );
    }

    @Override
    public final UserMangaListQuery getUserMangaListing(){
        return getUserMangaListing("@me");
    }

    @Override
    public final UserMangaListQuery getUserMangaListing(final String username){
        return new UserMangaListQuery(Objects.requireNonNull(username)) {

            @Override
            public final List<MangaListStatus> search(){
                final JsonObject response = handleResponse(
                    () -> service.getUserMangaListing(
                        auth,
                        username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                        status != null ? status.field() : null,
                        sort != null ? sort.field() : null,
                        between(0, limit, 1000),
                        between(0, offset, null),
                        fields == null ? mangaFields : asStringList(fields))
                );
                if(response == null) return null;

                final List<MangaListStatus> manga = new ArrayList<>();
                for(final JsonObject iterator : response.getJsonArray("data"))
                    manga.add(asMangaListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asMangaPreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))));
                return manga;
            }

            @Override
            public final PaginatedIterator<MangaListStatus> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-between(0, limit == null ? 100 : limit, 100)); // make so first nextPage returns offset 0

                    private final AtomicReference<Boolean> hasNextPage = new AtomicReference<>(null);

                    @Override
                    final boolean hasNextPage(){
                        if(hasNextPage.get() == null){ // required for first check (since no previous page has yet provided info on next paging)
                            final JsonObject response = handleResponse(
                                () -> service.getUserMangaListing(
                                    auth,
                                    username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                                    status != null ? status.field() : null,
                                    sort != null ? sort.field() : null,
                                    between(0, limit, 1000),
                                    between(0, current.get(), null),
                                    null)
                            );
                            hasNextPage.set(response != null && response.getJsonObject("paging").containsKey("next"));
                        }
                        return hasNextPage.get();
                    }

                    @Override
                    synchronized final List<MangaListStatus> getNextPage(){
                        final JsonObject response = handleResponse(
                            () -> service.getUserMangaListing(
                                auth,
                                username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                                status != null ? status.field() : null,
                                sort != null ? sort.field() : null,
                                between(0, limit, 1000),
                                current.addAndGet(between(0, limit == null ? 1000 : limit, 1000)),
                                fields == null ? mangaFields : asStringList(fields))
                        );

                        if(response == null){
                            hasNextPage.set(false); // make sure null responses count as no next page
                            return null;
                        }

                        final List<MangaListStatus> manga = new ArrayList<>();
                        for(final JsonObject iterator : response.getJsonArray("data"))
                            manga.add(asMangaListStatus(MyAnimeListImpl.this, iterator.getJsonObject("list_status"), asMangaPreview(MyAnimeListImpl.this, iterator.getJsonObject("node"))));

                        hasNextPage.set(response.getJsonObject("paging").containsKey("next"));

                        return manga;
                    }

                };
            }

        };
    }

    private static final String userFields = "birthday,time_zone,anime_statistics";

    @Override
    public final User getMyself(){
        return getUser("@me", (String[]) null);
    }

    @Override
    public final User getMyself(final String[] fields){
        return getUser("@me", fields);
    }

    @Override
    public final User getUser(final String username){
        return getUser(username, (String[]) null);
    }

    @Override
    public final User getUser(final String username, final String... fields){
        return asUser(this,
        handleResponse(
            () -> service.getUser(
                auth,
                username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                fields == null ? userFields : asStringList(fields))
        ));
    }

    //
    
    private void handleVoidResponse(final ExceptionSupplier<Response<?>,IOException> supplier){
        handleResponseCodes(supplier);
    }
    
    private JsonObject handleResponse(final ExceptionSupplier<Response<?>,IOException> supplier){
        final Response<?> response = handleResponseCodes(supplier);
        return response.code() == HttpURLConnection.HTTP_OK ? (JsonObject) response.body() : null;
    }

    private Response<?> handleResponseCodes(final ExceptionSupplier<Response<?>,IOException> supplier){
        try{
            final Response<?> response = supplier.get();

            if(response.code() == HttpURLConnection.HTTP_OK)
                return response;
            else
                try{
                    throw new HTTPException(response.URL(), response.code(), (((JsonObject) response.body()).getString("message") + ' ' + ((JsonObject) response.body()).getString("error")).trim());
                }catch(final ClassCastException ignored){
                    throw new HTTPException(response.URL(), response.code(), response.raw());
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

    private static String asStringList(final List<String> fields){
        return asStringList(fields == null ? null : fields.toArray(new String[0]));
    }

    private static String asStringList(final String... fields){
        if(fields != null && fields.length > 0){
            final StringBuilder SB = new StringBuilder();
            for(final String field : fields)
                if(!field.isBlank())
                    SB.append(field).append(',');

            final String str = SB.toString();
            if(!str.isBlank())
                return str.substring(0, str.length() -1);
        }
        return fields == null ? null : "";
    }

    //

    @SuppressWarnings("SameParameterValue")
    private static Integer between(final Integer min, final Integer between, final Integer max){
        if(between == null)
            return null;
        else if(min == null)
            return Math.min(between, max);
        else if(max == null)
            return Math.max(between, min);
        else
            return Math.min(Math.max(min, between), max);
    }

}
