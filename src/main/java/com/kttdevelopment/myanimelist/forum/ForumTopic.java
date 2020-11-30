package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.forum.property.Poll;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get</a>
 *
 * Represents a forum topic.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getForumTopicDetail(long)
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumTopic {

    /**
     * Returns the topic title.
     *
     * @return topic title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    /**
     * Returns the posts in the topic.
     *
     * @return posts
     *
     * @see Post
     * @since 1.0.0
     */
    public abstract Post[] getPosts();

    /**
     * Returns the poll in the topic.
     *
     * @return poll
     *
     * @see Poll
     * @since 1.0.0
     */
    public abstract Poll getPoll();

}
