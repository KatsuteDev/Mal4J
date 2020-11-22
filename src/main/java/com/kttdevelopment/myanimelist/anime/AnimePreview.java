package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.property.Preview;

/**
 * Represents an Anime preview. The full Anime details can be obtained using {@link #getAnime()}
 *
 * @see Anime
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimePreview implements Preview {

    /**
     * Returns the full Anime details.
     *
     * @return Anime details
     *
     * @since 1.0.0
     * @see Anime
     */
    public abstract Anime getAnime();

}
