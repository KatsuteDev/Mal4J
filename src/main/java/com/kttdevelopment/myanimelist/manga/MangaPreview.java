package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.manga.property.Author;
import com.kttdevelopment.myanimelist.manga.property.MangaPublishStatus;
import com.kttdevelopment.myanimelist.manga.property.MangaRetrievable;
import com.kttdevelopment.myanimelist.manga.property.MangaType;
import com.kttdevelopment.myanimelist.property.MediaItem;

public abstract class MangaPreview implements MangaRetrievable,MediaItem<MangaType,MangaPublishStatus,MangaListStatus> {

    public abstract int getVolumes();

    public abstract int getChapters();

    public abstract Author[] getAuthors();

}
