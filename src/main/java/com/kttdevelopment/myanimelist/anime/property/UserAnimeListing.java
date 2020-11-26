package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.AnimeListStatus;

public abstract class UserAnimeListing implements AnimePreviewable{

    public abstract AnimeListStatus getListStatus();

}
