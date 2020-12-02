package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.Statistics;

/**
 * Represents an Manga's statistics.
 *
 * @deprecated Does not exist in the API currently
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public abstract class MangaStatistics extends Statistics {

    /**
     * Returns the total users reading.
     *
     * @return total users reading
     *
     * @since 1.0.0
     */
    public abstract int getReading();

    /**
     * Returns the total users completed.
     *
     * @return total users completed
     *
     * @since 1.0.0
     */
    public abstract int getCompleted();

    /**
     * Returns the total users on hold.
     *
     * @return total users on hold
     *
     * @since 1.0.0
     */
    public abstract int getOnHold();

     /**
     * Returns the total users dropped.
     *
     * @return total users dropped
     *
     * @since 1.0.0
     */
    public abstract int getDropped();

     /**
     * Returns the total users planning to read.
     *
     * @return total users planning to read
     *
     * @since 1.0.0
     */
    public abstract int getPlanToRead();

}
