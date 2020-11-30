package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.manga.MangaPreview;

/**
 * Indicates that a Manga preview can be retrieved from the object.
 *
 * @see MangaPreview
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface MangaPreviewRetrievable {

    /**
     * Returns the Manga preview.
     *
     * @return Manga preview
     *
     * @see MangaPreview
     * @since 1.0.0
     */
    MangaPreview getMangaPreview();

}
