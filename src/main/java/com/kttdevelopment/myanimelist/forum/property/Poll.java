package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.property.ID;

public abstract class Poll implements ID {

    // API methods

    public abstract String getQuestion();

    public abstract boolean isClosed();

    public abstract PollOption[] getOptions();

    // additional methods

    public abstract ForumTopic getForumTopic();

}
