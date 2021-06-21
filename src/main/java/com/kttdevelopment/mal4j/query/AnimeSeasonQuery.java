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
import com.kttdevelopment.mal4j.anime.property.AnimeSeasonSort;
import com.kttdevelopment.mal4j.anime.property.time.Season;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_season_year_season_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_season_year_season_get</a> <br>
 * Represents an Anime season query.
 *
 * @see MyAnimeList#getAnimeSeason(int, Season)
 * @see FieldQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeSeasonQuery extends FieldQuery<AnimeSeasonQuery,AnimePreview> implements NSFW<AnimeSeasonQuery> {

    protected final int year;
    protected final Season season;
    protected AnimeSeasonSort sort;
    protected Boolean nsfw;

    /**
     * Creates an Anime season query. Applications do not use this constructor.
     *
     * @param year year
     * @param season season
     *
     * @see MyAnimeList#getAnimeSeason(int, Season)
     * @see Season
     * @since 1.0.0
     */
    public AnimeSeasonQuery(final int year, final Season season) {
        this.year = year;
        this.season = season;
    }

    /**
     * Sets the sorting option.
     *
     * @param sort sort
     * @return season query
     *
     * @see AnimeSeasonSort
     * @since 1.0.0
     */
    public final AnimeSeasonQuery sortBy(final AnimeSeasonSort sort){
        this.sort = sort;
        return this;
    }

    @Override
    public final AnimeSeasonQuery includeNSFW(){
        return includeNSFW(true);
    }

    @Override
    public final AnimeSeasonQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
