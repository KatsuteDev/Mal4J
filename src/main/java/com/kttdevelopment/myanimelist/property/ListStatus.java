package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.query.property.Priority;

/**
 * Indicates that the object is a list status.
 *
 * @param <Status> status state type
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface ListStatus<Status extends Enum<?>> {

    /**
     * Returns the status of the listing.
     *
     * @return status
     *
     * @see com.kttdevelopment.myanimelist.anime.property.AnimeStatus
     * @see com.kttdevelopment.myanimelist.manga.property.MangaStatus
     * @since 1.0.0
     */
    Status getStatus();

    /**
     * Returns the score of the listing.
     *
     * @return score
     *
     * @since 1.0.0
     */
    int getScore();

    /**
     * Returns the start date for the listing.
     *
     * @return start date
     *
     * @see #getFinishDate()
     * @since 1.0.0
     */
    long getStartDate();

    /**
     * Returns the finish date for the listing.
     *
     * @return finish date
     *
     * @see #getStartDate()
     * @since 1.0.0
     */
    long getFinishDate();

    /**
     * Returns the priority for the listing.
     *
     * @return priority
     *
     * @see Priority
     * @since 1.0.0
     */
    Priority getPriority();

    /**
     * Returns the tags for the listing.
     *
     * @return tags
     *
     * @since 1.0.0
     */
    String[] getTags();

    /**
     * Returns the comments for the listing.
     *
     * @return comments
     *
     * @since 1.0.0
     */
    String getComments();

    /**
     * Returns when the listing was last updated.
     *
     * @return last updated
     *
     * @since 1.0.0
     */
    long getUpdatedAt();

}
