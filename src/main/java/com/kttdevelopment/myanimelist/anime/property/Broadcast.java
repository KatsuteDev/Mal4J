package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.property.time.DayOfWeek;
import com.kttdevelopment.myanimelist.anime.property.time.Time;

/**
 * Represents a broadcast time.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Broadcast {

    /**
     * Returns the day of the week the broadcast is.
     *
     * @return day of week
     *
     * @see DayOfWeek
     * @since 1.0.0
     */
    public abstract DayOfWeek getDayOfWeek();

    /**
     * Returns the broadcast time.
     *
     * @return time
     *
     * @see Time
     * @since 1.0.0
     */
    public abstract Time getStartTime();

}
