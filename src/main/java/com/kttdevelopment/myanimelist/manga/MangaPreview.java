package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.property.Preview;

/**
 * Represents an Manga preview. The full Manga details can be obtained using {@link #getManga()}.
 *
 * @see Manga
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaPreview implements Preview {

    /**
     * Returns the full Manga details.
     *
     * @return Manga details
     *
     * @since 1.0.0
     * @see Manga
     */
    public abstract Manga getManga();

}
