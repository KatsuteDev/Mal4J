package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.manga.Manga;
import com.kttdevelopment.myanimelist.manga.MangaStatus;

public abstract class AnimeListing {

    public abstract Anime getManga();

    public abstract AnimeStatus getStatus();

    public abstract boolean isRereading();

    public abstract int getVolumesRead();

    public abstract int getChaptersRead();

    public abstract int getScore();

    // todo: convert string date to millis
    public abstract long getUpdatedAt();

}
