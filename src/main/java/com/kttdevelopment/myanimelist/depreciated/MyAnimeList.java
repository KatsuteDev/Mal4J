package com.kttdevelopment.myanimelist.depreciated;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.user.User;

import java.util.List;

// interface for clients
public abstract class MyAnimeList {

// anime
    /*
    // https://myanimelist.net/apiconfig/references/api/v2#operation/anime_get
    public abstract List<Anime> getAnime(final String search);

    public abstract List<Anime> getAnime(final String search, final int limit);

    public abstract List<Anime> getAnime(final String search, final int limit, final int offset);

    // https://myanimelist.net/apiconfig/references/api/v2#operation/anime_ranking_get
    public abstract List<Anime> getAnime(final String search, final AnimeRankingEnum ranking);

    public abstract List<Anime> getAnime(final String search, final AnimeRankingEnum ranking, final int limit);

    public abstract List<Anime> getAnime(final String search, final AnimeRankingEnum ranking, final int limit, final int offset);

    // https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_get
    public abstract Anime getAnime(final int anime_id);

    // https://myanimelist.net/apiconfig/references/api/v2#operation/anime_season_year_season_get
    public abstract List<Anime> getSeasonalAnime(final int year, final Season season);

    public abstract List<Anime> getSeasonalAnime(final int year, final Season season, final int limit);

    public abstract List<Anime> getSeasonalAnime(final int year, final Season season, final int limit, final int offset);

    public abstract List<Anime> getSeasonalAnime(final int year, final Season season, final Sort sort);

    public abstract List<Anime> getSeasonalAnime(final int year, final Season season, final Sort sort, final int limit);

    public abstract List<Anime> getSeasonalAnime(final int year, final Season season, final Sort sort, final int limit, final int offset);

    // https://myanimelist.net/apiconfig/references/api/v2#operation/anime_season_year_season_get
    public abstract List<Anime> getSuggestedAnime();

    public abstract List<Anime> getSuggestedAnime(final int limit);

    public abstract List<Anime> getSuggestedAnime(final int limit, final int offset);

    // https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get
    public abstract AnimeList getAnimeList();

    public abstract AnimeList getAnimeList(final String username);

    public abstract AnimeList getAnimeList(final String username, final AnimeStatus status, final Sort sort, final int limit, final int offset);

    public abstract AnimeList getAnimeList(final String username, final AnimeStatus status, final int limit, final int offset);

    public abstract AnimeList getAnimeList(final String username, final Sort sort, final int limit, final int offset);

// forum

    // https://myanimelist.net/apiconfig/references/api/v2#tag/forum
    public abstract List<ForumCategory> getForumBoards();

    // https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get
    public abstract List<ForumCategory> getForumBoards(final int topic_id);

    public abstract List<ForumCategory> getForumBoards(final int topic_id, final int limit);

    public abstract List<ForumCategory> getForumBoards(final int topic_id, final int limit, final int offset);

    // https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get
    // todo

// manga

    // https://myanimelist.net/apiconfig/references/api/v2#tag/manga
    public abstract List<Manga> getManga(final String search);

    public abstract List<Manga> getManga(final String search, final int limit);

    public abstract List<Manga> getManga(final String search, final int limit, final int offset);

    public abstract List<Manga> getManga(final String search, final MangaRankingEnum ranking);

    public abstract List<Manga> getManga(final String search, final MangaRankingEnum ranking, final int limit);

    public abstract List<Manga> getManga(final String search, final MangaRankingEnum ranking, final int limit, final int offset);

    // https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get
    public abstract Manga getManga(final int manga_id);

    public abstract MangaList getMangaList();

    public abstract MangaList getMangaList(final String username);

    public abstract MangaList getMangaList(final String username, final MangaStatus status, final Sort sort, final int limit, final int offset);

    public abstract MangaList getMangaList(final String username, final MangaStatus status, final int limit, final int offset);

    public abstract MangaList getMangaList(final String username, final Sort sort, final int limit, final int offset);

// user

    public abstract User getMyself();

// mal

    public static MyAnimeList create(final String client_id, final int auth_port){
        return new MyAnimeListImpl(client_id, auth_port);
    }

    */

}
