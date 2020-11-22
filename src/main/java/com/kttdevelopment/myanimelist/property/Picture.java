package com.kttdevelopment.myanimelist.property;

/**
 * Represents the main picture.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Picture {

    /**
     * Returns the URL for the medium picture.
     *
     * @return medium picture URL
     *
     * @since 1.0.0
     */
    public abstract String getMediumURL();

    /**
     * Returns the URL for the large picture.
     *
     * @return large picture URL
     */
    public abstract String getLargeURL();

}
