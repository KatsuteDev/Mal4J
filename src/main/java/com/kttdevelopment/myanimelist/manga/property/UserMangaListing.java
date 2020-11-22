package com.kttdevelopment.myanimelist.manga.property;

/**
 * Represents a user Manga listing.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class UserMangaListing implements MangaPreviewable {

    /**
     * Returns the Manga list status.
     *
     * @return list status
     *
     * @see MangaListStatus
     * @since 1.0.0
     */
    public abstract MangaListStatus getListStatus();

}
