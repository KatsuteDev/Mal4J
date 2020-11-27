package com.kttdevelopment.myanimelist.property;

public interface MediaItem<MediaType extends Enum<?>,Status extends Enum<?>,ListStatus extends com.kttdevelopment.myanimelist.property.ListStatus<?>> extends ID {

    String getTitle();

    Picture getMainPicture();

    AlternativeTitles AlternativeTitles();

    long getStartDate();

    long getEndDate();

    String getSynopsis();

    float getMeanRating();

    int getRank();

    int getPopularity();

    int getUserListingCount();

    int getUserScoringCount();

    NSFW getNSFW();

    Genre[] getGenres();

    long getCreatedAt();

    long getUpdatedAt();

    MediaType getType();

    Status getStatus();

    ListStatus getListStatus();

}
