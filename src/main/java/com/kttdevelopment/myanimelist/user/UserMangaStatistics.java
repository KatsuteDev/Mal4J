package com.kttdevelopment.myanimelist.user;

/**
 * Represents a user's Manga statistics.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class UserMangaStatistics {

    /**
     * Returns total reading.
     *
     * @return Manga reading
     *
     * @since 1.0.0
     */
    public abstract int getReading();

    /**
     * Returns total completed.
     *
     * @return Manga completed
     *
     * @since 1.0.0
     */
    public abstract int getCompleted();

    /**
     * Returns total on hold.
     *
     * @return Manga on hold
     *
     * @since 1.0.0
     */
    public abstract int getOnHold();

    /**
     * Returns total dropped.
     *
     * @return Manga dropped
     *
     * @since 1.0.0
     */
    public abstract int getDropped();

    /**
     * Returns total planned.
     *
     * @return Manga planned
     */
    public abstract int getPlanToRead();

    /**
     * Returns total Manga listed.
     *
     * @return Manga total
     *
     * @since 1.0.0
     */
    public abstract int getTotal();

    /**
     * Returns total days read.
     *
     * @return days read
     *
     * @since 1.0.0
     */
    public abstract double getDaysReading();

    /**
     * Returns total days completed.
     *
     * @return days completed
     *
     * @since 1.0.0
     */
    public abstract double getDaysCompleted();

    /**
     * Returns total days on hold.
     *
     * @return days on hold
     *
     * @since 1.0.0
     */
    public abstract double getDaysOnHold();

    /**
     * Returns total days dropped.
     *
     * @return days dropped
     *
     * @since 1.0.0
     */
    public abstract double getDaysDropped();

    /**
     * Returns total days planned.
     *
     * @return days planned
     *
     * @since 1.0.0
     */
    public abstract double getDaysPlanToRead();

    /**
     * Returns total days.
     *
     * @return total days
     *
     * @since 1.0.0
     */
    public abstract double getTotalDays();

    /**
     * Returns total volumes listed.
     *
     * @return total volumes
     *
     * @see #getTotalChapters()
     * @since 1.0.0
     */
    public abstract int getTotalVolumes();

    /**
     * Returns total chapters listed.
     *
     * @return total chapters
     *
     * @since 1.0.0
     */
    public abstract int getTotalChapters();

    /**
     * Returns total Manga reread.
     *
     * @return total reread
     *
     * @since 1.0.0
     */
    public abstract int getTotalReread();

    /**
     * Returns average score.
     *
     * @return average score
     *
     * @since 1.0.0
     */
    public abstract double getMeanScore();

}
