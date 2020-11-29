package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.property.Recommendation;

/**
 * Represents an Anime recommendation.
 *
 * @see Anime#getRecommendations()
 * @see AnimePreview
 * @see Anime
 * @see Recommendation
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeRecommendation extends Recommendation implements AnimePreviewRetrievable, AnimeRetrievable {

}
