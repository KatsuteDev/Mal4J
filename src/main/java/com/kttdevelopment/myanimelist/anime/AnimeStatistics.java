package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import com.kttdevelopment.myanimelist.property.Statistics;

public abstract class AnimeStatistics extends Statistics<AnimeStatus> {

    public abstract int getWatching();

    public abstract int getCompleted();

    public abstract int getOnHold();

    public abstract int getDropped();

    public abstract int getPlanToWatch();

}
