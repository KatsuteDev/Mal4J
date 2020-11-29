package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.forum.property.ForumTopicCreator;
import com.kttdevelopment.myanimelist.property.ID;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topics_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topics_get</a> <br>
 *
 * Represents a forum topic's details.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumTopicDetail implements ID {

    /**
     * Returns the title of the topic.
     *
     * @return title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    /**
     * Returns when the topic was created at.
     *
     * @return topic creation time
     *
     * @since 1.0.l0
     */
    public abstract long getCreatedAt();

    /**
     * Returns who created the topic.
     *
     * @return topic creator
     *
     * @see ForumTopicCreator
     * @since 1.0.0
     */
    public abstract ForumTopicCreator getCreatedBy();

    /**
     * Returns the total post count.
     *
     * @return total posts
     *
     * @since 1.0.0
     */
    public abstract int getPostsCount();

    /**
     * Returns when the last post was created at.
     *
     * @return last posted time
     *
     * @since 1.0.0
     */
    public abstract long getLastPostCreatedAt();

    /**
     * Returns who the last post was created by.
     *
     * @return last poster
     *
     * @see ForumTopicCreator
     * @since 1.0.0
     */
    public abstract ForumTopicCreator getLastPostCreatedBy();

    /**
     * Returns if the topic is locked.
     *
     * @return locked
     *
     * @since 1.0.0
     */
    public abstract boolean isLocked();

}
