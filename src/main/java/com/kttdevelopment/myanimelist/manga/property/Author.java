package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents a Manga author.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Author implements ID {

    /**
     * Returns the author's first name.
     *
     * @return first name
     *
     * @see #getLastName()
     * @since 1.0.0
     */
    public abstract String getFirstName();

    /**
     * Returns the author's last name.
     *
     * @return last name
     *
     * @see #getFirstName()
     * @since 1.0.0
     */
    public abstract String getLastName();

    /**
     * Returns the author's role.
     *
     * @return role
     *
     * @since 1.0.0
     */
    public abstract String getRole();

}
