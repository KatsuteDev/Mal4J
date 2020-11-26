package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.Statistics;

public abstract class MangaStatistics extends Statistics<MangaStatus> {

    public abstract int getReading();

    public abstract int getCompleted();

    public abstract int getOnHold();

    public abstract int getDropped();

    public abstract int getPlanToRead();

}
