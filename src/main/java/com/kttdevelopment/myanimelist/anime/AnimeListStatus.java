package com.kttdevelopment.myanimelist.anime;


import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import com.kttdevelopment.myanimelist.property.ListStatus;
import com.kttdevelopment.myanimelist.query.AnimeListUpdate;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put</a> <br>
 * Represents a user's Anime list status.
 *
 * @see Anime#getListStatus()
 * @see MyAnimeList#updateAnimeListing(long)
 * @see ListStatus
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListStatus implements ListStatus<AnimeStatus> {

    // API methods

    /**
     * Returns the total amount of watched episodes.
     *
     * @return total watched episodes
     *
     * @since 1.0.0
     */
    public abstract int getWatchedEpisodes();

    /**
     * Returns if the user is rewatching.
     *
     * @return rewatching
     *
     * @since 1.0.0
     */
    public abstract boolean isRewatching();

    /**
     * Returns the total times rewatched.
     *
     * @return times rewatched
     *
     * @since 1.0.0
     */
    public abstract int getTimesRewatched();

    /**
     * Returns the rewatch value.
     *
     * @return rewatch value
     *
     * @since 1.0.0
     */
    public abstract int getRewatchValue();

    // additional methods

    /**
     * Creates an Anime list update object. Used to change the Anime list status.
     *
     * @return list updater
     *
     * @see AnimeListUpdate
     * @since 1.0.0
     */
    public abstract AnimeListUpdate edit();

}
