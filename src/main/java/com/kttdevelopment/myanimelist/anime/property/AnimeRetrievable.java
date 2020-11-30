package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.Anime;

/**
 * Indicates that an Anime can be retrieved from the object.
 *
 * @see Anime
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface AnimeRetrievable {

    /**
     * Returns the Anime.
     *
     * @return Anime
     *
     * @see Anime
     * @since 1.0.0
     */
    Anime getAnime();

}
