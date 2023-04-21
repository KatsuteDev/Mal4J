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
 * Represents the alternative titles of a media item.
 *
 * @see MediaItem#getAlternativeTitles()
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public abstract class AlternativeTitles {

    /**
     * Returns the synonyms for the title.
     *
     * @return synonyms
     *
     * @since 1.0.0
     */
    public abstract String[] getSynonyms();

    /**
     * Returns the English title.
     *
     * @return English title
     *
     * @since 1.0.0
     */
    public abstract String getEnglish();

    /**
     * Returns the Japanese title.
     *
     * @return Japanese title
     *
     * @since 1.0.0
     */
    public abstract String getJapanese();

}