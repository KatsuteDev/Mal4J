package com.kttdevelopment.myanimelist.anime;


import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import com.kttdevelopment.myanimelist.property.ListStatus;

@SuppressWarnings("SpellCheckingInspection")
public interface AnimeListStatus extends ListStatus {

    AnimeStatus getStatus();

    int getEpisodesWatched();

    boolean isRewatching();

}
