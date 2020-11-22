package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.forum.property.Poll;

/**
 * Represents a forum topic.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumTopic {

    /**
     * Returns the forum topic title.
     *
     * @return topic title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    /**
     * Returns a list of forum posts.
     *
     * @return forum posts
     *
     * @see ForumPost
     * @since 1.0.0
     */
    public abstract ForumPost[] getPosts();

    /**
     * Returns the forum active poll.
     *
     * @return poll
     *
     * @see Poll
     * @since 1.0.0
     */
    public abstract Poll getPoll();

}
