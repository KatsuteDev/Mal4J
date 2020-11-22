package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents a Manga publisher.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Publisher implements ID {

    /**
     * Returns the publisher name.
     *
     * @return studio name
     *
     * @since 1.0.0
     */
    public abstract String getName();

}
