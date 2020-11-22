package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.property.ExtendedPreview;
import com.kttdevelopment.myanimelist.anime.property.Studio;

public abstract class Anime extends AnimePreview implements ExtendedPreview {

    public abstract AnimeType getAnimeType();

    public abstract AnimeStatus getStatus();

    public abstract AnimeListing getListStatus();

    public abstract int getEpisodes();

    public abstract StartSeason getStartSeason();

    public abstract String getSource();

    public abstract int getAverageEpisodeLength();

    public abstract String getRating();

    public abstract AnimeRecommendation[] getRecommendations();

    public abstract Studio[] getStudios();

}
