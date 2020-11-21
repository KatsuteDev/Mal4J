package com.kttdevelopment.myanimelist.user;

public abstract class User {

    public abstract long getId();

    public abstract String getUsername();

    public abstract String getLocation();

    // todo: convert string to long millist
    public abstract long getJoinedAt();

    public abstract AnimeStatistics getAnimeStatistics();

    // todo: check endpoint for additional fields

}
