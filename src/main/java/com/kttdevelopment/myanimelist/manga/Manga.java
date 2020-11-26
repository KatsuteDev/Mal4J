package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.ExtendedPreview;

public abstract class Manga implements ExtendedPreview {

    public abstract MangaType getMangaType();

    public abstract MangaPublishStatus getStatus();

    public abstract MangaListStatus getListStatus();

    public abstract int getVolumes();

    public abstract int getChapters();

    public abstract Author[] getAuthors();

    public abstract MangaRecommendation[] getRecommendations();

    public abstract Publisher[] getSerialization();

    public abstract MangaStatistics getStatistics();

}
