package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.AlternativeTitles;
import com.kttdevelopment.myanimelist.anime.AnimeRelation;
import com.kttdevelopment.myanimelist.anime.property.Genre;
import com.kttdevelopment.myanimelist.manga.MangaRelation;

public interface ExtendedPreview extends Preview {

    AlternativeTitles AlternativeTitles();

    long getStartDate();

    long getEndDate();

    String getSynopsis();

    int getMeanRating();

    int getRank();

    int getPopularity();

    int getUserListingCount();

    int getUserScoringCount();

    String getNSFW();

    long getCreatedAt();

    long getUpdatedAt();

    Genre[] getGenres();

    Picture[] getPictures();

    String getBackground();

    AnimeRelation[] getRelatedAnime();

    MangaRelation[] getRelatedManga();

}
