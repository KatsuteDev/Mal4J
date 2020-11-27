package com.kttdevelopment.myanimelist.manga;


import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.property.ListStatus;

public abstract class MangaListStatus implements ListStatus<MangaStatus> {

    public abstract int getVolumesRead();

    public abstract int getChaptersRead();

    public abstract boolean isRereading();

    public abstract int getTimesReread();

    public abstract int getRereadValue();

}
