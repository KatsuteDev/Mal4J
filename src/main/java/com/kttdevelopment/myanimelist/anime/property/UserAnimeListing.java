package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.AnimeListStatus;

/**
 * Represents a user Anime listing.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class UserAnimeListing implements AnimePreviewable{

    /**
     * Returns the Anime list status.
     *
     * @return list status
     *
     * @see AnimeListStatus
     * @since 1.0.0
     */
    public abstract AnimeListStatus getListStatus();

}
