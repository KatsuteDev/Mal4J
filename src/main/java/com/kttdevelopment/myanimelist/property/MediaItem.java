package com.kttdevelopment.myanimelist.property;

/**
 * Indicates that the object contains these media fields.
 *
 * @param <MediaType> media type enum
 * @param <Status> status type enum
 * @param <ListStatus> list status object
 *
 * @see FullMediaItem
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface MediaItem<MediaType extends Enum<?>,Status extends Enum<?>,ListStatus extends com.kttdevelopment.myanimelist.property.ListStatus<?>> extends ID {

    /**
     * Returns the title.
     *
     * @return title
     *
     * @since 1.0.0
     */
    String getTitle();

    /**
     * Returns the main picture.
     *
     * @return main picture
     *
     * @see Picture
     * @since 1.0.0
     */
    Picture getMainPicture();

    /**
     * Returns the alternative titles.
     *
     * @return alternative titles
     *
     * @see AlternativeTitles
     * @since 1.0.0
     */
    AlternativeTitles getAlternativeTitles();

    /**
     * Returns the start date.
     *
     * @return start date
     *
     * @see #getEndDate()
     * @since 1.0.0
     */
    long getStartDate();

    /**
     * Returns the end date.
     *
     * @return end date
     *
     * @see #getStartDate()
     * @since 1.0.0
     */
    long getEndDate();

    /**
     * Returns the synopsis.
     *
     * @return synopsis
     *
     * @since 1.0.0
     */
    String getSynopsis();

    /**
     * Returns the average user rating.
     *
     * @return mean rating
     *
     * @since 1.0.0
     */
    float getMeanRating();

    /**
     * Returns the overall rank.
     *
     * @return rank
     *
     * @since 1.0.0
     */
    int getRank();

    /**
     * Returns the popularity.
     *
     * @return popularity
     *
     * @since 1.0.0
     */
    int getPopularity();

    /**
     * Returns how many users have this item on their list.
     *
     * @return user list count
     *
     * @since 1.0.0
     */
    int getUserListingCount();

    /**
     * Returns how many users have this item scored on their list.
     *
     * @return user scoring count
     *
     * @since 1.0.0
     */
    int getUserScoringCount();

    /**
     * Returns the NSFW rating.
     *
     * @return nsfw
     *
     * @see NSFW
     * @since 1.0.0
     */
    NSFW getNSFW();

    /**
     * Returns the genres.
     *
     * @return genres
     *
     * @since 1.0.0
     */
    Genre[] getGenres();

    /**
     * Returns when this was created.
     *
     * @return creation time
     *
     * @see #getUpdatedAt()
     * @since 1.0.0
     */
    long getCreatedAt();

    /**
     * Returns when this was last updated.
     *
     * @return updated time
     *
     * @see #getCreatedAt()
     * @since 1.0.0
     */
    long getUpdatedAt();

    /**
     * Returns the media type.
     *
     * @return media type
     *
     * @see com.kttdevelopment.myanimelist.anime.property.AnimeType
     * @see com.kttdevelopment.myanimelist.manga.property.MangaType
     * @since 1.0.0
     */
    MediaType getType();

    /**
     * Returns the status.
     *
     * @return status
     *
     * @see com.kttdevelopment.myanimelist.anime.property.AnimeStatus
     * @see com.kttdevelopment.myanimelist.manga.property.MangaStatus
     */
    Status getStatus();

    /**
     * Returns the user's list status.
     *
     * @return list status
     *
     * @see com.kttdevelopment.myanimelist.anime.AnimeListStatus
     * @see com.kttdevelopment.myanimelist.manga.MangaListStatus
     * @since 1.0.0
     */
    ListStatus getListStatus();

}
