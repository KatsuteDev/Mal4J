package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.manga.property.MangaType;
import com.kttdevelopment.myanimelist.property.ExtendedPreview;

public abstract class Anime implements ExtendedPreview {

    public abstract AnimeType getAnimeType();

    public abstract AnimeAirStatus getStatus();

    public abstract AnimeListStatus getListStatus();

    public abstract int getEpisodes();

    public abstract StartSeason getStartSeason();

    public abstract Broadcast getBroadcast();

    public abstract AnimeSource getSource();

    public abstract int getAverageEpisodeLength();

    public abstract AnimeRating getRating();

    public abstract AnimeRecommendation[] getRecommendations();

    public abstract Studio[] getStudios();

    public abstract AnimeStatistics getStatistics();

}
