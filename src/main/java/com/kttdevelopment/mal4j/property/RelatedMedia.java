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

package com.kttdevelopment.mal4j.property;

/**
 * Represents a related media.
 *
 * @see com.kttdevelopment.mal4j.anime.RelatedAnime
 * @see com.kttdevelopment.mal4j.manga.RelatedManga
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public abstract class RelatedMedia {

    /**
     * Returns how the media is related.
     *
     * @return relation type
     *
     * @see RelationType
     * @since 1.0.0
     */
    public abstract RelationType getRelationType();

    /**
     * Returns how the media is related by its display name.
     *
     * @return relation type
     *
     * @see #getRelationType()
     * @since 1.0.0
     */
    public abstract String getRelationTypeFormat();

}
