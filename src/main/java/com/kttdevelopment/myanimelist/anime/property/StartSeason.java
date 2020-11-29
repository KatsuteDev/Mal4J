package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.property.time.Season;

/**
 * Represents the start season for an Anime.
 *
 * @see Anime#getStartSeason()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Develoment
 */
public abstract class StartSeason {

    /**
     * The year that the Anime will start in.
     *
     * @return start year
     *
     * @since 1.0.0
     */
    public abstract int getYear();

    /**
     * The broadcast season that the Anime will start in
     *
     * @return start season
     *
     * @see Season
     * @since 1.0.0
     */
    public abstract Season getSeason();

}
