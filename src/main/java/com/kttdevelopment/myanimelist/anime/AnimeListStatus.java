package com.kttdevelopment.myanimelist.anime;


import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import com.kttdevelopment.myanimelist.property.ListStatus;
import com.kttdevelopment.myanimelist.query.AnimeListUpdate;

@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListStatus implements ListStatus<AnimeStatus> {

    // API methods

    public abstract int getWatchedEpisodes();

    public abstract boolean isRewatching();

    public abstract int getTimesRewatched();

    public abstract int getRewatchValue();

    // additional methods

    public abstract AnimeListUpdate edit();

}
