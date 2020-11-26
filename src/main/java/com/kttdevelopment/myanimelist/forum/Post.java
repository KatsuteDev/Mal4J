package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;
import com.kttdevelopment.myanimelist.forum.property.PostAuthor;

public abstract class Post implements ID {

    // API methods

    public abstract int getNumber();

    public abstract long getCreatedAt();

    public abstract PostAuthor getAuthor();

    public abstract String getBody();

    public abstract String getSignature();

    // additional methods

    public abstract ForumTopic getForumTopic();

}
