package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.auth.MyAnimeListAuthenticator;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.manga.Manga;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;
import com.kttdevelopment.myanimelist.query.*;
import com.kttdevelopment.myanimelist.user.User;

import java.io.IOException;
import java.util.List;

public abstract class MyAnimeList {

    public static MyAnimeList withOAuthToken(final String token){
        return new MyAnimeListImpl(token);
    }

    public static MyAnimeList withClientId(final String client_id, final int server_port) throws IOException{
        return new MyAnimeListImpl(new MyAnimeListAuthenticator(client_id, server_port).getAccessToken().getToken());
    }

    // anime

    public abstract AnimeSearchQuery getAnime();

    public abstract Anime getAnime(final long id);

    public abstract Anime getAnime(final long id, final String... fields);

    // anime ranking

    public abstract AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType);

    // anime season

    public abstract AnimeSeasonQuery getAnimeSeason(final int year, final Season season);

    // anime suggestions

    public abstract AnimeSuggestionQuery getAnimeSuggestions();

    // anime list

    public abstract AnimeListUpdate updateAnimeListing(final long id);

    public abstract void deleteAnimeListing(final long id);

    public abstract UserAnimeListQuery getUserAnimeListing();

    public abstract UserAnimeListQuery getUserAnimeListing(final String username);

    // forum

    public abstract List<ForumCategory> getForumBoards();

    //

    public abstract List<ForumTopic> getForumTopicDetails(final long id);

    public abstract List<ForumTopic> getForumTopicDetails(final long id, final int limit);

    public abstract List<ForumTopic> getForumTopicDetails(final long id, final int limit, final int offset);

    //

    public abstract ForumSearchQuery getForumTopics();

    // manga

    public abstract MangaSearchQuery getManga();

    public abstract Manga getManga(final long id);

    public abstract Manga getManga(final long id, final String... fields);

    // manga ranking

    public abstract MangaRankingQuery getMangaRanking(final MangaRankingType rankingType);

    // manga list

    public abstract MangaListUpdate updateMangaListing(final long id);

    public abstract void deleteMangaListing(final long id);

    public abstract UserMangaListQuery getUserMangaListing();

    public abstract UserMangaListQuery getUserMangaListing(final String username);

    // user

    public abstract User getMyself();

    public abstract User getMyself(final String[] fields);

    public abstract User getUser(final String username);

    public abstract User getUser(final String username, final String... fields);

}
