package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.manga.property.*;

public abstract class Manga extends MangaPreview {

    public abstract MangaType getAnimeType();

    public abstract MangaStatus getStatus();

    public abstract MangaListing getListStatus();

    public abstract int getVolumes();

    public abstract int getChapters();

    public abstract MangaRecommendation[] getRecommendations();

    public abstract Publisher[] getSerialization();


}
