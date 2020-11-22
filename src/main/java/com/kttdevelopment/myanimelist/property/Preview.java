package com.kttdevelopment.myanimelist.property;

/**
 * Represents a preview of the full object.
 *
 * @see com.kttdevelopment.myanimelist.anime.AnimePreview
 * @see com.kttdevelopment.myanimelist.manga.MangaPreview
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface Preview extends ID {

    /**
     * Returns the title.
     *
     * @return title
     * @since 1.0.0
     */
    String getTitle();

    /**
     * Returns the main picture.
     *
     * @return main picture
     *
     * @see Picture
     * @since 1.0.0
     */
    Picture getMainPicture();

}
