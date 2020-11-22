package com.kttdevelopment.myanimelist.user;

public abstract class User {

    public abstract long getId();

    public abstract String getUsername();

    public abstract String getLocation();

    public abstract long getJoinedAt();

    public abstract AnimeStatistics getAnimeStatistics();

}
