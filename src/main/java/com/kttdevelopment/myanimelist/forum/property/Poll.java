package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;

public abstract class Poll implements ID {

    public abstract String getQuestion();

    public abstract int getClosed();

    public abstract PollOption[] getOptions();

}
