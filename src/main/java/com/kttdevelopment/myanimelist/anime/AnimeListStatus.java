package com.kttdevelopment.myanimelist.anime;


import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import com.kttdevelopment.myanimelist.property.ListStatus;

/**
 * Represents an Anime list status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public interface AnimeListStatus extends ListStatus {

    /**
     * Returns Anime status.
     *
     * @return status
     *
     * @see AnimeStatus
     * @since 1.0.0
     */
    AnimeStatus getStatus();

    /**
     * Returns total episodes watched.
     *
     * @return episodes watched
     *
     * @since 1.0.0
     */
    int getEpisodesWatched();

    /**
     * Returns if rewatching.
     *
     * @return rewatching
     *
     * @since 1.0.0
     */
    boolean isRewatching();

}
