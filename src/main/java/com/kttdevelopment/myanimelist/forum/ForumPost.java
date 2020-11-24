package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;
import com.kttdevelopment.myanimelist.forum.property.PostAuthor;

/**
 * Represents a forum post.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumPost implements ID {

    /**
     * Returns the post number.
     *
     * @return post number
     *
     * @since 1.0.0
     */
    public abstract int getNumber();

    /**
     * Returns when the post was created in milliseconds.
     *
     * @return post creation time
     *
     * @since 1.0.0
     */
    public abstract long getCreatedAt();

    /**
     * Returns the post's author.
     *
     * @return post author
     *
     * @see PostAuthor
     * @since 1.0.0
     */
    public abstract PostAuthor getAuthor();

    /**
     * Returns the post body.
     *
     * @return post body
     *
     * @since 1.0.0
     */
    public abstract String getBody();

    /**
     * Returns the post signature.
     *
     * @return post signature
     *
     * @since 1.0.0
     */
    public abstract String getSignature();

}
