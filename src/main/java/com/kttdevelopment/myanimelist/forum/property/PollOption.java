package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;

public abstract class PollOption implements ID {

    // API methods

    public abstract String getText();

    public abstract int getVotes();

    // additional methods

    public abstract Poll getPoll();

}
