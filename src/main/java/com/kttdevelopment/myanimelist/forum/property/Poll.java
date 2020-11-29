package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.property.ID;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get</a> <br>
 *
 * Represents a forum poll.
 *
 * @see ForumTopic#getPoll()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Poll implements ID {

    // API methods

    /**
     * Returns the poll's question.
     *
     * @return poll question
     *
     * @since 1.0.0
     */
    public abstract String getQuestion();

    /**
     * Returns if the poll is closed.
     *
     * @return closed
     *
     * @since 1.0.0
     */
    public abstract boolean isClosed();

    /**
     * Returns the poll's options.
     *
     * @return options
     *
     * @see PollOption
     * @since 1.0.0
     */
    public abstract PollOption[] getOptions();

    // additional methods

    /**
     * Returns the topic that the poll is from.
     *
     * @return forum topic
     *
     * @see ForumTopic
     * @since 1.0.0
     */
    public abstract ForumTopic getForumTopic();

}
