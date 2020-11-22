package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.AnimeListStatus;
import com.kttdevelopment.myanimelist.property.Listing;

@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListing implements AnimeListStatus, Listing {

    public abstract int getTimesRewatched();

    public abstract int getRewatchValue();

}
