package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.Statistics;

/**
 * Represents Manga statistics.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaStatistics extends Statistics<MangaStatus> {

    /**
     * Returns the total amount of users reading.
     *
     * @return users reading
     *
     * @since 1.0.0
     */
    public abstract int getReading();

    /**
     * Returns the total amount of users who have completed.
     *
     * @return users completed
     *
     * @since 1.0.0
     */
    public abstract int getCompleted();

    /**
     * Returns the total amount of users on hold.
     *
     * @return users on hold
     *
     * @since 1.0.0
     */
    public abstract int getOnHold();

    /**
     * Returns the total amount of users who have dropped.
     *
     * @return users dropped
     *
     * @since 1.0.0
     */
    public abstract int getDropped();

    /**
     * Returns the total amount of users who plan to read.
     *
     * @return users planned
     *
     * @since 1.0.0
     */
    public abstract int getPlanToRead();

}
