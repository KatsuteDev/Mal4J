package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.manga.property.MangaType;
import com.kttdevelopment.myanimelist.property.ExtendedPreview;

/**
 * Represents the full details of an Anime.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Anime extends AnimePreview implements ExtendedPreview {

    /**
     * Returns the Anime type.
     *
     * @return Anime type
     *
     * @see AnimeType
     * @since 1.0.0
     */
    public abstract AnimeType getAnimeType();

    /**
     * Returns the Anime airing status.
     *
     * @return status
     *
     * @see AnimeAirStatus
     * @since 1.0.0
     */
    public abstract AnimeAirStatus getStatus();

    /**
     * Returns the Anime list status.
     *
     * @return list status
     *
     * @see AnimeListStatus
     * @since 1.0.0
     */
    public abstract AnimeListStatus getListStatus();

    /**
     * Returns the Anime episode count.
     *
     * @return episode count
     *
     * @since 1.0.0
     */
    public abstract int getEpisodes();

    /**
     * Returns the airing season.
     *
     * @return airing season
     *
     * @see StartSeason
     * @since 1.0.0
     */
    public abstract StartSeason getStartSeason();

    /**
     * Returns the Anime source material.
     *
     * @return source
     *
     * @see MangaType
     * @since 1.0.0
     */
    public abstract MangaType getSource();

    /**
     * Returns the average episode length in seconds.
     *
     * @return episode length
     *
     * @since 1.0.0
     */
    public abstract int getAverageEpisodeLength();

    /**
     * Returns the audience rating (ex: PG-13).
     *
     * @return audience rating
     *
     * @since 1.0.0
     */
    public abstract String getRating();

    /**
     * Returns a list of Anime recommendations.
     *
     * @return recommendations
     *
     * @see AnimeRecommendation
     * @since 1.0.0
     */
    public abstract AnimeRecommendation[] getRecommendations();

    /**
     * Returns a list of the studios.
     *
     * @return studios
     *
     * @see Studio
     * @since 1.0.0
     */
    public abstract Studio[] getStudios();

}
