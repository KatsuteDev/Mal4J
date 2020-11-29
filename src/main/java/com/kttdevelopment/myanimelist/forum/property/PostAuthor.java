package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.forum.Post;
import com.kttdevelopment.myanimelist.property.IDN;
import com.kttdevelopment.myanimelist.user.User;

/**
 * Returns the post's author.
 *
 * @see Post#getAuthor()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class PostAuthor implements IDN {

    // API methods

    /**
     * Returns the author's avatar URL.
     *
     * @return avatar URL
     *
     * @since 1.0.0
     */
    public abstract String getForumAvatarURL();

    // additional methods

    /**
     * Returns the user.
     *
     * @return user
     *
     * @see User
     * @since 1.0.0
     */
    public abstract User getUser();

}
