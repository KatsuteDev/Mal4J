package com.kttdevelopment.myanimelist.anime.property.time;

/**
 * Represents the time on a clock.
 *
 * @see com.kttdevelopment.myanimelist.anime.property.Broadcast
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Time {

    /**
     * Returns the hour on a 24 hour clock (0-23).
     *
     * @return hour
     *
     * @since 1.0.0
     */
    public abstract int getHour();

    /**
     * Returns the hour on a 12 hour clock (1-12).
     *
     * @return hour
     *
     * @since 1.0.0
     */
    public abstract int get12Hour();

    /**
     * Returns if the time is AM.
     *
     * @return if time is AM
     *
     * @see #get12Hour()
     * @see #isPM()
     * @since 1.0.0
     */
    public abstract boolean isAM();

    /**
     * Returns if the time is PM.
     *
     * @return if time is PM
     *
     * @see #get12Hour()
     * @see #isAM()
     * @since 1.0.0
     */
    public abstract boolean isPM();

    /**
     * Returns the minute (0-59).
     *
     * @return minute
     *
     * @since 1.0.0
     */
    public abstract int getMinute();

}
