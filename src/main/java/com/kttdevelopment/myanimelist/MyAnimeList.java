package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.RankingType;
import com.kttdevelopment.myanimelist.user.User;

import java.util.List;

/**
 * MyAnimeList API.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MyAnimeList {

    // anime

    public abstract AnimePreview getAnime();

    public abstract List<AnimePreview> getAnime(final String search);

    public abstract List<AnimePreview> getAnime(final String search, final int limit);

    public abstract List<AnimePreview> getAnime(final String search, final int limit, final int offset);

    public abstract List<AnimePreview> getAnime(final String search, final String[] fields);

    public abstract List<AnimePreview> getAnime(final String search, final int limit, final int offset, final String[] fields);

    public abstract Anime getAnime(final int id);

    public abstract Anime getAnime(final int id, final String[] fields);

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

    public abstract void deleteAnimeListing(final int id);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final int limit);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final int limit, final int offset);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status);

    // todo: with sort status

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status, final int limit);

    public abstract List<UserAnimeListing> getUserAnimeListing(final String username, final AnimeStatus status, final int limit, final int offset);

    // forum

    public abstract List<ForumCategory> getForumBoards();

    // forum board

    public abstract ForumCategory getForumBoard(final int id);

    public abstract ForumCategory getForumBoard(final int id, final int limit);

    public abstract ForumCategory getForumBoard(final int id, final int limit, final int offset);

    // forum topics

    public abstract List<ForumTopic> getForumTopics(); // todo

    // manga

    public abstract List<MangaPreview> getManga();

    public abstract List<MangaPreview> getManga(final String search);

    public abstract List<MangaPreview> getManga(final String search, final int limit);

    public abstract List<MangaPreview> getManga(final String search, final int limit, final int offset);

    public abstract List<MangaPreview> getManga(final String search, final String[] fields);

    public abstract List<MangaPreview> getManga(final String search, final int limit, final int offset, final String[] fields);

    public abstract MangaPreview getManga(final int id);

    public abstract MangaPreview getManga(final int id, final String[] fields);

    // manga ranking

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType);

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final String[] fields);

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit);

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final int offset);

    public abstract List<MangaRanking> getMangaRanking(final MangaRankingType rankingType, final int limit, final int offset, final String[] fields);

    // manga list

    public abstract MangaListing updateMangaListing(); // todo

    public abstract void deleteMangaListing(final int id);

    public abstract List<UserMangaListing> getUserMangaListing(final String username);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final int limit);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final int limit, final int offset);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status);

    // todo: with sort status

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status, final int limit);

    public abstract List<UserMangaListing> getUserMangaListing(final String username, final MangaStatus status, final int limit, final int offset);

    // user

    public abstract User getMyself();

    public abstract User getMyself(final String[] fields);

    public abstract User getUser(final String username);

    public abstract User getUser(final String username, final String[] fields);

}
