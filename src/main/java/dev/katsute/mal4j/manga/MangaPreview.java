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

package dev.katsute.mal4j.manga;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.manga.property.*;
import dev.katsute.mal4j.property.MediaItem;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get</a> <br>
 * Represents a Manga.
 *
 * @see MyAnimeList#getManga()
 * @see Manga
 * @see MediaItem
 * @since 1.0.0
 * @version 2.12.0
 * @author Katsute
 */
public abstract class MangaPreview implements MediaItem<MangaType,MangaPublishStatus,MangaListStatus> {

    /**
     * Returns the total amount of volumes.
     *
     * @return total volumes
     *
     * @see #getChapters()
     * @since 1.0.0
     */
    public abstract Integer getVolumes();

    /**
     * Returns the total amount of chapters.
     *
     * @return total chapters
     *
     * @see #getVolumes()
     * @since 1.0.0
     */
    public abstract Integer getChapters();

    /**
     * Returns the authors.
     *
     * @return Manga authors
     *
     * @see Author
     * @since 1.0.0
     */
    public abstract Author[] getAuthors();

}
