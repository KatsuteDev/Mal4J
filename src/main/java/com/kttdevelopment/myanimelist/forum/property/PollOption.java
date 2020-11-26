package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;

public abstract class PollOption implements ID {

    public abstract String text();

    public abstract int votes();

}
