package com.kttdevelopment.myanimelist.property;

/**
 * Represents statistics.
 *
 * @param <E> count types
 *
 * @see com.kttdevelopment.myanimelist.anime.property.AnimeStatistics
 * @see com.kttdevelopment.myanimelist.manga.property.MangaStatistics
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("rawtypes")
public abstract class Statistics<E extends Enum> {

    /**
     * Returns the count for a particular statistic.
     *
     * @param type statistic type
     *
     * @return total count
     *
     * @since 1.0.0
     */
    public abstract int getCount(final E type);

    /**
     * Returns the amount of users contributing to this statistic.
     *
     * @return total users
     *
     * @since 1.0.0
     */
    public abstract int getUserCount();

}
