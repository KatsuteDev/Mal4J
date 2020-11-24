package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.manga.MangaListStatus;

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
