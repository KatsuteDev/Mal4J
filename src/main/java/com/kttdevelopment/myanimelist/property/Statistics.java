package com.kttdevelopment.myanimelist.property;

/**
 * Represents a statistic.
 *
 * @see com.kttdevelopment.myanimelist.anime.property.AnimeStatistics
 * @see com.kttdevelopment.myanimelist.manga.property.MangaStatistics
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("deprecation")
public abstract class Statistics {

    /**
     * Returns the total amount of users contributing to the statistic.
     *
     * @return total users
     *
     * @since 1.0.0
     */
    public abstract int getUserCount();

}
