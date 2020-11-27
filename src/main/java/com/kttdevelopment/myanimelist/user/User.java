package com.kttdevelopment.myanimelist.user;

import com.kttdevelopment.myanimelist.property.IDN;

public abstract class User implements IDN {

    public abstract String getPictureURL();

    public abstract String getGender();

    public abstract long getBirthday();

    public abstract String getLocation();

    public abstract long getJoinedAt();

    public abstract UserAnimeStatistics getAnimeStatistics();

    public abstract String getTimeZone();

    public abstract boolean isSupporter();

}
