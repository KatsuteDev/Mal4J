/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
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

package dev.katsute.mal4j.user;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.property.IDN;
import dev.katsute.mal4j.query.UserAnimeListQuery;
import dev.katsute.mal4j.query.UserMangaListQuery;
import dev.katsute.mal4j.user.property.AnimeAffinity;
import dev.katsute.mal4j.user.property.MangaAffinity;

import java.util.Date;
import java.util.function.Consumer;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_get</a> <br>
 * Represents a user.
 *
 * @see MyAnimeList#getAuthenticatedUser()
 * @see MyAnimeList#getAuthenticatedUser(String...)
 * @see MyAnimeList#getUser(String)
 * @see MyAnimeList#getUser(String, String...)
 * @since 1.0.0
 * @version 2.3.0
 * @author Katsute
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

    /**
     * <b>This is a thread blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Anime affinity with authenticated user.
     *
     * @return Anime affinity
     *
     * @see AnimeAffinity
     * @see #getAnimeAffinity(Consumer)
     * @see #getAnimeAffinity(String)
     * @see #getAnimeAffinity(String, Consumer)
     * @see #getAnimeAffinity(User)
     * @see #getAnimeAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract AnimeAffinity getAnimeAffinity();

    /**
     * <b>This is a non-blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Anime affinity with authenticated user and returns it to the callback.
     *
     * @param callback callback method
     *
     * @see AnimeAffinity
     * @see #getAnimeAffinity()
     * @see #getAnimeAffinity(String)
     * @see #getAnimeAffinity(String, Consumer)
     * @see #getAnimeAffinity(User)
     * @see #getAnimeAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract void getAnimeAffinity(final Consumer<AnimeAffinity> callback);

    /**
     * <b>This is a thread blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Anime affinity with a user.
     *
     * @param username username
     * @return Anime affinity
     *
     * @see AnimeAffinity
     * @see #getAnimeAffinity()
     * @see #getAnimeAffinity(Consumer)
     * @see #getAnimeAffinity(String, Consumer)
     * @see #getAnimeAffinity(User)
     * @see #getAnimeAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract AnimeAffinity getAnimeAffinity(final String username);

    /**
     * <b>This is a non-blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Anime affinity with a user and returns it to the callback.
     *
     * @param username username
     * @param callback callback method
     *
     * @see AnimeAffinity
     * @see #getAnimeAffinity()
     * @see #getAnimeAffinity(Consumer)
     * @see #getAnimeAffinity(String)
     * @see #getAnimeAffinity(User)
     * @see #getAnimeAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract void getAnimeAffinity(final String username, final Consumer<AnimeAffinity> callback);

    /**
     * <b>This is a thread blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Anime affinity with a user.
     *
     * @param user user
     * @return Anime affinity
     *
     * @see AnimeAffinity
     * @see #getAnimeAffinity()
     * @see #getAnimeAffinity(Consumer)
     * @see #getAnimeAffinity(String)
     * @see #getAnimeAffinity(String, Consumer)
     * @see #getAnimeAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract AnimeAffinity getAnimeAffinity(final User user);

    /**
     * <b>This is a non-blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Anime affinity with a user and returns it to the callback.
     *
     * @param user user
     * @param callback callback method
     *
     * @see AnimeAffinity
     * @see #getAnimeAffinity()
     * @see #getAnimeAffinity(Consumer)
     * @see #getAnimeAffinity(String)
     * @see #getAnimeAffinity(String, Consumer)
     * @see #getAnimeAffinity(User)
     * @since 2.3.0
     */
    public abstract void getAnimeAffinity(final User user, final Consumer<AnimeAffinity> callback);

    /**
     * <b>This is a thread blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Manga affinity with authenticated user.
     *
     * @return Manga affinity
     *
     * @see MangaAffinity
     * @see #getMangaAffinity(Consumer)
     * @see #getMangaAffinity(String)
     * @see #getMangaAffinity(String, Consumer)
     * @see #getMangaAffinity(User)
     * @see #getMangaAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract MangaAffinity getMangaAffinity();

    /**
     * <b>This is a non-blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Manga affinity with authenticated user and returns it to the callback.
     *
     * @param callback callback method
     *
     * @see MangaAffinity
     * @see #getMangaAffinity()
     * @see #getMangaAffinity(String)
     * @see #getMangaAffinity(String, Consumer)
     * @see #getMangaAffinity(User)
     * @see #getMangaAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract void getMangaAffinity(final Consumer<MangaAffinity> callback);

    /**
     * <b>This is a thread blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Manga affinity with a user.
     *
     * @param username username
     * @return Manga affinity
     *
     * @see MangaAffinity
     * @see #getMangaAffinity()
     * @see #getMangaAffinity(Consumer)
     * @see #getMangaAffinity(String, Consumer)
     * @see #getMangaAffinity(User)
     * @see #getMangaAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract MangaAffinity getMangaAffinity(final String username);

    /**
     * <b>This is a non-blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Manga affinity with a user and returns it to the callback.
     *
     * @param username username
     * @param callback callback method
     *
     * @see MangaAffinity
     * @see #getMangaAffinity()
     * @see #getMangaAffinity(Consumer)
     * @see #getMangaAffinity(String)
     * @see #getMangaAffinity(User)
     * @see #getMangaAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract void getMangaAffinity(final String username, final Consumer<MangaAffinity> callback);

    /**
     * <b>This is a thread blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Manga affinity with a user.
     *
     * @param user user
     * @return Manga affinity
     *
     * @see MangaAffinity
     * @see #getMangaAffinity()
     * @see #getMangaAffinity(Consumer)
     * @see #getMangaAffinity(String)
     * @see #getMangaAffinity(String, Consumer)
     * @see #getMangaAffinity(User, Consumer)
     * @since 2.3.0
     */
    public abstract MangaAffinity getMangaAffinity(final User user);

    /**
     * <b>This is a non-blocking method.</b>
     * <br>
     * <i>This may take several minutes to complete depending on the amount of listings.</i>
     * <br><br>
     * Calculates Manga affinity with a user and returns it to the callback.
     *
     * @param user user
     * @param callback callback method
     *
     * @see MangaAffinity
     * @see #getMangaAffinity()
     * @see #getMangaAffinity(Consumer)
     * @see #getMangaAffinity(String)
     * @see #getMangaAffinity(String, Consumer)
     * @see #getMangaAffinity(User)
     * @since 2.3.0
     */
    public abstract void getMangaAffinity(final User user, final Consumer<MangaAffinity> callback);

}