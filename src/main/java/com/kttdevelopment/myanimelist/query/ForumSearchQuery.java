package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.forum.ForumTopicDetail;
import com.kttdevelopment.myanimelist.forum.property.ForumSort;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topics_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topics_get</a> <br>
 * Represents a forum topic search query.
 *
 * @see MyAnimeList#getForumTopics()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumSearchQuery extends FieldSearchQuery<ForumSearchQuery,ForumTopicDetail> {

    protected String query;
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    protected Long boardId, subboardId;
    protected final String sort = ForumSort.Recent.field();
    protected String topicUsername, username;
    
    public ForumSearchQuery(final MyAnimeListService service){
        super(service);
    }

    /**
     * Sets the search query.
     *
     * @param query query
     * @return search query
     *
     * @since 1.0.0
     */
    public final ForumSearchQuery withQuery(final String query){
        this.query = query;
        return this;
    }

    /**
     * Sets the board id.
     *
     * @param boardId board id
     * @return search query
     *
     * @since 1.0.0
     */
    public final ForumSearchQuery withBoardId(final long boardId){
        this.boardId = boardId;
        return this;
    }

    /**
     * Sets the subboard id.
     *
     * @param subboardId subboard id
     * @return search query
     *
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public final ForumSearchQuery withSubboardId(final long subboardId){
        this.subboardId = subboardId;
        return this;
    }

    /**
     * Sets the topic username.
     *
     * @param topicUsername topic username
     * @return search query
     *
     * @since 1.0.0
     */
    public final ForumSearchQuery withTopicUsername(final String topicUsername){
        this.topicUsername = topicUsername;
        return this;
    }

    /**
     * Sets the username.
     *
     * @param username username
     * @return search query
     *
     * @since 1.0.0
     */
    public final ForumSearchQuery withUsername(final String username){
        this.username = username;
        return this;
    }

}
