package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.forum.ForumTopicDetail;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;
import com.kttdevelopment.myanimelist.query.*;
import com.kttdevelopment.myanimelist.user.User;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.*;
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
@SuppressWarnings("deprecation")
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
    public synchronized final void refreshOAuthToken() throws IOException{
        if(authenticator == null)
            throw new UnsupportedOperationException("OAuth token refresh can only be used with authorization");
        this.auth = authenticator.refreshAccessToken().getToken();
    }

    //

    @SuppressWarnings("SpellCheckingInspection")
    private static final String animeFields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,num_episodes,start_season,broadcast,source,average_episode_duration,rating,pictures,background,related_anime,related_manga,recommendations,studios,statistics,my_list_status{start_date,end_date,priority,num_times_rewatched,rewatch_value,tags,comments},list_status{start_date,end_date,priority,num_times_rewatched,rewatch_value,tags,comments}";

    @Override
    public final AnimeSearchQuery getAnime(){
        return new AnimeSearchQuery(service) {

            @Override
            public final List<AnimePreview> search(){
                final Call.GetAnimeList response = handleResponse(
                    () -> service.getAnime(
                        auth,
                        query,
                        between(0, limit, 100),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields),
                        nsfw)
                    .execute()
                );
                if(response == null) return null;

                final List<AnimePreview> anime = new ArrayList<>();
                for(final Common.Node<TopLevelObject.Anime> iterator : response.data)
                    anime.add(asAnimePreview(MyAnimeListImpl.this, iterator.node));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimePreview> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetAnimeList response = handleResponse(
                            () -> service.getAnime(
                                auth,
                                query,
                                between(0, limit, 100),
                                between(0, current.get(), null),
                                null,
                                nsfw)
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<AnimePreview> getNextPage(){
                        final AnimeSearchQuery asq = getAnime()
                            .withQuery(query)
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 100)))
                            .withFields(fields);
                        if(nsfw != null)
                            asq.includeNSFW(nsfw);
                        return asq.search();
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
            .execute()
        ));
    }

    @Override
    public final AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType){
        return new AnimeRankingQuery(service, Objects.requireNonNull(rankingType)) {

            @Override
            public final List<AnimeRanking> search(){
                final Call.GetAnimeRanking response = handleResponse(
                    () -> service.getAnimeRanking(
                        auth,
                        rankingType.field(),
                        between(0, limit, 500),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields),
                        nsfw)
                    .execute()
                );
                if(response == null) return null;

                final List<AnimeRanking> anime = new ArrayList<>();
                for(final SubLevelObject.Ranking<TopLevelObject.Anime> iterator : response.data)
                    anime.add(asAnimeRanking(MyAnimeListImpl.this, iterator));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimeRanking> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetAnimeRanking response = handleResponse(
                            () -> service.getAnimeRanking(
                                auth,
                                rankingType.field(),
                                between(0, limit, 500),
                                between(0,  current.get(), null),
                                null,
                                nsfw)
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<AnimeRanking> getNextPage(){
                        final AnimeRankingQuery arq = getAnimeRanking(rankingType)
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 500)))
                            .withFields(fields);
                        if(nsfw != null)
                            arq.includeNSFW(nsfw);
                        return arq.search();
                    }

                };
            }
        };
    }

    @Override
    public final AnimeSeasonQuery getAnimeSeason(final int year, final Season season){
        return new AnimeSeasonQuery(service, year, Objects.requireNonNull(season)) {

            @Override
            public final List<AnimePreview> search(){
                final Call.GetSeasonalAnime response = handleResponse(
                    () -> service.getAnimeSeason(
                        auth,
                        year,
                        season.field(),
                        sort != null ? sort.field() : null,
                        between(0, limit, 500),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields),
                        nsfw)
                    .execute()
                );
                if(response == null) return null;

                final List<AnimePreview> anime = new ArrayList<>();
                for(final Common.Node<TopLevelObject.Anime> iterator : response.data)
                    anime.add(asAnimePreview(MyAnimeListImpl.this, iterator.node));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimePreview> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetSeasonalAnime response = handleResponse(
                            () -> service.getAnimeSeason(
                                auth,
                                year,
                                season.field(),
                                sort != null ? sort.field() : null,
                                between(0, limit, 500),
                                between(0, current.get(), null),
                                null,
                                nsfw)
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<AnimePreview> getNextPage(){
                        final AnimeSeasonQuery asq = getAnimeSeason(year, season)
                            .sortBy(sort)
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 500)))
                            .withFields(fields);
                        if(nsfw != null)
                            asq.includeNSFW(nsfw);
                        return asq.search();
                    }

                };
            }

        };
    }

    @Override
    public final AnimeSuggestionQuery getAnimeSuggestions(){
        return new AnimeSuggestionQuery(service) {

            @Override
            public final List<AnimePreview> search(){
                final Call.GetSuggestedAnime response = handleResponse(
                    () -> service.getAnimeSuggestions(
                        auth,
                        between(0, limit, 100),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields),
                        nsfw)
                    .execute()
                );
                if(response == null) return null;

                final List<AnimePreview> anime = new ArrayList<>();
                for(final Common.Node<TopLevelObject.Anime> iterator : response.data)
                    anime.add(asAnimePreview(MyAnimeListImpl.this, iterator.node));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimePreview> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetSuggestedAnime response = handleResponse(
                            () -> service.getAnimeSuggestions(
                                auth,
                                between(0, limit, 100),
                                between(0, current.get(), null),
                                null,
                                nsfw)
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<AnimePreview> getNextPage(){
                        final AnimeSuggestionQuery asq = getAnimeSuggestions()
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 100)))
                            .withFields(fields);
                        if(nsfw != null)
                            asq.includeNSFW(nsfw);
                        return asq.search();
                    }

                };
            }

        };
    }

    @Override
    public final AnimeListUpdate updateAnimeListing(final long id){
        return new AnimeListUpdate(service, id) {

            @Override
            public synchronized final AnimeListStatus update(){
                final Call.UpdateUserAnimeList response = handleResponse(
                    () -> service.updateAnimeListing(
                        auth,
                        id,
                        status != null ? status.field() : null,
                        rewatching,
                        between(0, score, 10),
                        between(0, watchedEpisodes, null),
                        between(0, priority, 2),
                        between(0, timesRewatched, null),
                        between(0, rewatchValue, 5),
                        asStringList(tags),
                        comments)
                    .execute()
                );
                if(response == null) return null;

                return asAnimeListStatus(MyAnimeListImpl.this, response, id);
            }

        };
    }

    @Override
    public synchronized final void deleteAnimeListing(final long id){
        handleResponse(
            () -> service.deleteAnimeListing(
                auth,
                (int) id)
            .execute()
        );
    }

    @Override
    public final UserAnimeListQuery getUserAnimeListing(){
        return getUserAnimeListing("@me");
    }

    @Override
    public final UserAnimeListQuery getUserAnimeListing(final String username){
        return new UserAnimeListQuery(service, Objects.requireNonNull(username)) {

            @Override
            public final List<AnimeListStatus> search(){
                final Call.GetUserAnimeList response = handleResponse(
                    () -> service.getUserAnimeListing(
                        auth,
                        username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                        status != null ? status.field() : null,
                        sort != null ? sort.field() : null,
                        between(0, limit, 1000),
                        between(0, offset, null),
                        fields == null ? animeFields : asStringList(fields))
                    .execute()
                );
                if(response == null) return null;

                final List<AnimeListStatus> anime = new ArrayList<>();
                for(final SubLevelObject.ListEdge<TopLevelObject.Anime,Call.UpdateUserAnimeList> iterator : response.data)
                    anime.add(asAnimeListStatus(MyAnimeListImpl.this, iterator.list_status, asAnimePreview(MyAnimeListImpl.this, iterator.node)));
                return anime;
            }

            @Override
            public final PaginatedIterator<AnimeListStatus> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetUserAnimeList response = handleResponse(
                            () -> service.getUserAnimeListing(
                                auth,
                                username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                                status != null ? status.field() : null,
                                sort != null ? sort.field() : null,
                                between(0, limit, 1000),
                                between(0, current.get(), null),
                                null)
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<AnimeListStatus> getNextPage(){
                        return getUserAnimeListing(username)
                            .withStatus(status)
                            .sortBy(sort)
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 1000)))
                            .withFields(fields)
                            .search();
                    }

                };
            }

        };
    }

    @Override
    public final List<ForumCategory> getForumBoards(){
        final Call.GetForumBoards response = handleResponse(
            () -> service.getForumBoards(
                auth)
            .execute()
        );
        if(response == null) return null;

        final List<ForumCategory> categories = new ArrayList<>();
        for(final TopLevelObject.ForumCategory iterator : response.categories)
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

    @SuppressWarnings("CommentedOutCode")
    @Override
    public final ForumTopic getForumTopicDetail(final long id, final int limit, final int offset){
        final Call.GetForumTopicDetail response = handleResponse(
            () -> service.getForumBoard(
                auth,
                id,
                limit == -1 ? null : between(0, limit, 100),
                offset == -1 ? null : between(0, offset, null))
            .execute()
        );
        if(response == null) return null;

        // final List<ForumTopic> topics = new ArrayList<>();
        // for(final TopLevelObject.ForumTopicData iterator : response.data)
        //     topics.add(asForumTopic(MyAnimeListImpl.this, iterator));
        // return topics;
        return asForumTopic(MyAnimeListImpl.this, response.data);
    }

    @Override
    public final ForumSearchQuery getForumTopics(){
        return new ForumSearchQuery(service) {

            @Override
            public final List<ForumTopicDetail> search(){
                final Call.GetForumTopics response = handleResponse(
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
                    .execute()
                );
                if(response == null) return null;

                final List<ForumTopicDetail> topics = new ArrayList<>();
                for(final TopLevelObject.ForumTopicsData iterator : response.data)
                    topics.add(asForumTopicDetail(MyAnimeListImpl.this, iterator));
                return topics;
            }

            @Override
            public synchronized final PaginatedIterator<ForumTopicDetail> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetForumTopics response = handleResponse(
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
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<ForumTopicDetail> getNextPage(){
                        return getForumTopics()
                            .withBoardId(boardId)
                            .withSubboardId(subboardId)
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 100)))
                            .withQuery(query)
                            .withTopicUsername(topicUsername)
                            .withUsername(username)
                            .search();
                    }

                };
            }

        };
    }

    private static final String mangaFields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,num_volumes,num_chapters,authors{first_name,last_name},pictures,background,related_anime,related_manga,recommendations,serialization{name,role},my_list_status{start_date,finish_date,priority,num_times_reread,reread_value,tags,comments},list_status{start_date,finish_date,priority,num_times_reread,reread_value,tags,comments}";

    @Override
    public final MangaSearchQuery getManga(){
        return new MangaSearchQuery(service) {

            @Override
            public final List<MangaPreview> search(){
                final Call.GetMangaList response = handleResponse(
                    () -> service.getManga(
                        auth,
                        query,
                        between(0, limit, 100),
                        between(0, offset, null),
                        fields == null ? mangaFields : asStringList(fields),
                        nsfw)
                    .execute()
                );
                if(response == null) return null;

                final List<MangaPreview> manga = new ArrayList<>();
                for(final Common.Node<TopLevelObject.Manga> iterator : response.data)
                    manga.add(asMangaPreview(MyAnimeListImpl.this, iterator.node));
                return manga;
            }

            @Override
            public final PaginatedIterator<MangaPreview> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetMangaList response = handleResponse(
                            () -> service.getManga(
                                auth,
                                query,
                                between(0, limit, 100),
                                between(0, current.get(), null),
                                null,
                                nsfw)
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<MangaPreview> getNextPage(){
                        final MangaSearchQuery msq = getManga()
                            .withQuery(query)
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 100)))
                            .withFields(fields);
                        if(nsfw != null)
                            msq.includeNSFW(nsfw);
                        return msq.search();
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
            .execute()
        ));
    }

    @Override
    public final MangaRankingQuery getMangaRanking(final MangaRankingType rankingType){
        return new MangaRankingQuery(service, Objects.requireNonNull(rankingType)) {

            @Override
            public final List<MangaRanking> search(){
                final Call.GetMangaRanking response = handleResponse(
                    () -> service.getMangaRanking(
                        auth,
                        rankingType != null ? rankingType.field() : null,
                        between(0, limit, 500),
                        between(0, offset, null),
                        fields == null ? mangaFields : asStringList(fields),
                        nsfw)
                    .execute()
                );
                if(response == null) return null;

                final List<MangaRanking> manga = new ArrayList<>();
                for(final SubLevelObject.Ranking<TopLevelObject.Manga> iterator : response.data)
                    manga.add(asMangaRanking(MyAnimeListImpl.this, iterator));
                return manga;
            }

            @Override
            public final PaginatedIterator<MangaRanking> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetMangaRanking response = handleResponse(
                            () -> service.getMangaRanking(
                                auth,
                                rankingType != null ? rankingType.field() : null,
                                between(0, limit, 500),
                                between(0, offset, null),
                                fields == null ? mangaFields : asStringList(fields),
                                nsfw)
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<MangaRanking> getNextPage(){
                        final MangaRankingQuery mrq = getMangaRanking(rankingType)
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 500)))
                            .withFields(fields);
                        if(nsfw != null)
                            mrq.includeNSFW(nsfw);
                        return mrq.search();
                    }

                };
            }

        };
    }

    @Override
    public final MangaListUpdate updateMangaListing(final long id){
        return new MangaListUpdate(service, id) {

            @Override
            public synchronized final MangaListStatus update(){
                final Call.UpdateUserMangaList response = handleResponse(
                    () -> service.updateMangaListing(
                        auth,
                        id,
                        status != null ? status.field() : null,
                        rereading,
                        between(0, score, 10),
                        between(0, volumesRead, null),
                        between(0, chaptersRead, null),
                        between(0, priority, 2),
                        between(0, timesReread, null),
                        between(0, rereadValue, 5),
                        asStringList(tags),
                        comments)
                    .execute()
                );
                if(response == null) return null;

                return asMangaListStatus(MyAnimeListImpl.this, response, id);
            }

        };
    }

    @Override
    public synchronized final void deleteMangaListing(final long id){
        handleResponse(
            () -> service.deleteMangaListing(
                auth,
                id)
            .execute()
        );
    }

    @Override
    public final UserMangaListQuery getUserMangaListing(){
        return getUserMangaListing("@me");
    }

    @Override
    public final UserMangaListQuery getUserMangaListing(final String username){
        return new UserMangaListQuery(service, Objects.requireNonNull(username)) {

            @Override
            public final List<MangaListStatus> search(){
                final Call.GetUserMangaList response = handleResponse(
                    () -> service.getUserMangaListing(
                        auth,
                        username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                        status != null ? status.field() : null,
                        sort != null ? sort.field() : null,
                        between(0, limit, 1000),
                        between(0, offset, null),
                        fields == null ? mangaFields : asStringList(fields))
                    .execute()
                );
                if(response == null) return null;

                final List<MangaListStatus> manga = new ArrayList<>();
                for(final SubLevelObject.ListEdge<TopLevelObject.Manga,Call.UpdateUserMangaList> iterator : response.data)
                    manga.add(asMangaListStatus(MyAnimeListImpl.this, iterator.list_status, asMangaPreview(MyAnimeListImpl.this, iterator.node)));
                return manga;
            }

            @Override
            public final PaginatedIterator<MangaListStatus> searchAll(){
                return new PaginatedIterator<>() {

                    private final AtomicInteger current = new AtomicInteger(-limit); // make so first nextPage returns offset 0

                    @Override
                    final boolean hasNextPage(){
                        final Call.GetUserMangaList response = handleResponse(
                            () -> service.getUserMangaListing(
                                auth,
                                username.equals("@me") ? "@me" : URLEncoder.encode(username, StandardCharsets.UTF_8),
                                status != null ? status.field() : null,
                                sort != null ? sort.field() : null,
                                between(0, limit, 1000),
                                between(0, current.get(), null),
                                null)
                            .execute()
                        );
                        return response != null && response.paging.next != null;
                    }

                    @Override
                    synchronized final List<MangaListStatus> getNextPage(){
                        return getUserMangaListing(username)
                            .withStatus(status)
                            .sortBy(sort)
                            .withLimit(limit)
                            .withOffset(current.addAndGet(between(0, limit, 1000)))
                            .withFields(fields)
                            .search();
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
            .execute()
        ));
    }

    //

    private <R> R handleResponse(final ExceptionSupplier<Response<R>,IOException> supplier){
        try{
            final Response<R> response = supplier.get();
            switch(response.code()){
                case HttpURLConnection.HTTP_OK:
                    return response.body();
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    throw new InvalidParametersException(response.toString());
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    throw new InvalidAuthException(response.toString());
                case HttpURLConnection.HTTP_FORBIDDEN:
                    throw new ConnectionForbiddenException(response.toString());
            }
        }catch(final IOException e){ // client side failure
            throw new FailedRequestException(e);
        }
        return null;
    }

    private interface ExceptionSupplier<T,E extends Exception>{

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
        return asStringList(fields.toArray(new String[0]));
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
        return null;
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
