package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.Statistics;

public abstract class AnimeStatistics extends Statistics {

    public abstract int getWatching();

    public abstract int getCompleted();

    public abstract int getOnHold();

    public abstract int getDropped();

    public abstract int getPlanToWatch();

}
