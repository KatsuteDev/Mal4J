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

package dev.katsute.mal4j.query;

import dev.katsute.mal4j.anime.AnimeListStatus;
import dev.katsute.mal4j.anime.property.AnimeStatus;
import dev.katsute.mal4j.anime.property.RewatchValue;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put</a> <br>
 * Represents an Anime list update.
 *
 * @see dev.katsute.mal4j.MyAnimeList#updateAnimeListing(long)
 * @see ListUpdate
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListUpdate extends ListUpdate<AnimeListUpdate,AnimeListStatus,AnimeStatus> {

    protected Boolean rewatching;
    protected Integer watchedEpisodes, timesRewatched;
    protected Integer rewatchValue;

    /**
     * Creates an Anime list update.
     * <br>
     * Do not use this constructor, use {@link dev.katsute.mal4j.MyAnimeList#updateAnimeListing(long)} instead.
     *
     * @param id Anime id
     *
     * @see dev.katsute.mal4j.MyAnimeList#updateAnimeListing(long)
     * @since 1.0.0
     */
    public AnimeListUpdate(final long id){
        super(id);
    }

    /**
     * Sets the rewatching status.
     *
     * @param rewatching rewatching
     * @return list update
     *
     * @since 1.0.0
     */
    public final AnimeListUpdate rewatching(final boolean rewatching){
        this.rewatching = rewatching;
        return this;
    }

    /**
     * Sets the episodes watched.
     *
     * @param watchedEpisodes watched episodes
     * @return list update
     *
     * @since 1.0.0
     */
    public final AnimeListUpdate episodesWatched(final int watchedEpisodes){
        this.watchedEpisodes = watchedEpisodes;
        return this;
    }

    /**
     * Sets the times rewatched.
     *
     * @param timesRewatched times rewatched
     * @return list updateB
     *
     * @since 1.0.0
     */
    public final AnimeListUpdate timesRewatched(final int timesRewatched){
        this.timesRewatched = timesRewatched;
        return this;
    }

    /**
     * Sets the rewatch value.
     *
     * @param rewatchValue rewatch value
     * @return list update
     *
     * @see #rewatchValue(Integer)
     * @see RewatchValue
     * @since 1.0.0
     */
    public final AnimeListUpdate rewatchValue(final RewatchValue rewatchValue){
        return rewatchValue(rewatchValue.value());
    }

    /**
     * Sets the rewatch value.
     * <br>
     * It is recommended to use {@link #rewatchValue(RewatchValue)} rather than this method.
     * This method should only be used if the rewatch value is missing from {@link RewatchValue}.
     *
     * @param rewatchValue raw rewatch value
     * @return list update
     *
     * @see #rewatchValue(RewatchValue)
     * @since 2.9.0
     */
    public final AnimeListUpdate rewatchValue(final Integer rewatchValue){
        this.rewatchValue = rewatchValue;
        return this;
    }

}