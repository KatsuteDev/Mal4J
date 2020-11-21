package com.kttdevelopment.myanimelist.manga;

public abstract class MangaListing {

    public abstract Manga getManga();

    public abstract MangaStatus getStatus();

    public abstract boolean isRereading();

    public abstract int getVolumesRead();

    public abstract int getChaptersRead();

    public abstract int getScore();

    // todo: convert string date to millis
    public abstract long getUpdatedAt();

}
