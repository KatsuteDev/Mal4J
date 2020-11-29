package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.forum.property.ForumTopicDetail;

import java.util.List;

public abstract class ForumSearchQuery extends FieldSearchQuery<ForumSearchQuery,List<ForumTopicDetail>> {

    protected String query;
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    protected Long boardId, subboardId;
    protected final String sort = "recent";
    protected String topicUsername, username;
    
    public ForumSearchQuery(final MyAnimeListService service){
        super(service);
    }

    public final ForumSearchQuery withQuery(final String query){
        this.query = query;
        return this;
    }

    public final ForumSearchQuery withBoardId(final long boardId){
        this.boardId = boardId;
        return this;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public final ForumSearchQuery withSubboardId(final long subboardId){
        this.subboardId = subboardId;
        return this;
    }

    public final ForumSearchQuery withTopicUsername(final String topicUsername){
        this.topicUsername = topicUsername;
        return this;
    }

    public final ForumSearchQuery withUsername(final String username){
        this.username = username;
        return this;
    }

}
