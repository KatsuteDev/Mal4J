package com.kttdevelopment.myanimelist.user;

public abstract class UserMangaStatistics {

    public abstract int getReading();

    public abstract int getCompleted();

    public abstract int getOnHold();

    public abstract int getDropped();

    public abstract int getPlanToRead();

    public abstract int getTotal();

    public abstract double getDaysReading();

    public abstract double getDaysCompleted();

    public abstract double getDaysOnHold();

    public abstract double getDaysDropped();

    public abstract double getDaysPlanToRead();

    public abstract double getTotalDays();

    public abstract int getTotalVolumes();

    public abstract int getTotalChapters();

    public abstract int getTotalReread();

    public abstract double getMeanScore();

}
