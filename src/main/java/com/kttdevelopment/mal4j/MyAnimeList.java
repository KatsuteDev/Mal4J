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

import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.anime.property.AnimeRankingType;
import com.kttdevelopment.mal4j.anime.property.time.Season;
import com.kttdevelopment.mal4j.forum.ForumCategory;
import com.kttdevelopment.mal4j.forum.ForumTopicDetail;
import com.kttdevelopment.mal4j.manga.Manga;
import com.kttdevelopment.mal4j.manga.property.MangaRankingType;
import com.kttdevelopment.mal4j.query.*;
import com.kttdevelopment.mal4j.user.User;
import com.kttdevelopment.mal4j.user.property.*;

import java.io.UncheckedIOException;
import java.util.List;

/**
 * The MyAnimeList API interface, used to execute requests with the API from Java.
 * <br><br>
 * The {@link MyAnimeList} class can be created by authenticating with either:
 * <ul>
 *     <li>An oauth token using {@link #withOAuthToken(String)}</li>
 *     <li>An authorization code and client id using {@link #withAuthorization(MyAnimeListAuthenticator)}.</li>
 * </ul>
 *
 * @since 1.0.0
 * @version 2.0.0
 * @author Ktt Development
 */
public abstract class MyAnimeList {

    MyAnimeList(){ }

    /**
     * Creates an interface with an OAuth token. Note that this method does not support {@link #refreshOAuthToken()}.
     *
     * @param token OAuth token, Ex: 'Bearer oauth2token'
     * @throws NullPointerException if token is null
     * @throws IllegalArgumentException if token doesn't start with 'Bearer'
     *
     * @return MyAnimeList
     *
     * @see #withAuthorization(MyAnimeListAuthenticator)
     * @since 1.0.0
     */
    public static MyAnimeList withOAuthToken(final String token){
        return new MyAnimeListImpl(token);
    }

    /**
     * Creates an interface with an authenticator.
     *
     * @param authenticator authenticator
     * @return MyAnimeList
     * @throws NullPointerException if authenticator is null
     *
     * @see #withOAuthToken(String)
     * @see #refreshOAuthToken()
     * @see MyAnimeListAuthenticator
     * @since 1.0.0
     */
    public static MyAnimeList withAuthorization(final MyAnimeListAuthenticator authenticator){
        return new MyAnimeListImpl(authenticator);
    }

    /**
     * Refreshes the OAuth token. Only works with {@link #withAuthorization(MyAnimeListAuthenticator)}.
     *
     * @throws UnsupportedOperationException if the object was not created with an authenticator
     *
     * @since 1.0.0
     */
    public abstract void refreshOAuthToken();

    // anime

    /**
     * Returns an Anime search query.
     *
     * @return Anime search
     *
     * @see AnimeSuggestionQuery
     * @see AnimeSuggestionQuery#search()
     * @see com.kttdevelopment.mal4j.anime.AnimePreview
     * @since 1.0.0
     */
    public abstract AnimeSearchQuery getAnime();

    /**
     * Returns the full Anime details given an ID.
     *
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     * @param id Anime id
     * @return Anime
     *
     * @see Anime
     * @see #getAnime(long)
     * @since 1.0.0
     */
    public abstract Anime getAnime(final long id);

    /**
     * Returns Anime details requested in the fields given an ID.
     *
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     * @param id Anime id
     * @param fields a comma separated string or a string array of the fields that should be returned
     * @return Anime
     *
     * @see Anime
     * @see #getAnime()
     * @see Fields#anime
     * @since 1.0.0
     */
    public abstract Anime getAnime(final long id, final String... fields);

    // anime ranking

    /**
     * Returns an Anime ranking query.
     *
     * @param rankingType ranking type
     * @return ranked Anime
     * @throws NullPointerException if ranking type is null
     *
     * @see AnimeRankingQuery
     * @see AnimeRankingQuery#search()
     * @see com.kttdevelopment.mal4j.anime.AnimeRanking
     * @see AnimeRankingType
     * @since 1.0.0
     */
    public abstract AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType);

    // anime season

    /**
     * Returns an Anime season query.
     *
     * @param year year
     * @param season airing season
     * @return seasonal Anime
     * @throws NullPointerException if season is null
     *
     * @see AnimeSeasonQuery
     * @see AnimeSeasonQuery#search()
     * @see com.kttdevelopment.mal4j.anime.AnimePreview
     * @see Season
     * @since 1.0.0
     */
    public abstract AnimeSeasonQuery getAnimeSeason(final int year, final Season season);

    // anime suggestions

    /**
     * Returns an Anime suggestions query.
     *
     * @return suggested Anime
     *
     * @see AnimeSuggestionQuery
     * @see AnimeSuggestionQuery#search()
     * @see com.kttdevelopment.mal4j.anime.AnimePreview
     * @since 1.0.0
     */
    public abstract AnimeSuggestionQuery getAnimeSuggestions();

    // anime list

    /**
     * Returns an Anime listing updater.
     *
     * @param id Anime id
     * @return Anime list updater
     *
     * @see AnimeListUpdate
     * @see AnimeListUpdate#update()
     * @see #deleteAnimeListing(long)
     * @see #getUserAnimeListing()
     * @see #getUserAnimeListing(String)
     * @since 1.0.0
     */
    public abstract AnimeListUpdate updateAnimeListing(final long id);

    /**
     * Removes an Anime listing.
     *
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     * @param id Anime id
     *
     * @see #updateAnimeListing(long)
     * @see #getUserAnimeListing()
     * @see #getUserAnimeListing(String)
     * @since 1.0.0
     */
    public abstract void deleteAnimeListing(final long id);

    /**
     * Returns a list query for your Anime listings.
     *
     * @return Anime listing
     *
     * @see UserAnimeListQuery
     * @see UserAnimeListQuery#search()
     * @see #updateAnimeListing(long)
     * @see #deleteAnimeListing(long)
     * @see #getUserAnimeListing(String)
     * @since 1.0.0
     */
    public abstract UserAnimeListQuery getUserAnimeListing();

    /**
     * Returns a list query for a user's Anime listings.
     *
     * @param username username
     * @return Anime listing
     * @throws NullPointerException if username is null
     *
     * @see UserAnimeListQuery
     * @see UserAnimeListQuery#search()
     * @see #updateAnimeListing(long)
     * @see #deleteAnimeListing(long)
     * @see #getUserAnimeListing()
     * @since 1.0.0
     */
    public abstract UserAnimeListQuery getUserAnimeListing(final String username);

    // forum

    /**
     * Returns the top level forum boards.
     *
     * @return forum boards
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see ForumCategory
     * @since 1.0.0
     */
    public abstract List<ForumCategory> getForumBoards();

    //

    /**
     * Returns a forum topic.
     *
     * @param id forum topic id
     * @return forum topic
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see #getForumTopicDetail(long, Integer)
     * @see #getForumTopicDetail(long, Integer, Integer)
     * @see #getForumTopicDetailPostQuery(long)
     * @since 1.0.0
     */
    public abstract ForumTopicDetail getForumTopicDetail(final long id);

    /**
     * Returns a forum topic.
     *
     * @param id forum topic id
     * @param limit post limit
     * @return forum topic
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see #getForumTopicDetail(long)
     * @see #getForumTopicDetail(long, Integer, Integer)
     * @see #getForumTopicDetailPostQuery(long)
     * @since 1.0.0
     */
    public abstract ForumTopicDetail getForumTopicDetail(final long id, final Integer limit);

    /**
     * Returns a forum topic.
     *
     * @param id forum topic id
     * @param limit post limit
     * @param offset post offset
     * @return forum topic
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see #getForumTopicDetail(long)
     * @see #getForumTopicDetail(long, Integer)
     * @see #getForumTopicDetailPostQuery(long)
     * @since 1.0.0
     */
    public abstract ForumTopicDetail getForumTopicDetail(final long id, final Integer limit, final Integer offset);

    /**
     * Returns a forum topic post query.
     *
     * @param id forum topic id
     * @return post search
     *
     * @see #getForumTopicDetail(long)
     * @see #getForumTopicDetail(long, Integer)
     * @see #getForumTopicDetail(long, Integer, Integer)
     * @see ForumTopicDetailPostQuery
     * @since 2.0.0
     */
    public abstract ForumTopicDetailPostQuery getForumTopicDetailPostQuery(final long id);

    //

    /**
     * Returns a forum topic search query.
     *
     * @return forums
     *
     * @see ForumSearchQuery
     * @see ForumSearchQuery#search()
     * @since 1.0.0
     */
    public abstract ForumSearchQuery getForumTopics();

    // manga

    /**
     * Returns a Manga search query.
     *
     * @return Manga search
     *
     * @see MangaSearchQuery
     * @see MangaSearchQuery#search()
     * @see com.kttdevelopment.mal4j.manga.MangaPreview
     * @since 1.0.0
     */
    public abstract MangaSearchQuery getManga();

    /**
     * Returns the full Manga details given an ID.
     *
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     * @param id Manga id
     * @return Manga
     *
     * @see Manga
     * @see #getManga(long)
     * @since 1.0.0
     */
    public abstract Manga getManga(final long id);

    /**
     * Returns Manga details requested in the fields given an ID.
     *
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     * @param id Manga id
     * @param fields a comma separated string or a string array of the fields that should be returned
     * @return Manga
     *
     * @see Manga
     * @see #getManga()
     * @see Fields#manga
     * @since 1.0.0
     */
    public abstract Manga getManga(final long id, final String... fields);

    // manga ranking

    /**
     * Returns a Manga ranking query.
     *
     * @param rankingType ranking type
     * @return ranked Manga
     * @throws NullPointerException if ranking type is null
     *
     * @see MangaRankingQuery
     * @see MangaRankingQuery#search()
     * @see com.kttdevelopment.mal4j.manga.MangaRanking
     * @see MangaRankingType
     * @since 1.0.0
     */
    public abstract MangaRankingQuery getMangaRanking(final MangaRankingType rankingType);

    // manga list

    /**
     * Returns a Manga listing updater.
     *
     * @param id Manga id
     * @return Manga list updater
     *
     * @see MangaListUpdate
     * @see MangaListUpdate#update()
     * @see #deleteMangaListing(long)
     * @see #getUserMangaListing()
     * @see #getUserMangaListing(String)
     * @since 1.0.0
     */
    public abstract MangaListUpdate updateMangaListing(final long id);

    /**
     * Removes a Manga listing.
     *
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     * @param id Manga id
     *
     * @see #updateMangaListing(long)
     * @see #getUserMangaListing()
     * @see #getUserMangaListing(String)
     * @since 1.0.0
     */
    public abstract void deleteMangaListing(final long id);

    /**
     * Returns a list query for your Manga listings.
     *
     * @return Manga listing
     *
     * @see UserMangaListQuery
     * @see UserMangaListQuery#search()
     * @see #updateMangaListing(long)
     * @see #deleteMangaListing(long)
     * @see #getUserMangaListing(String)
     * @since 1.0.0
     */
    public abstract UserMangaListQuery getUserMangaListing();

    /**
     * Returns a list query for a user's Manga listings.
     *
     * @param username username
     * @return Manga listing
     * @throws NullPointerException if username is null
     *
     * @see UserMangaListQuery
     * @see UserMangaListQuery#search()
     * @see #updateMangaListing(long)
     * @see #deleteMangaListing(long)
     * @see #getUserMangaListing()
     * @since 1.0.0
     */
    public abstract UserMangaListQuery getUserMangaListing(final String username);

    // user

    /**
     * Returns the authenticated user.
     *
     * @return user
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see User
     * @see #getMyself(String[])
     * @since 1.0.0
     */
    public abstract User getMyself();

    /**
     * Returns the authenticated user.
     *
     * @param fields a comma separated string or a string array of the fields that should be returned
     * @return user
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see User
     * @see #getMyself()
     * @see Fields#user
     * @since 1.0.0
     */
    public abstract User getMyself(final String... fields);

    /**
     * Returns a user given their username.
     *
     * @param username username
     * @return user
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     * @throws NullPointerException if username is null
     *
     * @see User
     * @see #getUser(String, String...)
     * @since 1.0.0
     */
    public abstract User getUser(final String username);

    /**
     * Returns a user given their username.
     *
     * @param username username
     * @param fields a comma separated string or a string array of the fields that should be returned
     * @return user
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     * @throws NullPointerException if username is null
     *
     * @see User
     * @see #getUser(String)
     * @see Fields#user
     * @since 1.0.0
     */
    public abstract User getUser(final String username, final String... fields);

}
