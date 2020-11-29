package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.AnimePreviewRetrievable;
import com.kttdevelopment.myanimelist.anime.property.AnimeRetrievable;
import com.kttdevelopment.myanimelist.property.Recommendation;
import com.kttdevelopment.myanimelist.property.RelatedMedia;

/**
 * Represents a related Anime
 *
 * @see Anime#getRelatedAnime()
 * @see AnimePreview
 * @see Anime
 * @see RelatedMedia
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class RelatedAnime extends RelatedMedia implements AnimePreviewRetrievable, AnimeRetrievable {

}
