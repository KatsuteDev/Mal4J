package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;

public abstract class ForumTopicDetail implements ID {

    public abstract String getTitle();

    public abstract long getCreatedAt();

    public abstract ForumTopicCreator getCreatedBy();

    public abstract int getPostsCount();

    public abstract long getLastPostCreatedAt();

    public abstract ForumTopicCreator getLastPostCreatedBy();

    public abstract boolean isLocked();

}
