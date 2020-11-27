package com.kttdevelopment.myanimelist.user;

public abstract class UserAnimeStatistics {

    public abstract int getItemsWatching();

    public abstract int getItemsCompleted();

    public abstract int getItemsOnHold();

    public abstract int getItemsDropped();

    public abstract int getItemsPlanToWatch();

    public abstract int getItems();

    public abstract float getDaysWatched();

    public abstract float getDaysWatching();

    public abstract float getDaysCompleted();

    public abstract float getDaysOnHold();

    public abstract float getDaysDropped();

    public abstract float getDays();

    public abstract int getEpisodes();

    @SuppressWarnings("SpellCheckingInspection")
    public abstract int getTimesRewatched();

    public abstract float getMeanScore();

}
