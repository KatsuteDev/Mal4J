package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.user.UserPreview;

public abstract class ForumPost {

    public abstract long getId();

    public abstract int getNumber();

    public abstract long getCreatedAt();

    public abstract UserPreview getAuthor();

    public abstract String getBody();

}
