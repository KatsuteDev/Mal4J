/*
 * Copyright (C) 2021 Ktt Development
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

package com.kttdevelopment.mal4j.query;

import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import com.kttdevelopment.mal4j.anime.property.AnimeStatus;
import com.kttdevelopment.mal4j.anime.property.RewatchValue;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_my_list_status_put</a> <br>
 * Represents an Anime list update.
 *
 * @see com.kttdevelopment.mal4j.MyAnimeList#updateAnimeListing(long)
 * @see ListUpdate
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListUpdate extends ListUpdate<AnimeListUpdate,AnimeListStatus,AnimeStatus> {

    protected Boolean rewatching;
    protected Integer watchedEpisodes, timesRewatched;
    protected RewatchValue rewatchValue;

    /**
     * Creates an Anime list update. Applications do not use this constructor.
     *
     * @param id Anime id
     *
     * @see com.kttdevelopment.mal4j.MyAnimeList#updateAnimeListing(long)
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
     * @return list update
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
     * @see RewatchValue
     * @since 1.0.0
     */
    public final AnimeListUpdate rewatchValue(final RewatchValue rewatchValue){
        this.rewatchValue = rewatchValue;
        return this;
    }


}
