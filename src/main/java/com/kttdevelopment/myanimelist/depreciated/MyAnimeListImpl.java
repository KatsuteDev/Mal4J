package com.kttdevelopment.myanimelist.depreciated;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import com.kttdevelopment.myanimelist.depreciated.*;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.depreciated.net.HttpRequestMethod;
import com.kttdevelopment.myanimelist.property.Season;
import com.kttdevelopment.myanimelist.property.Sort;
import com.kttdevelopment.myanimelist.user.User;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

final class MyAnimeListImpl extends MyAnimeList {

    private transient final String oauth;

    private final MyAnimeListClientDep client;

    public MyAnimeListImpl(final String client_id, final int auth_port){
        try{
            this.oauth = new MyAnimeListAuthenticator(client_id, auth_port).authenticate();

            client = new MyAnimeListClientDep(oauth);
        }catch(URISyntaxException | IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public final List<Anime> getAnime(final String search){
        return getAnime(search, 100, 0);
    }

    @Override
    public final List<Anime> getAnime(final String search, final int limit){
        return getAnime(search, limit, 0);
    }

    @Override
    public final List<Anime> getAnime(final String search, final int limit, final int offset){
        return null; // todo
    }

    @Override
    public final List<Anime> getAnime(final String search, final AnimeRankingEnum ranking){
        return getAnime(search, ranking, 100, 0);
    }

    @Override
    public final List<Anime> getAnime(final String search, final AnimeRankingEnum ranking, final int limit){
        return getAnime(search, ranking, limit, 0);
    }

    @Override
    public final List<Anime> getAnime(final String search, final AnimeRankingEnum ranking, final int limit, final int offset){
        return null; // todo
    }

    @Override
    public final Anime getAnime(final int anime_id){
        final JSONObject obj = client.request("anime/" + anime_id, HttpRequestMethod.GET);
        System.out.println(obj);

        return null; // todo
    }

    @Override
    public final List<Anime> getSeasonalAnime(final int year, final Season season){
        return getSeasonalAnime(year, season, 100, 0);
    }

    @Override
    public final List<Anime> getSeasonalAnime(final int year, final Season season, final int limit){
        return getSeasonalAnime(year, season, limit, 0);
    }

    @Override
    public final List<Anime> getSeasonalAnime(final int year, final Season season, final int limit, final int offset){
        return null; // todo
    }

    @Override
    public final List<Anime> getSeasonalAnime(final int year, final Season season, final Sort sort){
        return getSeasonalAnime(year, season, sort, 100, 0);
    }

    @Override
    public final List<Anime> getSeasonalAnime(final int year, final Season season, final Sort sort, final int limit){
        return getSeasonalAnime(year, season, sort, limit, 0);
    }

    @Override
    public final List<Anime> getSeasonalAnime(final int year, final Season season, final Sort sort, final int limit, final int offset){
        return null; // todo
    }

    @Override
    public final List<Anime> getSuggestedAnime(){
        return getSuggestedAnime(100, 0);
    }

    @Override
    public final List<Anime> getSuggestedAnime(final int limit){
        return getSuggestedAnime(limit, 0);
    }

    @Override
    public final List<Anime> getSuggestedAnime(final int limit, final int offset){
        return null; // todo
    }

    @Override
    public final AnimeList getAnimeList(){
        return getAnimeList("@me");
    }

    @Override
    public final AnimeList getAnimeList(final String username){
        return null; // todo
    }

    @Override
    public final AnimeList getAnimeList(final String username, final AnimeStatus status, final Sort sort, final int limit, final int offset){
        return null;
    }

    @Override
    public final AnimeList getAnimeList(final String username, final AnimeStatus status, final int limit, final int offset){
        return null;
    }

    @Override
    public final AnimeList getAnimeList(final String username, final Sort sort, final int limit, final int offset){
        return null;
    }

    @Override
    public final List<ForumCategory> getForumBoards(){
        return null;
    }

    @Override
    public final List<ForumCategory> getForumBoards(final int topic_id){
        return null;
    }

    @Override
    public final List<ForumCategory> getForumBoards(final int topic_id, final int limit){
        return getForumBoards(topic_id, limit, 0);
    }

    @Override
    public final List<ForumCategory> getForumBoards(final int topic_id, final int limit, final int offset){
        return null; // todo
    }

    @Override
    public final List<Manga> getManga(final String search){
        return getManga(search, 100, 0);
    }

    @Override
    public final List<Manga> getManga(final String search, final int limit){
        return getManga(search, limit, 0);
    }

    @Override
    public final List<Manga> getManga(final String search, final int limit, final int offset){
        return null; // todo
    }

    @Override
    public final List<Manga> getManga(final String search, final MangaRankingEnum ranking){
        return getManga(search, ranking, 100, 0);
    }

    @Override
    public final List<Manga> getManga(final String search, final MangaRankingEnum ranking, final int limit){
        return getManga(search, ranking, limit, 0);
    }

    @Override
    public final List<Manga> getManga(final String search, final MangaRankingEnum ranking, final int limit, final int offset){
        return null; // todo
    }

    @Override
    public final Manga getManga(final int manga_id){
        return null;
    }

    @Override
    public final MangaList getMangaList(){
        return getMangaList("@me");
    }

    @Override
    public final MangaList getMangaList(final String username){
        return null; // todo
    }

    @Override
    public final MangaList getMangaList(final String username, final MangaStatus status, final Sort sort, final int limit, final int offset){
        return null;
    }

    @Override
    public final MangaList getMangaList(final String username, final MangaStatus status, final int limit, final int offset){
        return null;
    }

    @Override
    public final MangaList getMangaList(final String username, final Sort sort, final int limit, final int offset){
        return null;
    }

    @Override
    public final User getMyself(){
        return null;
    }

}
