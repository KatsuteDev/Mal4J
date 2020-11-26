package com.kttdevelopment.myanimelist.user;

import com.kttdevelopment.myanimelist.property.ID;

public abstract class User implements ID {

    public abstract String getUsername();

    public abstract String getLocation();

    public abstract long getJoinedAt();

    public abstract UserAnimeStatistics getAnimeStatistics();

    public abstract UserMangaStatistics getMangaStatistics();

}
