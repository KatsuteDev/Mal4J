package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;
import com.kttdevelopment.myanimelist.user.User;

public abstract class PostAuthor implements ID {

    public abstract String getName();

    public abstract String getForumAvatarURL();

    public abstract User getUser();

}
