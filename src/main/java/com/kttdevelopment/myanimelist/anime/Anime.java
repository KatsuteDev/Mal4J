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

package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.property.FullMediaItem;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_get</a> <br>
 * Represents an Anime's full details.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getAnime(long)
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getAnime(long, String...)
 * @see AnimePreview
 * @see FullMediaItem
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Anime extends AnimePreview implements FullMediaItem<AnimeType,AnimeAirStatus,AnimeListStatus,AnimeRecommendation,AnimeStatistics> {

    // additional methods

    @Override
    public final Anime getAnime() {
        return this;
    }

}
