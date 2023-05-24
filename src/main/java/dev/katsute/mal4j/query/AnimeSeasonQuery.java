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

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.anime.property.AnimeSeasonSort;
import dev.katsute.mal4j.anime.property.time.Season;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_season_year_season_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_season_year_season_get</a> <br>
 * Represents an Anime season query.
 *
 * @see MyAnimeList#getAnimeSeason(int, Season)
 * @see FieldQuery
 * @since 1.0.0
 * @version 2.12.0
 * @author Katsute
 */
public abstract class AnimeSeasonQuery extends FieldQuery<AnimeSeasonQuery,Anime> implements NSFW<AnimeSeasonQuery> {

    protected final int year;
    protected final Season season;
    protected String sort;
    protected Boolean nsfw;

    /**
     * Creates an Anime season query.
     * <br>
     * Do not use this constructor, use {@link MyAnimeList#getAnimeSeason(int, Season)} instead.
     *
     * @param year year
     * @param season season
     *
     * @see MyAnimeList#getAnimeSeason(int, Season)
     * @see Season
     * @since 1.0.0
     */
    public AnimeSeasonQuery(final int year, final Season season){
        this.year = year;
        this.season = season;
    }

    /**
     * Sets the sorting option.
     *
     * @param sort sort
     * @return season query
     *
     * @see #sortBy(String)
     * @see AnimeSeasonSort
     * @since 1.0.0
     */
    public final AnimeSeasonQuery sortBy(final AnimeSeasonSort sort){
        return sortBy(sort.field());
    }

    /**
     * Sets the sorting option.
     * <br>
     * It is recommended to use {@link #sortBy(AnimeSeasonSort)} instead of this method.
     * This method should only be used of the season sort is missing from {@link AnimeSeasonSort}.
     *
     * @param sort raw sort
     * @return season query
     *
     * @see #sortBy(AnimeSeasonSort)
     * @since 2.9.0
     */
    public final AnimeSeasonQuery sortBy(final String sort){
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