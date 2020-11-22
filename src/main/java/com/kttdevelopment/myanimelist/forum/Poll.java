package com.kttdevelopment.myanimelist.forum;

public abstract class Poll {

    public abstract int getId();

    public abstract String getQuestion();

    public abstract int getClosed();

    public abstract PollOption[] getOptions();

}
