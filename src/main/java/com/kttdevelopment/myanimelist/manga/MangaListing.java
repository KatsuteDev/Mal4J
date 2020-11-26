package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.property.Listing;

public abstract class MangaListing extends Listing implements MangaListStatus {

    public abstract int getTimesReread();

    public abstract int getRereadValue();
}
