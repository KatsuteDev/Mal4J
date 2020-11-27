package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;

public abstract class ForumBoard implements ID {

    // API methods

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract ForumSubBoard[] getSubBoards();

    // additional methods

    public abstract ForumCategory getCategory();

}
