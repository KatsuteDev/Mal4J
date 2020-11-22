package com.kttdevelopment.myanimelist.property;

/**
 * Represents a recommendation.
 *
 * @see com.kttdevelopment.myanimelist.anime.AnimeRecommendation
 * @see com.kttdevelopment.myanimelist.manga.MangaRecommendation
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Recommendation {

    /**
     * Returns the amount of recommendations.
     *
     * @return recommendations
     *
     * @since 1.0.0
     */
    public abstract int getRecommendations();

}
