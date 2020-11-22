package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.manga.MangaPreview;

/**
 * Indicates that the object contains a Manga preview.
 *
 * @see MangaPreview
 * @since 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public interface MangaPreviewable {

    /**
     * Returns the Manga preview.
     *
     * @return Manga preview
     *
     * @see MangaPreview
     * @since 1.0.0
     * @author Ktt Development
     */
    MangaPreview getMangaPreview();

}
