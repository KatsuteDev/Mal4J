package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.AnimeListStatus;
import com.kttdevelopment.myanimelist.property.Listing;

/**
 * Reprsents an Anime listing.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListing extends Listing implements AnimeListStatus {

    /**
     * Returns total times rewatched.
     *
     * @return times rewatched
     *
     * @since 1.0.0
     */
    public abstract int getTimesRewatched();

    /**
     * Returns rewatch value.
     *
     * @return times rewatched
     *
     * @since 1.0.0
     */
    public abstract int getRewatchValue();

}
