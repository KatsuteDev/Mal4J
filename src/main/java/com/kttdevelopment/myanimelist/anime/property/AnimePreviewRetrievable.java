package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.AnimePreview;

/**
 * Indicates that an Anime preview can be retrieved from the object.
 *
 * @see AnimePreview
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface AnimePreviewRetrievable {

    /**
     * Returns the Anime preview.
     *
     * @return anime preview
     *
     * @see AnimePreview
     * @since 1.0.0
     */
    AnimePreview getAnimePreview();

}
