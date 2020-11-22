package com.kttdevelopment.myanimelist.manga.property;


public interface MangaListStatus {

    MangaStatus getStatus();

    int getScore();

    int getVolumesRead();

    int getChaptersRead();

    boolean isRereading();

    long getUpdatedAt();

}
