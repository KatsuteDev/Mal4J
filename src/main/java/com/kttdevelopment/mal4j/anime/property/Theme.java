package com.kttdevelopment.mal4j.anime.property;

import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.property.ID;

/**
 * Represents an Anime op/ed.
 *
 * @see Anime#getOpeningThemes()
 * @see Anime#getEndingThemes()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Theme implements AnimeRetrievable, ID {

    /**
     * Returns the display name for the op/ed.
     *
     * @return display name
     *
     * @since 1.0.0
     */
    public abstract String getText();

}
