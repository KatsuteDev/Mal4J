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

import dev.katsute.mal4j.manga.MangaListStatus;
import dev.katsute.mal4j.manga.property.MangaStatus;
import dev.katsute.mal4j.manga.property.RereadValue;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put</a> <br>
 * Represents a Manga list update.
 *
 * @see dev.katsute.mal4j.MyAnimeList#updateMangaListing(long)
 * @see ListUpdate
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public abstract class MangaListUpdate extends ListUpdate<MangaListUpdate,MangaListStatus,MangaStatus> {

    protected Boolean rereading;
    protected Integer volumesRead, chaptersRead, timesReread;
    protected Integer rereadValue;

    /**
     * Creates a Manga list update.
     * <br>
     * This constructor should not be used, use {@link dev.katsute.mal4j.MyAnimeList#updateMangaListing(long)} instead.
     *
     * @param id Manga id
     *
     * @see dev.katsute.mal4j.MyAnimeList#updateMangaListing(long)
     * @since 1.0.0
     */
    public MangaListUpdate(final long id){
        super(id);
    }

    /**
     * Sets the rereading status.
     *
     * @param rereading rereading
     * @return list update
     *
     * @since 1.0.0
     */
    public final MangaListUpdate rereading(final boolean rereading){
        this.rereading = rereading;
        return this;
    }

    /**
     * Sets the volumes read.
     *
     * @param volumesRead volumes read
     * @return list update
     *
     * @since 1.0.0
     */
    public final MangaListUpdate volumesRead(final int volumesRead){
        this.volumesRead = volumesRead;
        return this;
    }

    /**
     * Sets the chapters read.
     *
     * @param chaptersRead chapters read
     * @return list update
     *
     * @since 1.0.0
     */
    public final MangaListUpdate chaptersRead(final int chaptersRead){
        this.chaptersRead = chaptersRead;
        return this;
    }

    /**
     * Sets the times reread.
     *
     * @param timesReread times reread
     * @return list update
     *
     * @since 1.0.0
     */
    public final MangaListUpdate timesReread(final int timesReread){
        this.timesReread = timesReread;
        return this;
    }

    /**
     * Sets the reread value.
     *
     * @param rereadValue reread value
     * @return list update
     *
     * @see #rereadValue(Integer)
     * @see RereadValue
     * @since 1.0.0
     */
    public final MangaListUpdate rereadValue(final RereadValue rereadValue){
        return rereadValue(rereadValue.value());
    }

    /**
     * Sets the reread value.
     * <br>
     * It is recommended to use {@link #rereadValue(RereadValue)} rather than this method.
     * This method should only be used if the reread value is missing from {@link RereadValue}.
     *
     * @param rereadValue reread value
     * @return list update
     *
     * @see #rereadValue(RereadValue)
     * @since 2.9.0
     */
    public final MangaListUpdate rereadValue(final Integer rereadValue){
        this.rereadValue = rereadValue;
        return this;
    }

}