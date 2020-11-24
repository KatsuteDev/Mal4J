package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;
import com.kttdevelopment.myanimelist.user.User;

/**
 * Represents a user preview.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class PostAuthor implements ID {

    /**
     * Returns the user's name.
     *
     * @return username
     *
     * @since 1.0.0
     */
    public abstract String getName();

    /**
     * Returns the user's forum avatar URL.
     *
     * @return avatar URL
     *
     * @since 1.0.0
     */
    public abstract String getForumAvatarURL();

    /**
     * Returns the full user.
     *
     * @return user
     *
     * @see User
     * @since 1.0.0
     */
    public abstract User getUser();

}
