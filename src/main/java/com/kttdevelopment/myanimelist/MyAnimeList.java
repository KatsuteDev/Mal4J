package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.auth.MyAnimeListAuthenticator;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.manga.property.MangaSort;
import com.kttdevelopment.myanimelist.user.User;

import java.io.IOException;
import java.util.List;

/**
 * MyAnimeList API.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MyAnimeList {

    public static MyAnimeList withOAuthToken(final String token){
        return new MyAnimeListImpl(token);
    }

    public static MyAnimeList withClientId(final String client_id, final int server_port) throws IOException{
        return new MyAnimeListImpl(new MyAnimeListAuthenticator(client_id, server_port).getToken().getToken());
    }

    // anime

    public abstract List<AnimePreview> getAnime();

    public abstract List<AnimePreview> getAnime(final String search);

    public abstract List<AnimePreview> getAnime(final String search, final int limit);

    public abstract List<AnimePreview> getAnime(final String search, final int limit, final int offset);

    public abstract List<AnimePreview> getAnime(final String search, final String[] fields);

    public abstract List<AnimePreview> getAnime(final String search, final int limit, final int offset, final String[] fields);

    public abstract Anime getAnime(final long id);

    public abstract Anime getAnime(final long id, final String[] fields);

    // anime ranking

    public abstract List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType);

    public abstract List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final String[] fields);

    public abstract List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit);

    public abstract List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit, final int offset);

    public abstract List<AnimeRanking> getAnimeRanking(final AnimeRankingType rankingType, final int limit, final int offset, final String[] fields);

    // anime season

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final String[] fields);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final String[] fields);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final int offset);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final int limit, final int offset, final String[] fields);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final String[] fields);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final String[] fields);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final int offset);

    public abstract List<AnimePreview> getAnimeSeason(final int year, final Season season, final AnimeRankingType sort, final int limit, final int offset, final String[] fields);

    // anime list

    public abstract AnimeListing updateAnimeListing(); // todo

    public abstract void deleteAnimeListing(final long id);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final int limit);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final int limit, final int offset);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status, final int limit);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status, final int limit, final int offset);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, AnimeSort sort);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, AnimeSort sort, final int limit);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, AnimeSort sort, final int limit, final int offset);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, AnimeSort sort, final AnimeStatus status);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, AnimeSort sort, final AnimeStatus status, final int limit);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, AnimeSort sort, final AnimeStatus status, final int limit, final int offset);

    // forum

    public abstract List<ForumCategory> getForumBoards();

    // forum board

    public abstract ForumCategory getForumBoard(final long id);

    public abstract ForumCategory getForumBoard(final long id, final int limit);

    public abstract ForumCategory getForumBoard(final long id, final int limit, final int offset);

    // forum topics

    public abstract List<ForumTopic> getForumTopics(); // todo

    // manga

    public abstract List<MangaPreview> getManga();

    public abstract List<MangaPreview> getManga(final String search);

    public abstract List<MangaPreview> getManga(final String search, final int limit);

    public abstract List<MangaPreview> getManga(final String search, final int limit, final int offset);

    public abstract List<MangaPreview> getManga(final String search, final String[] fields);

    public abstract List<MangaPreview> getManga(final String search, final int limit, final int offset, final String[] fields);

    public abstract Manga getManga(final long id);

    public abstract Manga getManga(final long id, final String[] fields);

    // manga ranking

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType);

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final String[] fields);

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit);

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final int offset);

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final int offset, final String[] fields);

    // manga list

    public abstract MangaListing updateMangaListing(); // todo

    public abstract void deleteMangaListing(final long id);

    public abstract List<UserMangaListing> getUserMangaListing(final String username);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final int limit);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final int limit, final int offset);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status, final int limit);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status, final int limit, final int offset);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, MangaSort sort);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, MangaSort sort, final int limit);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, MangaSort sort, final int limit, final int offset);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, MangaSort sort, final MangaStatus status);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, MangaSort sort, final MangaStatus status, final int limit);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, MangaSort sort, final MangaStatus status, final int limit, final int offset);

    // user

    public abstract User getMyself();

    public abstract User getMyself(final String[] fields);

    public abstract User getUser(final String username);

    public abstract User getUser(final String username, final String[] fields);

}
