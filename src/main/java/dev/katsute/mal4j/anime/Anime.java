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

import dev.katsute.mal4j.anime.property.*;
import dev.katsute.mal4j.property.FullMediaItem;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_get</a> <br>
 * Represents an Anime.
 *
 * @see dev.katsute.mal4j.MyAnimeList#getAnime(long)
 * @see dev.katsute.mal4j.MyAnimeList#getAnime(long, String...)
 * @see AnimePreview
 * @see FullMediaItem
 * @since 1.0.0
 * @version 2.12.0
 * @author Katsute
 */
public abstract class Anime extends AnimePreview implements FullMediaItem<AnimeType,AnimeAirStatus,AnimeListStatus,AnimeRecommendation,AnimeStatistics> {

    /**
     * Returns the opening themes.
     *
     * @return opening themes
     *
     * @see OpeningTheme
     * @since 1.0.0
     */
    public abstract OpeningTheme[] getOpeningThemes();

    /**
     * Returns the ending themes.
     *
     * @return ending themes
     *
     * @see EndingTheme
     * @since 1.0.0
     */
    public abstract EndingTheme[] getEndingThemes();

    /**
     * Returns the PVs and trailers.
     *
     * @return promotional videos and trailers
     *
     * @see Video
     * @since 2.10.0
     */
    public abstract Video[] getVideos();

}
