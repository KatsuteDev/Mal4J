package com.kttdevelopment.myanimelist.anime.property.time;

/**
 * Represents a time.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Time {

    /**
     * The hour on a 24 hour clock.
     *
     * @return hour
     *
     * @see #get12Hour()
     * @since 1.0.0
     */
    public abstract int getHour();

    /**
     * The hour on a 12 hour clock.
     *
     * @return hour
     *
     * @see #getHour()
     * @see #isAM()
     * @see #isPM()
     * @since 1.0.0
     */
    public abstract int get12Hour();

    /**
     * Returns if the 12 hours is on AM.
     *
     * @return AM
     *
     * @see #get12Hour()
     * @see #isPM()
     * @since 1.0.0
     */
    public abstract boolean isAM();

    /**
     * Returns if the 12 hours is on PM.
     *
     * @return PM
     *
     * @see #get12Hour()
     * @see #isAM()
     */
    public abstract boolean isPM();

    /**
     * Returns the minute.
     *
     * @return minute
     *
     * @since 1.0.0
     */
    public abstract int getMinute();

}
