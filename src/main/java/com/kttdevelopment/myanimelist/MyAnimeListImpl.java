package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.forum.property.ForumTopicDetail;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;
import com.kttdevelopment.myanimelist.query.*;
import com.kttdevelopment.myanimelist.user.User;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponseMapping.Anime.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponseMapping.Forum.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponseMapping.Manga.*;
import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponseMapping.User.*;

public final class MyAnimeListImpl extends MyAnimeList{

    private transient final String auth;

    private final MyAnimeListService service = MyAnimeListService.create();

    public MyAnimeListImpl(final String auth){
        this.auth = auth;
    }

    //

    @Override
    public final AnimeSearchQuery getAnime(){
        return new AnimeSearchQuery(service) {

            @Override
            public final List<AnimePreview> search(){
                final Call.GetAnimeList response = handleResponse(
                    () -> service.getAnime(
                        auth,
                        query,
                        limit,
                        offset,
                        fields == null ? Call.GetAnime.fields : asStringListEncoded(fields),
                        false)
                    .execute()
                );
                if(response == null) return null;

                final List<AnimePreview> anime = new ArrayList<>();
                for(final Common.Node<TopLevelObject.Anime> iterator : response.data)
                    anime.add(asAnimePreview(MyAnimeListImpl.this, iterator.node));
                return anime;
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
                fields == null ? Call.GetAnime.fields : asStringListEncoded(fields),
                false)
            .execute()
        ));
    }

    @Override
    public final AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType){
        return new AnimeRankingQuery(service, rankingType) {

            @Override
            public final List<AnimeRanking> search(){
                final Call.GetAnimeRanking response = handleResponse(
                    () -> service.getAnimeRanking(
                        auth,
                        rankingType.field(),
                        limit,
                        offset,
                        fields == null ? Call.GetAnime.fields : asStringListEncoded(fields),
                        false)
                    .execute()
                );
                if(response == null) return null;

                final List<AnimeRanking> anime = new ArrayList<>();
                for(final SubLevelObject.Ranking<TopLevelObject.Anime> iterator : response.data)
                    anime.add(asAnimeRanking(MyAnimeListImpl.this, iterator));
                return anime;
            }

        };
    }

    @Override
    public final AnimeSeasonQuery getAnimeSeason(final int year, final Season season){
        return new AnimeSeasonQuery(service, year, season) {

            @Override
            public final List<AnimePreview> search(){
                final Call.GetSeasonalAnime response = handleResponse(
                    () -> service.getAnimeSeason(
                        auth,
                        year,
                        season.field(),
                        sort.field(),
                        limit,
                        offset,
                        fields == null ? Call.GetAnime.fields : asStringListEncoded(fields),
                        false)
                    .execute()
                );
                if(response == null) return null;

                final List<AnimePreview> anime = new ArrayList<>();
                for(final Common.Node<TopLevelObject.Anime> iterator : response.data)
                    anime.add(asAnimePreview(MyAnimeListImpl.this, iterator.node));
                return anime;
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
                            status.field(),
                            rewatching,
                            score,
                            watchedEpisodes,
                            priority,
                            timesRewatched,
                            rewatchValue,
                            asStringListEncoded(tags),
                            comments)
                    .execute()
                );
                if(response == null) return null;

                return asUserAnimeListStatus(MyAnimeListImpl.this, response, null);
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
    public final UserAnimeListQuery getUserAnimeListing(final String username){
        return new UserAnimeListQuery(service, username) {

            @Override
            public final List<UserAnimeListStatus> search(){
                final Call.GetUserAnimeList response = handleResponse(
                    () -> service.getUserAnimeListing(
                        auth,
                        username,
                        status.field(),
                        sort.field(),
                        limit,
                        offset,
                        false)
                    .execute()
                );
                if(response == null) return null;

                final List<UserAnimeListStatus> anime = new ArrayList<>();
                for(final SubLevelObject.ListEdge<TopLevelObject.Anime, Call.UpdateUserAnimeList> iterator : response.data)
                    anime.add(asUserAnimeListStatus(MyAnimeListImpl.this, iterator.list_status, asAnimePreview(MyAnimeListImpl.this, iterator.node)));
                return anime;
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
    public final List<ForumTopic> getForumTopicDetails(final long id){
        return getForumTopicDetails(id, -1, -1);
    }

    @Override
    public final List<ForumTopic> getForumTopicDetails(final long id, final int limit){
        return getForumTopicDetails(id, limit, -1);
    }

    @Override
    public final List<ForumTopic> getForumTopicDetails(final long id, final int limit, final int offset){
        final Call.GetForumTopicDetail response = handleResponse(
            () -> service.getForumBoard(
                auth,
                id,
                limit,
                offset)
            .execute()
        );
        if(response == null) return null;

        final List<ForumTopic> topics = new ArrayList<>();
        for(final TopLevelObject.ForumTopicData iterator : response.data)
            topics.add(asForumTopic(MyAnimeListImpl.this, iterator));
        return topics;
    }

    @Override
    public final ForumSearchQuery getForumTopics(){
        return new ForumSearchQuery(service) {

            @Override
            public final List<ForumTopicDetail> search(){
                final Call.GetForumTopics response = handleResponse(
                    () -> service.getForumTopics(
                        auth,
                        (long) boardId,
                        (long) subboardId,
                        limit,
                        offset,
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

        };
    }

    @Override
    public final MangaSearchQuery getManga(){
        return new MangaSearchQuery(service) {

            @Override
            public final List<MangaPreview> search(){
                final Call.GetMangaList response = handleResponse(
                    () -> service.getManga(
                        auth,
                        query,
                        limit,
                        offset,
                        fields == null ? Call.GetManga.fields : asStringListEncoded(fields),
                        false)
                    .execute()
                );
                if(response == null) return null;

                final List<MangaPreview> manga = new ArrayList<>();
                for(final Common.Node<TopLevelObject.Manga> iterator : response.data)
                    manga.add(asMangaPreview(MyAnimeListImpl.this, iterator.node));
                return manga;
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
                fields == null ? Call.GetManga.fields : asStringListEncoded(fields),
                false)
            .execute()
        ));
    }

    @Override
    public final MangaRankingQuery getMangaRanking(final MangaRankingType rankingType){
        return new MangaRankingQuery(service, rankingType) {

            @Override
            public final List<MangaRanking> search(){
                final Call.GetMangaRanking response = handleResponse(
                    () -> service.getMangaRanking(
                        auth,
                        rankingType.field(),
                        limit,
                        offset,
                        fields == null ? Call.GetAnime.fields : asStringListEncoded(fields),
                        false)
                    .execute()
                );
                if(response == null) return null;

                final List<MangaRanking> manga = new ArrayList<>();
                for(final SubLevelObject.Ranking<TopLevelObject.Manga> iterator : response.data)
                    manga.add(asMangaRanking(MyAnimeListImpl.this, iterator));
                return manga;
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
                            status.field(),
                            rereading,
                            score,
                            volumesRead,
                            chaptersRead,
                            priority,
                            timesReread,
                            rereadValue,
                            asStringListEncoded(tags),
                            comments)
                    .execute()
                );
                if(response == null) return null;

                return asUserMangaListStatus(MyAnimeListImpl.this, response, null);
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
    public final UserMangaListQuery getUserMangaListing(final String username){
        return new UserMangaListQuery(service, username) {

            @Override
            public final List<UserMangaListStatus> search(){
                final Call.GetUserMangaList response = handleResponse(
                    () -> service.getUserMangaListing(
                        auth,
                        username,
                        status.field(),
                        sort.field(),
                        limit,
                        offset,
                        false)
                    .execute()
                );
                if(response == null) return null;

                final List<UserMangaListStatus> manga = new ArrayList<>();
                for(final SubLevelObject.ListEdge<TopLevelObject.Manga, Call.UpdateUserMangaList> iterator : response.data)
                    manga.add(asUserMangaListStatus(MyAnimeListImpl.this, iterator.list_status, asMangaPreview(MyAnimeListImpl.this, iterator.node)));
                return manga;
            }

        };
    }

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
                username,
                fields == null ? Call.GetUserInformation.fields : asStringListEncoded(fields),
                false)
            .execute()
        ));
    }

    //

    private <R> R handleResponse(final ExceptionSupplier<Response<R>, IOException> supplier){
        try{
            final Response<R> response = supplier.get();
            if(response.code() != HttpURLConnection.HTTP_OK){
                 System.out.println(response.code());
                System.out.println(response.toString());
            }
            switch(response.code()){
                case HttpURLConnection.HTTP_OK:
                    return response.body();
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    return null; // todo
                case HttpURLConnection.HTTP_FORBIDDEN:
                    return null; // todo
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return null;
            }
        }catch(final IOException e){ // client side failure
            e.printStackTrace();
        }
        return null;
    }

    private interface ExceptionSupplier<T,E extends Exception>{

        T get() throws E;

    }

    //

    private static String asStringListEncoded(final List<String> fields){
        return asStringListEncoded(fields.toArray(new String[0]));
    }

    private static String asStringListEncoded(final String[] fields){
        if(fields != null && fields.length > 0){
            final StringBuilder SB = new StringBuilder();
            for(final String field : fields)
                if(!field.isBlank())
                    SB
                        .append(URLEncoder.encode(field, StandardCharsets.UTF_8))
                        .append(',');

            final String str = SB.toString();
            if(!str.isBlank())
                return str.substring(0, str.length() -1);
        }
        return null;
    }

}
