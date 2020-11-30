package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.AnimePreview;
import com.kttdevelopment.myanimelist.manga.property.MangaPreviewRetrievable;
import com.kttdevelopment.myanimelist.manga.property.MangaRetrievable;
import com.kttdevelopment.myanimelist.property.Recommendation;

/**
 * Represents a Manga recommendation.
 *
 * @see Manga#getRecommendations()
 * @see MangaPreview
 * @see Manga
 * @see Recommendation
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaRecommendation extends Recommendation implements MangaPreviewRetrievable, MangaRetrievable {

}
