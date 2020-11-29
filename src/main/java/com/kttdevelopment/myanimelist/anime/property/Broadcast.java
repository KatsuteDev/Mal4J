package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.property.time.DayOfWeek;
import com.kttdevelopment.myanimelist.anime.property.time.Time;

/**
 * Represents the broadcast time of an Anime.
 *
 * @see Anime#getBroadcast()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Broadcast {

    /**
     * The day of the week that the Anime will broadcast on (JST).
     *
     * @return day of the week
     *
     * @see DayOfWeek
     * @since 1.0.0
     */
    public abstract DayOfWeek getDayOfWeek();

    /**
     * The time that the Anime will broadcast at (JST).
     *
     * @return broadcast time
     *
     * @see Time
     * @since 1.0.0
     */
    public abstract Time getStartTime();

}
