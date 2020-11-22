package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.time.Season;

/**
 * Represents an Anime airing season.
 *
 * @see AnimePreview
 * @see Season
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeSeason {

    /**
     * Returns a list of Anime set to air for the season.
     *
     * @return list of Anime
     *
     * @see AnimePreview
     * @since 1.0.0
     */
    public abstract AnimePreview[] getAnimePreview();

    /**
     * Returns the season.
     *
     * @return season
     *
     * @see Season
     * @since 1.0.0
     */
    public abstract Season getSeason();

}
