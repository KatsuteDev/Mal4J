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

package com.kttdevelopment.mal4j.manga;


import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.manga.property.*;
import com.kttdevelopment.mal4j.property.ListStatus;
import com.kttdevelopment.mal4j.query.MangaListUpdate;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put</a> <br>
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_mangalist_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_mangalist_get</a> <br>
 * Represents a user's Anime list status.
 *
 * @see Manga#getListStatus()
 * @see MyAnimeList#getUserMangaListing()
 * @see MyAnimeList#getUserMangaListing(String)
 * @see MyAnimeList#updateMangaListing(long)
 * @see ListStatus
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaListStatus implements ListStatus<MangaStatus>,MangaRetrievable,MangaPreviewRetrievable {

    // API methods

    /**
     * Returns the total amount of volumes read.
     *
     * @return total volumes read
     *
     * @since 1.0.0
     */
    public abstract Integer getVolumesRead();

    /**
     * Returns the total amount of chapters read.
     *
     * @return total chapters read
     *
     * @since 1.0.0
     */
    public abstract Integer getChaptersRead();

    /**
     * Returns if the user is rereading.
     *
     * @return rereading
     *
     * @since 1.0.0
     */
    public abstract Boolean isRereading();

    /**
     * Returns the total times reread.
     *
     * @return times reread
     *
     * @since 1.0.0
     */
    public abstract Integer getTimesReread();

    /**
     * Returns the reread value.
     *
     * @return reread value
     *
     * @see RereadValue
     * @since 1.0.0
     */
    public abstract RereadValue getRereadValue();

    // additional methods

    /**
     * Creates a Manga list update object. Used to change the Manga list status.
     *
     * @return list updater
     *
     * @see MangaListUpdate
     * @since 1.0.0
     */
    public abstract MangaListUpdate edit();

}
