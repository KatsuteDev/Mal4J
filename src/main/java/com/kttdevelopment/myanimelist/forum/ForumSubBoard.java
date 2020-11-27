package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;

public abstract class ForumSubBoard implements ID {

    // API methods

    public abstract String getTitle();

    // additional methods

    public abstract ForumBoard getBoard();

}
