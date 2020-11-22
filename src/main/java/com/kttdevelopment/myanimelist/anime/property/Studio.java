package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents an Anime studio.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Studio implements ID {

    /**
     * Returns the studio name.
     *
     * @return studio name
     *
     * @since 1.0.0
     */
    public abstract String getName();

}
