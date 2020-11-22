package com.kttdevelopment.myanimelist.user;

public abstract class AnimeStatistics {

    public abstract int getWatching();

    public abstract int getCompleted();

    public abstract int getOnHold();

    public abstract int getDropped();

    public abstract int getPlanToWatch();

    public abstract int getTotal();

    public abstract double getDaysWatching();

    public abstract double getDaysCompleted();

    public abstract double getDaysOnHold();

    public abstract double getDaysDropped();

    public abstract double getDaysPlanToWatch();

    public abstract double getTotalDays();

    public abstract int getTotalEpisodes();

    @SuppressWarnings("unused")
    public abstract int getTotalRewatched();

    public abstract double getMeanScore();

}
