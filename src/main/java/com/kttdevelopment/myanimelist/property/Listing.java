package com.kttdevelopment.myanimelist.property;

/**
 * Represents a listing.
 *
 * @see com.kttdevelopment.myanimelist.anime.AnimeListing
 * @see com.kttdevelopment.myanimelist.manga.MangaListing
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Listing implements ListStatus{

    /**
     * Returns the listing priority.
     *
     * @return priority
     *
     * @since 1.0.0
     */
    public abstract int getPriority();

    /**
     * Returns the listing tags.
     *
     * @return tags
     *
     * @since 1.0.0
     */
    public abstract String[] getTags();

    /**
     * Returns the listing comments.
     *
     * @return comments
     *
     * @since 1.0.0
     */
    public abstract String getComments();

}
