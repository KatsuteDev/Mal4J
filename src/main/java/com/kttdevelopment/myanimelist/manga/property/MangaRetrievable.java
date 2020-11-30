package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.manga.Manga;

/**
 * Indicates that a Manga can be retrieved from the object.
 *
 * @see Manga
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface MangaRetrievable {

    /**
     * Returns the Manga.
     *
     * @return Manga
     *
     * @see Manga
     * @since 1.0.0
     */
    Manga getManga();

}
