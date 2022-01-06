/*
 * Copyright (C) 2021-2022 Katsute <https://github.com/Katsute>
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
import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import com.kttdevelopment.mal4j.anime.property.AnimeSort;
import com.kttdevelopment.mal4j.anime.property.AnimeStatus;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get</a> <br>
 * Represents a user Anime list query.
 *
 * @see MyAnimeList#getUserAnimeListing()
 * @see MyAnimeList#getUserAnimeListing(String)
 * @see FieldQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public abstract class UserAnimeListQuery extends FieldQuery<UserAnimeListQuery,AnimeListStatus> implements NSFW<UserAnimeListQuery> {

    protected final String username;
    protected AnimeSort sort;
    protected AnimeStatus status;
    protected Boolean nsfw;

    /**
     * Creates a user Anime search query. Applications do not use this constructor.
     *
     * @param username username
     *
     * @see MyAnimeList#getUserAnimeListing()
     * @see MyAnimeList#getUserAnimeListing(String)
     * @since 1.0.0
     */
    public UserAnimeListQuery(final String username) {
        this.username = username;
    }

    /**
     * Sets the sorting option.
     *
     * @param sort sort
     * @return list query
     *
     * @see AnimeSort
     * @since 1.0.0
     */
    public final UserAnimeListQuery sortBy(final AnimeSort sort){
        this.sort = sort;
        return this;
    }

    /**
     * Sets the status filter.
     *
     * @param status status
     * @return list query
     *
     * @since 1.0.0
     */
    public final UserAnimeListQuery withStatus(final AnimeStatus status){
        this.status = status;
        return this;
    }

    @Override
    public final UserAnimeListQuery includeNSFW(){
        return includeNSFW(true);
    }

    @Override
    public final UserAnimeListQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
