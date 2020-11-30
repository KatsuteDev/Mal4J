package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.forum.Post;
import com.kttdevelopment.myanimelist.property.IDN;
import com.kttdevelopment.myanimelist.user.User;
import com.kttdevelopment.myanimelist.user.property.UserRetrievable;

/**
 * Returns the post's author.
 *
 * @see Post#getAuthor()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class PostAuthor implements IDN, UserRetrievable {

    // API methods

    /**
     * Returns the author's avatar URL.
     *
     * @return avatar URL
     *
     * @since 1.0.0
     */
    public abstract String getForumAvatarURL();

}
