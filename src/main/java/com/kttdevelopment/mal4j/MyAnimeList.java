/*
 * Copyright (C) 2021-2022 Katsute <https://github.com/Katsute>
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
import com.kttdevelopment.mal4j.anime.AnimePreview;
import com.kttdevelopment.mal4j.anime.property.AnimeRankingType;
import com.kttdevelopment.mal4j.anime.property.time.Season;
import com.kttdevelopment.mal4j.forum.ForumCategory;
import com.kttdevelopment.mal4j.forum.ForumTopicDetail;
import com.kttdevelopment.mal4j.manga.Manga;
import com.kttdevelopment.mal4j.manga.MangaRanking;
import com.kttdevelopment.mal4j.manga.property.MangaRankingType;
import com.kttdevelopment.mal4j.property.ExperimentalFeature;
import com.kttdevelopment.mal4j.query.*;
import com.kttdevelopment.mal4j.user.User;

import java.util.List;

/**
 * The MyAnimeList API interface, used to execute requests with the API from Java.
 * <br><br>
 * The {@link MyAnimeList} class can be created by authenticating with either:
 * <ul>
 *     <li>A client ID using {@link #withClientID(String)}</li>
 *     <li>A token using {@link #withToken(String)}</li>
 *     <li>OAuth2 using {@link #withOAuth2(MyAnimeListAuthenticator)}.</li>
 * </ul>
 *
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public abstract class MyAnimeList {

// debug

    /**
     * Enable/disable connection debugging.
     *
     * @param debug debug
     *
     * @since 2.7.0
     */
    public static void setDebug(final boolean debug){
        Logging.setDebug(debug);
    }

// authentication

    MyAnimeList(){ }

    /**
     * Creates an interface with a client ID. Only public read operations are allowed, for write and user operations use {@link #withToken(String)} or {@link #withOAuth2(MyAnimeListAuthenticator)}. Client secret is not required if your application has one.
     *
     * @param client_id client id
     * @return MyAnimeList
     * @throws NullPointerException if client ID is null
     *
     * @see #withToken(String)
     * @see #withOAuth2(MyAnimeListAuthenticator)
     * @since 2.6.0
     */
    public static MyAnimeList withClientID(final String client_id){
        return new MyAnimeListImpl(client_id, false);
    }

    /**
     * Creates an interface with an OAuth token. Note that this method does not support {@link #refreshToken()}.
     *
     * @param token OAuth token, Ex: 'Bearer oauth2token'
     * @return MyAnimeList
     * @throws NullPointerException if token is null
     * @throws InvalidTokenException if token doesn't start with 'Bearer'
     *
     * @see #withClientID(String)
     * @see #withOAuth2(MyAnimeListAuthenticator)
     * @since 2.6.0
     */
    public static MyAnimeList withToken(final String token){
        return new MyAnimeListImpl(token, true);
    }

    /**
     * Creates an interface with an OAuth token. Note that this method does not support {@link #refreshToken()}.
     *
     * @deprecated this method has been renamed to {@link #withToken(String)} for simplicity
     * @param token OAuth token, Ex: 'Bearer oauth2token'
     * @return MyAnimeList
     * @throws NullPointerException if token is null
     * @throws InvalidTokenException if token doesn't start with 'Bearer'
     *
     * @see #withClientID(String)
     * @see #withAuthorization(MyAnimeListAuthenticator)
     * @see #withToken(String)
     * @since 1.0.0
     */
    @Deprecated
    public static MyAnimeList withOAuthToken(final String token){
        Logging.getLogger().warning("The use of this method is deprecated, please use `MyAnimeList.withToken(...)");
        return withToken(token);
    }

    /**
     * Creates an interface with an authenticator.
     *
     * @deprecated this method has been renamed to {@link #withOAuth2(MyAnimeListAuthenticator)} to reduce confusion with {@link Authorization}
     * @param authenticator authenticator
     * @return MyAnimeList
     * @throws NullPointerException if authenticator is null
     *
     * @see #withClientID(String)
     * @see #withToken(String)
     * @see #refreshToken()
     * @see MyAnimeListAuthenticator
     * @since 1.0.0
     */
    @Deprecated
    public static MyAnimeList withAuthorization(final MyAnimeListAuthenticator authenticator){
        Logging.getLogger().warning("The use of this method is deprecated, please use `MyAnimeList.withOAuth2(...)");
        return withOAuth2(authenticator);
    }

    /**
     * Creates an interface using an authenticator.
     *
     * @param authenticator authenticator
     * @return MyAnimeList
     * @throws NullPointerException if authenticator is null
     *
     * @see #withClientID(String)
     * @see #withToken(String)
     * @see #refreshToken()
     * @see MyAnimeListAuthenticator
     * @since 2.7.0
     */
    public static MyAnimeList withOAuth2(final MyAnimeListAuthenticator authenticator){
        return new MyAnimeListImpl(authenticator);
    }

    /**
     * Refreshes the OAuth token. Only works with {@link #withOAuth2(MyAnimeListAuthenticator)}.
     *
     * @throws UnsupportedOperationException if this wasn't created with an authenticator
     *
     * @since 2.6.0
     */
    public abstract void refreshToken();

    /**
     * Refreshes the OAuth token. Only works with {@link #withAuthorization(MyAnimeListAuthenticator)}.
     *
     * @deprecated this method has been renamed to {@link #refreshToken()} for simplicity
     * @throws UnsupportedOperationException if this wasn't created with an authenticator
     *
     * @since 1.0.0
     * @see #refreshToken()
     */
    @Deprecated
    public abstract void refreshOAuthToken();

// experimental

    /**
     * Enables an experimental feature.
     *
     * @param feature feature to enable
     *
     * @see ExperimentalFeature
     * @since 2.3.0
     */
    public abstract void enableExperimentalFeature(final ExperimentalFeature feature);

// anime

    /**
     * Returns an Anime search query.
     *
     * @return Anime search
     *
     * @see AnimeSuggestionQuery
     * @see AnimeSuggestionQuery#search()
     * @see AnimePreview
     * @since 1.0.0
     */
    public abstract AnimeSearchQuery getAnime();

    /**
     * Returns the full Anime details given an ID.
     *
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
     * @param id Anime id
     * @param fields a string array of the fields that should be returned
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
     * @see #getAnimeRanking(String)
     * @see AnimeRankingQuery
     * @see AnimeRankingQuery#search()
     * @see com.kttdevelopment.mal4j.anime.AnimeRanking
     * @see AnimeRankingType
     * @since 1.0.0
     */
    public abstract AnimeRankingQuery getAnimeRanking(final AnimeRankingType rankingType);

    /**
     * Returns an Anime ranking query.
     * <br>
     * It is recommended to use {@link #getAnimeRanking(AnimeRankingType)} rather than this method.
     * This method should only be used if you are using a ranking type that is missing from {@link AnimeRankingType}.
     *
     * @param rankingType ranking type API field
     * @return ranked Anime
     * @throws NullPointerException if ranking type is null
     *
     * @see #getAnimeRanking(AnimeRankingType)
     * @see AnimeRankingQuery
     * @see AnimeRankingQuery#search()
     * @see com.kttdevelopment.mal4j.anime.AnimeRanking
     * @since 2.9.0
     */
    public abstract AnimeRankingQuery getAnimeRanking(final String rankingType);

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
     * @see AnimePreview
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
     * @see AnimePreview
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

// forum board

    /**
     * Returns the top level forum boards.
     *
     * @return forum boards
     *
     * @see ForumCategory
     * @since 1.0.0
     */
    public abstract List<ForumCategory> getForumBoards();

// forum topic

    /**
     * Returns a forum topic.
     *
     * @param id forum topic id
     * @return forum topic
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
     * @param id Manga id
     * @param fields a string array of the fields that should be returned
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
     * @see #getMangaRanking(String)
     * @see MangaRankingQuery
     * @see MangaRankingQuery#search()
     * @see MangaRanking
     * @see MangaRankingType
     * @since 1.0.0
     */
    public abstract MangaRankingQuery getMangaRanking(final MangaRankingType rankingType);

    /**
     * Returns a Manga ranking query.
     * <br>
     * It is recommended to use {@link #getMangaRanking(MangaRankingType)} rather than this method.
     * This method should only be used if you are using a ranking type that is missing from {@link MangaRankingType}.
     *
     * @param rankingType ranking type API field
     * @return ranked Manga
     * @throws NullPointerException if ranking type is null
     *
     * @see #getMangaRanking(MangaRankingType)
     * @see MangaRankingQuery
     * @see MangaRankingQuery#search()
     * @see MangaRanking
     * @since 2.9.0
     */
    public abstract MangaRankingQuery getMangaRanking(final String rankingType);

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
     * @return authenticated user
     *
     * @see User
     * @see #getAuthenticatedUser(String...)
     * @since 2.2.0
     */
    public abstract User getAuthenticatedUser();

    /**
     * Returns the authenticated user.
     *
     * @param fields a string array of the fields that should be returned
     * @return authenticated user
     *
     * @see User
     * @see #getAuthenticatedUser()
     * @see Fields#user
     * @since 2.2.0
     */
    public abstract User getAuthenticatedUser(final String... fields);

    /**
     * Returns the authenticated user.
     *
     * @deprecated use {@link #getAuthenticatedUser()} instead
     * @return user
     *
     * @see User
     * @see #getMyself(String...)
     * @since 1.0.0
     */
    @Deprecated
    public abstract User getMyself();

    /**
     * Returns the authenticated user.
     *
     * @deprecated use {@link #getAuthenticatedUser(String...)} instead
     * @param fields a string array of the fields that should be returned
     * @return user
     *
     * @see User
     * @see #getMyself()
     * @see Fields#user
     * @since 1.0.0
     */
    @Deprecated
    public abstract User getMyself(final String... fields);

    /**
     * Returns a user given their username.
     *
     * @param username username
     * @return user
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
     * @param fields a string array of the fields that should be returned
     * @return user
     * @throws NullPointerException if username is null
     *
     * @see User
     * @see #getUser(String)
     * @see Fields#user
     * @since 1.0.0
     */
    public abstract User getUser(final String username, final String... fields);

}
