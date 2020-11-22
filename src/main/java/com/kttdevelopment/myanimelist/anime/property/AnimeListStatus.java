package com.kttdevelopment.myanimelist.anime.property;

@SuppressWarnings("SpellCheckingInspection")
public interface AnimeListStatus {

    AnimeStatus getStatus();

    int getScore();

    int getEpisodesWatched();

    boolean isRewatching();

    long getUpdatedAt();

}
