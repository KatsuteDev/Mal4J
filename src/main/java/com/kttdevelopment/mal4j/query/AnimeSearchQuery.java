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

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.anime.AnimePreview;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_get</a> <br>
 * Represents a Anime search query.
 *
 * @see MyAnimeList#getAnime()
 * @see SearchQuery
 * @since 1.0.0
 * @version 2.2.1
 * @author Ktt Development
 */
public abstract class AnimeSearchQuery extends NSFWSearchQuery<AnimeSearchQuery,AnimePreview> {

    /**
     * Creates an Anime search query. Applications do not use this constructor.
     *
     *
     * @see MyAnimeList#getAnime()
     * @since 1.0.0
     */
    public AnimeSearchQuery() { }

}
