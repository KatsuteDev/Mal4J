package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.property.Listing;

@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListing extends Listing implements AnimeListStatus {

    public abstract int getTimesRewatched();

    public abstract int getRewatchValue();

}
