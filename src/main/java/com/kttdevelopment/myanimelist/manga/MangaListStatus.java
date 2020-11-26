package com.kttdevelopment.myanimelist.manga;


import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.property.ListStatus;

public interface MangaListStatus extends ListStatus {

    MangaStatus getStatus();

    int getVolumesRead();

    int getChaptersRead();

    boolean isRereading();

}
