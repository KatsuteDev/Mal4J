package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.FullMediaItem;
import com.kttdevelopment.myanimelist.property.MediaItem;

public abstract class Manga extends MangaPreview implements FullMediaItem<MangaType,MangaPublishStatus,MangaListStatus,MangaRecommendation,MangaStatistics> {

    // API methods

    // API doesn't return this
    @Override @Deprecated
    public final MangaStatistics getStatistics() {
        return null;
    }

    public abstract Publisher[] getSerialization();

    // additional methods

    @Override
    public final Manga getManga() {
        return this;
    }

}
