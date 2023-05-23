/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package dev.katsute.mal4j.anime;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.anime.property.AnimeRetrievable;
import dev.katsute.mal4j.anime.property.AnimeStatus;
import dev.katsute.mal4j.anime.property.RewatchValue;
import dev.katsute.mal4j.property.Editable;
import dev.katsute.mal4j.property.ListStatus;
import dev.katsute.mal4j.query.AnimeListUpdate;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put</a> <br>
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get</a> <br>
 * Represents a user's Anime list status.
 *
 * @see Anime#getListStatus()
 * @see MyAnimeList#getUserAnimeListing()
 * @see MyAnimeList#getUserAnimeListing(String)
 * @see MyAnimeList#updateAnimeListing(long)
 * @see ListStatus
 * @since 1.0.0
 * @version 2.12.0
 * @author Katsute
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListStatus implements ListStatus<AnimeStatus>, AnimeRetrievable, Editable<AnimeListUpdate> {

    // API methods

    /**
     * Returns the total amount of watched episodes.
     *
     * @return total watched episodes
     *
     * @since 1.0.0
     */
    public abstract Integer getWatchedEpisodes();

    /**
     * Returns if the user is rewatching.
     *
     * @return rewatching
     *
     * @since 1.0.0
     */
    public abstract Boolean isRewatching();

    /**
     * Returns the total times rewatched.
     *
     * @return times rewatched
     *
     * @since 1.0.0
     */
    public abstract Integer getTimesRewatched();

    /**
     * Returns the rewatch value.
     *
     * @return rewatch value
     *
     * @see #getRawRewatchValue()
     * @see RewatchValue
     * @since 1.0.0
     */
    public abstract RewatchValue getRewatchValue();

    /**
     * Returns the raw rewatch value.
     * <br>
     * It is recommended to use {@link #getRewatchValue()} and {@link RewatchValue#value()} rather than this method.
     * This method should only be used if the rewatch value is missing from {@link RewatchValue}.
     *
     * @return raw rewatch value
     *
     * @see #getRewatchValue()
     * @since 2.9.0
     */
    public abstract Integer getRawRewatchValue();

    // additional methods

    /**
     * Creates an Anime list update object. Used to change the Anime list status.
     *
     * @return list updater
     *
     * @see AnimeListUpdate
     * @since 1.0.0
     */
    @Override
    public abstract AnimeListUpdate edit();

}