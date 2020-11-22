package com.kttdevelopment.myanimelist.forum;

public abstract class ForumBoard {

    public abstract long getId();

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract ForumSubBoard[] getSubBoards();

}
