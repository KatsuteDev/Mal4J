package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.property.Statistics;

/**
 * Represents an Anime's statistics.
 *
 * @see Anime#getStatistics()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeStatistics extends Statistics {

    /**
     * The total amount of users watching.
     *
     * @return total users watching
     *
     * @since 1.0.0
     */
    public abstract int getWatching();

    /**
     * The total amount of users completed.
     *
     * @return total users completed
     *
     * @since 1.0.0
     */
    public abstract int getCompleted();

    /**
     * The total amount of users on hold.
     *
     * @return total users on hold
     *
     * @since 1.0.0
     */
    public abstract int getOnHold();

    /**
     * The total amount of users dropped.
     *
     * @return total users dropped
     *
     * @since 1.0.0
     */
    public abstract int getDropped();

    /**
     * The total amount of users planning to watch.
     *
     * @return total users planning to watch
     *
     * @since 1.0.0
     */
    public abstract int getPlanToWatch();

}
