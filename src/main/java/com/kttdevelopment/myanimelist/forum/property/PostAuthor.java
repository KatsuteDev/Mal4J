package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.IDN;
import com.kttdevelopment.myanimelist.user.User;

public abstract class PostAuthor implements IDN {

    // API methods

    public abstract String getForumAvatarURL();

    // additional methods

    public abstract User getUser();

}
