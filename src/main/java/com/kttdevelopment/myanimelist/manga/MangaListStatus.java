package com.kttdevelopment.myanimelist.manga;


import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.property.ListStatus;
import com.kttdevelopment.myanimelist.query.MangaListUpdate;

public abstract class MangaListStatus implements ListStatus<MangaStatus> {

    // API methods

    public abstract int getVolumesRead();

    public abstract int getChaptersRead();

    public abstract boolean isRereading();

    public abstract int getTimesReread();

    public abstract int getRereadValue();

    // additional methods

    public abstract MangaListUpdate edit();

}
