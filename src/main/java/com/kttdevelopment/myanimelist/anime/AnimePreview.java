package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.property.MediaItem;

public abstract class AnimePreview implements AnimeRetrievable,MediaItem<AnimeType,AnimeAirStatus,AnimeListStatus> {

    // API methods

    public abstract int getEpisodes();

    public abstract StartSeason getStartSeason();

    public abstract Broadcast getBroadcast();

    public abstract AnimeSource getSource();

    public abstract int getAverageEpisodeLength();

    public abstract AnimeRating getRating();

    public abstract Studio[] getStudios();

}
