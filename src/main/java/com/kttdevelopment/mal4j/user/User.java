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

package com.kttdevelopment.mal4j.user;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.property.IDN;
import com.kttdevelopment.mal4j.query.UserAnimeListQuery;
import com.kttdevelopment.mal4j.query.UserMangaListQuery;

import java.util.Date;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_get</a> <br>
 * Represents a user.
 *
 * @see MyAnimeList#getMyself()
 * @see MyAnimeList#getMyself(String[])
 * @see MyAnimeList#getUser(String)
 * @see MyAnimeList#getUser(String, String...)
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class User implements IDN {

    /**
     * Returns the picture URL.
     *
     * @return picture URL
     *
     * @since 1.0.0
     */
    public abstract String getPictureURL();

    /**
     * Returns the user's gender.
     *
     * @return gender
     *
     * @since 1.0.0
     */
    public abstract String getGender();

    /**
     * Returns the user's birthday.
     *
     * @return birthday
     *
     * @since 1.0.0
     */
    public abstract Date getBirthday();

    /**
     * Returns the user's location.
     *
     * @return location
     *
     * @since 1.0.0
     */
    public abstract String getLocation();

    /**
     * Returns when the user first joined.
     *
     * @return joined date
     *
     * @see #getJoinedAtEpochMillis()
     * @since 1.0.0
     */
    public abstract Date getJoinedAt();

    /**
     * Returns when the user first joined as milliseconds since epoch.
     *
     * @return joined date
     *
     * @see #getJoinedAt()
     * @since 1.0.0
     */
    public abstract Long getJoinedAtEpochMillis();

    /**
     * Returns the user's Anime statistics.
     *
     * @return Anime statistics
     *
     * @see UserAnimeStatistics
     * @since 1.0.0
     */
    public abstract UserAnimeStatistics getAnimeStatistics();

    /**
     * Returns the user's timezone.
     *
     * @return timezone
     *
     * @since 1.0.0
     */
    public abstract String getTimeZone();

    /**
     * Returns if the user is a MyAnimeList supporter.
     *
     * @return supporter
     *
     * @since 1.0.0
     */
    public abstract Boolean isSupporter();

    /**
     * Returns user Anime list.
     *
     * @return Anime listing
     *
     * @see UserAnimeListQuery
     * @see MyAnimeList#getUserAnimeListing()
     * @see MyAnimeList#getUserAnimeListing(String)
     * @since 1.2.0
     */
    public abstract UserAnimeListQuery getUserAnimeListing();

    /**
     * Returns user Manga list.
     *
     * @return Manga listing
     *
     * @see UserMangaListQuery
     * @see MyAnimeList#getUserMangaListing()
     * @see MyAnimeList#getUserMangaListing(String)
     * @since 1.2.0
     */
    public abstract UserMangaListQuery getUserMangaListing();

}
