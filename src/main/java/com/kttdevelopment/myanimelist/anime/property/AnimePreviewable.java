package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.AnimePreview;

/**
 * Indicates that the object contains an Anime preview.
 *
 * @see AnimePreview
 * @since 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public interface AnimePreviewable {

    /**
     * Returns the Anime preview.
     *
     * @return Anime preview
     *
     * @see AnimePreview
     * @since 1.0.0
     * @author Ktt Development
     */
    AnimePreview getAnimePreview();

}
