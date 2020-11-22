package com.kttdevelopment.myanimelist.property;

/**
 * Represents a limited list status.
 *
 * @see com.kttdevelopment.myanimelist.anime.property.AnimeListStatus
 * @see com.kttdevelopment.myanimelist.manga.property.MangaListStatus
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface ListStatus {

    /**
     * Returns the score of the listing.
     *
     * @return score
     *
     * @since 1.0.0
     */
    int getScore();

    /**
     * Returns when the listing was last updated in milliseconds.
     *
     * @return last updated
     *
     * @since 1.0.0
     */
    long getUpdatedAt();

}
