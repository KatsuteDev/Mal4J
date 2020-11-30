package com.kttdevelopment.myanimelist.property;

/**
 * Represents a picture.
 *
 * @see MediaItem#getMainPicture()
 * @see FullMediaItem#getPictures()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Picture {

    /**
     * Medium picture URL.
     *
     * @return medium URL
     *
     * @see #getLargeURL()
     * @since 1.0.0
     */
    public abstract String getMediumURL();

    /**
     * Large picture URL
     *
     * @return large URL
     *
     * @see #getMediumURL()
     * @since 1.0.0
     */
    public abstract String getLargeURL();

}
