package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.property.FullMediaItem;

public abstract class Anime extends AnimePreview implements FullMediaItem<AnimeType,AnimeAirStatus,AnimeListStatus,AnimeRecommendation,AnimeStatistics> {

    // additional methods

    @Override
    public final Anime getAnime() {
        return this;
    }

}
