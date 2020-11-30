package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;
import com.kttdevelopment.myanimelist.forum.property.PostAuthor;

/**
 * Represents a forum post.
 *
 * @see ForumTopic#getPosts()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Post implements ID {

    // API methods

    /**
     * Returns the post number.
     *
     * @return post number
     *
     * @since 1.0.0
     */
    public abstract int getNumber();

    /**
     * Returns when the post was created at.
     *
     * @return post creation time
     *
     * @since 1.0.0
     */
    public abstract long getCreatedAt();

    /**
     * Returns the post author.
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

    // additional methods

    /**
     * Returns the forum topic that the post is from.
     *
     * @return forum topic
     *
     * @see ForumTopic
     * @since 1.0.0
     */
    public abstract ForumTopic getForumTopic();

}
