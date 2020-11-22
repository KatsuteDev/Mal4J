package com.kttdevelopment.myanimelist.user;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents a full user's details.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class User implements ID {

    /**
     * Returns the user's name.
     *
     * @return username
     *
     * @since 1.0.0
     */
    public abstract String getUsername();

    /**
     * Returns the user's location.
     *
     * @return location
     *
     * @since 1.0.0
     */
    public abstract String getLocation();

    /**
     * Returns when the user joined MAL in milliseconds.
     *
     * @return join time
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
     * Returns the user's Manga statistics.
     *
     * @return Manga statistics
     *
     * @see UserMangaStatistics
     * @since 1.0.0
     */
    public abstract UserMangaStatistics getMangaStatistics();

}
