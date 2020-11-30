package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.manga.property.MangaPreviewRetrievable;
import com.kttdevelopment.myanimelist.manga.property.MangaRetrievable;
import com.kttdevelopment.myanimelist.property.RelatedMedia;

/**
 * Represents a related Manga.
 *
 * @see Anime#getRelatedManga()
 * @see Manga#getRelatedManga()
 * @see MangaPreview
 * @see Manga
 * @see RelatedMedia
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class RelatedManga extends RelatedMedia implements MangaPreviewRetrievable, MangaRetrievable {

}
