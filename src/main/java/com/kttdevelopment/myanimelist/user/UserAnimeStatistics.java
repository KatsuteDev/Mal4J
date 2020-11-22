package com.kttdevelopment.myanimelist.user;

/**
 * Represents a user's Anime statistics.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class UserAnimeStatistics {

    /**
     * Returns total watching.
     *
     * @return Anime watching
     *
     * @since 1.0.0
     */
    public abstract int getWatching();

    /**
     * Returns total completed.
     *
     * @return Anime completed
     *
     * @since 1.0.0
     */
    public abstract int getCompleted();

    /**
     * Returns total on hold.
     *
     * @return Anime on hold
     *
     * @since 1.0.0
     */
    public abstract int getOnHold();

    /**
     * Returns total dropped.
     *
     * @return Anime watching
     *
     * @since 1.0.0
     */
    public abstract int getDropped();

    /**
     * Returns total planned.
     *
     * @return Anime planned
     *
     * @since 1.0.0
     */
    public abstract int getPlanToWatch();

    /**
     * Returns total Anime listed.
     *
     * @return Anime total
     *
     * @since 1.0.0
     */
    public abstract int getTotal();

    /**
     * Returns total days watched.
     *
     * @return days watched
     *
     * @since 1.0.0
     */
    public abstract double getDaysWatching();

    /**
     * Returns total days completed.
     *
     * @return total completed
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
     * Returns total days planned to watch.
     *
     * @return days planned
     *
     * @since 1.0.0
     */
    public abstract double getDaysPlanToWatch();

    /**
     * Returns total days.
     *
     * @return total days
     *
     * @since 1.0.0
     */
    public abstract double getTotalDays();

    /**
     * Returns total episodes listed.
     *
     * @return total episodes
     *
     * @since 1.0.0
     */
    public abstract int getTotalEpisodes();

    /**
     * Returns total Anime rewatched.
     *
     * @return total rewatched
     *
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public abstract int getTotalRewatched();

    /**
     * Returns average score.
     *
     * @return average score
     *
     * @since 1.0.0
     */
    public abstract double getMeanScore();

}
