package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.RelatedAnime;
import com.kttdevelopment.myanimelist.manga.RelatedManga;

/**
 * Indicates that the object contains these media fields.
 *
 * @param <MediaType> media type enum
 * @param <Status> status type enum
 * @param <ListStatus> list status object
 * @param <Recommendation> recommendation object
 * @param <Statistics> statistics object
 *
 * @see MediaItem
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface FullMediaItem<MediaType extends Enum<?>,Status extends Enum<?>,ListStatus extends com.kttdevelopment.myanimelist.property.ListStatus<?>,Recommendation extends com.kttdevelopment.myanimelist.property.Recommendation,Statistics extends com.kttdevelopment.myanimelist.property.Statistics> extends MediaItem<MediaType,Status,ListStatus> {

    /**
     * Returns the pictures for the media item.
     *
     * @return pictures
     *
     * @see Picture
     * @see MediaItem#getMainPicture()
     * @since 1.0.0
     */
    Picture[] getPictures();

    /**
     * Returns the background information.
     *
     * @return background
     *
     * @since 1.0.0
     */
    String getBackground();

    /**
     * Returns a list of related Anime.
     *
     * @return related Anime
     *
     * @see RelatedAnime
     * @since 1.0.0
     */
    RelatedAnime[] getRelatedAnime();

    /**
     * Returns a list of related Manga.
     *
     * @return related Manga
     *
     * @see RelatedManga
     * @since 1.0.0
     */
    RelatedManga[] getRelatedManga();

    /**
     * Returns a list of recommendations.
     *
     * @return recommendations
     *
     * @see com.kttdevelopment.myanimelist.anime.AnimeRecommendation
     * @see com.kttdevelopment.myanimelist.manga.MangaRecommendation
     * @since 1.0.0
     */
    Recommendation[] getRecommendations();

    /**
     * Returns the user statistics.
     *
     * @return statistics
     *
     * @see com.kttdevelopment.myanimelist.property.Statistics
     * @see com.kttdevelopment.myanimelist.anime.property.AnimeStatistics
     * @see com.kttdevelopment.myanimelist.manga.property.MangaStatistics
     * @since 1.0.0
     */
    Statistics getStatistics();

}
