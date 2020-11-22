package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.manga.property.MangaListStatus;
import com.kttdevelopment.myanimelist.property.Listing;

public abstract class MangaListing implements MangaListStatus, Listing {

   public abstract int getTimesReread();

    public abstract int getRereadValue();
}
