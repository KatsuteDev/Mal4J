package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;

public abstract class ForumBoard implements ID {

    public abstract String getTitle();

    public abstract String getDescription();

    @SuppressWarnings("SpellCheckingInspection")
    public abstract ForumSubBoard[] getSubBoards();

}
