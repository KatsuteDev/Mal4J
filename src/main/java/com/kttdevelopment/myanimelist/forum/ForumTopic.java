package com.kttdevelopment.myanimelist.forum;

public abstract class ForumTopic {

    public abstract String getTitle();

    public abstract ForumPost[] getPosts();

    public abstract Poll getPoll();

}
