package com.kttdevelopment.myanimelist.user;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.property.IDN;

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
    public abstract long getBirthday();

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
     * @since 1.0.0
     */
    public abstract long getJoinedAt();

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
    public abstract boolean isSupporter();

}
