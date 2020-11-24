package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.property.Listing;

/**
 * Represents a Manga listing.
 *
 * @see MangaListStatus
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaListing extends Listing implements MangaListStatus {

    /**
     * Returns total times reread.
     *
     * @return times reread
     *
     * @since 1.0.0
     */
    public abstract int getTimesReread();

    /**
     * Returns reread value.
     *
     * @return reread value
     *
     * @since 1.0.0
     */
    public abstract int getRereadValue();
}
