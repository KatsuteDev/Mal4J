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

package dev.katsute.mal4j.property;

/**
 * Represents a related media.
 *
 * @see dev.katsute.mal4j.anime.RelatedAnime
 * @see dev.katsute.mal4j.manga.RelatedManga
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public abstract class RelatedMedia {

    /**
     * Returns how the media is related.
     *
     * @return relation type
     *
     * @see #getRawRelationType()
     * @see RelationType
     * @since 1.0.0
     */
    public abstract RelationType getRelationType();

    /**
     * Returns the raw relation type.
     * <br>
     * It is recommended to use {@link #getRelationType()} rather than this method.
     * This method should only be used if the relation type is missing from {@link RelationType}.
     *
     * @return raw relation type
     *
     * @see #getRelationType()
     * @since 2.9.0
     */
    public abstract String getRawRelationType();

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