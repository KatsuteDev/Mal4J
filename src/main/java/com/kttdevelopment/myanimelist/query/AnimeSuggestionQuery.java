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

package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.anime.Anime;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_suggestions_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_suggestions_get</a> <br>
 * Represents an Anime suggestion query.
 *
 * @see MyAnimeList#getAnimeSuggestions()
 * @see FieldSearchQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeSuggestionQuery extends FieldSearchQuery<AnimeSuggestionQuery,Anime> {

    protected String query;
    protected Boolean nsfw;

    /**
     * Creates an Anime suggestion query. Applications do not use this constructor.
     *
     *
     * @see MyAnimeList#getAnimeSuggestions()
     * @since 1.0.0
     */
    public AnimeSuggestionQuery() { }

    /**
     * Sets the search query.
     *
     * @param query query
     * @return suggestion query
     *
     * @since 1.0.0
     */
    public final AnimeSuggestionQuery withQuery(final String query){
        this.query = query;
        return this;
    }

    /**
     * Sets if the query will return NSFW results.
     *
     * @deprecated The API does not support this option currently
     * @param nsfw nsfw
     * @return suggestion query
     *
     * @since 1.0.0
     */
    @Deprecated
    public final AnimeSuggestionQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
