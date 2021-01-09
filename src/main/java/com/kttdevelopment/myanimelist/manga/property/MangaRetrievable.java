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

package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.manga.Manga;

/**
 * Indicates that a Manga can be retrieved from the object.
 *
 * @see Manga
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface MangaRetrievable {

    /**
     * Returns the Manga.
     *
     * @return Manga
     *
     * @see Manga
     * @since 1.0.0
     */
    Manga getManga();

}
