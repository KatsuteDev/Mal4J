package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.forum.property.Poll;

public abstract class ForumTopic {

    public abstract String getTitle();

    public abstract Post[] getPosts();

    public abstract Poll getPoll();

}
