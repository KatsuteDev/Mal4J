package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.manga.Manga;
import com.kttdevelopment.myanimelist.property.IDN;

/**
 * Represents a Manga publisher.
 *
 * @see Manga#getSerialization()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Publisher implements IDN {

    /**
     * Returns the role of the publisher.
     *
     * @return role
     *
     * @since 1.0.0
     */
    public abstract String getRole();

}
