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
import dev.katsute.mal4j.anime.property.*;
import dev.katsute.mal4j.anime.property.time.Season;
import dev.katsute.mal4j.property.MediaItem;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_get</a> <br>
 * Represents an Anime.
 *
 * @see MyAnimeList#getAnime()
 * @see MyAnimeList#getAnimeSeason(int, Season)
 * @see MyAnimeList#getAnimeSuggestions()
 * @see Anime
 * @see MediaItem
 * @since 1.0.0
 * @version 2.12.0
 * @author Katsute
 */
public abstract class AnimePreview implements MediaItem<AnimeType,AnimeAirStatus,AnimeListStatus> {

    // API methods

    /**
     * Returns the total amount of episodes.
     *
     * @return total episodes
     *
     * @since 1.0.0
     */
    public abstract Integer getEpisodes();

    /**
     * Returns the start season.
     *
     * @return start season
     *
     * @see StartSeason
     * @since 1.0.0
     */
    public abstract StartSeason getStartSeason();

    /**
     * Returns the broadcast time.
     *
     * @return broadcast time
     *
     * @see Broadcast
     * @since 1.0.0
     */
    public abstract Broadcast getBroadcast();

    /**
     * Returns the source material.
     *
     * @return source material
     *
     * @see #getRawSource()
     * @see AnimeSource
     * @since 1.0.0
     */
    public abstract AnimeSource getSource();

    /**
     * Returns the raw source material.
     * <br>
     * It is recommended to use {@link #getSource()} rather than this method.
     * This method should only be used if the source value is missing from {@link AnimeSource}.
     *
     * @return raw source material
     *
     * @see #getSource()
     * @since 2.9.0
     */
    public abstract String getRawSource();

    /**
     * Returns the average episode length in seconds.
     *
     * @return episode length
     *
     * @since 1.0.0
     */
    public abstract Integer getAverageEpisodeLength();

    /**
     * Returns the TV viewing rating (ex: pg13).
     *
     * @return TV viewing rating
     *
     * @see #getRawRating()
     * @see AnimeRating
     * @since 1.0.0
     */
    public abstract AnimeRating getRating();

    /**
     * Returns the raw rating.
     * <br>
     * It is recommended to use {@link #getRating()} rather than this method.
     * This method should only be used if the rating value is missing from {@link AnimeRating}.
     *
     * @return raw TV viewing rating
     *
     * @see #getRating()
     * @since 2.9.0
     */
    public abstract String getRawRating();

    /**
     * Returns the studios.
     *
     * @return studios
     *
     * @see Studio
     * @since 1.0.0
     */
    public abstract Studio[] getStudios();

}