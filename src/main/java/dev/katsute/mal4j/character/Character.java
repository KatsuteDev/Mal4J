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

package dev.katsute.mal4j.character;

import dev.katsute.mal4j.property.ID;
import dev.katsute.mal4j.property.Picture;

/**
 * Represents a character.
 *
 * @see dev.katsute.mal4j.MyAnimeList#getCharacter(long)
 * @see dev.katsute.mal4j.MyAnimeList#getCharacter(long, String...)
 * @see Character
 * @since 3.1.0
 * @version 3.1.0
 * @author Katsute
 */
public abstract class Character implements ID {

    /**
     * Returns the first name.
     *
     * @return first name
     *
     * @see #getLastName()
     * @see #getAlternativeNames()
     * @since 3.1.0
     */
    public abstract String getFirstName();

    /**
     * Returns the last name.
     *
     * @return last name
     *
     * @see #getFirstName()
     * @see #getAlternativeNames()
     * @since 3.1.0
     */
    public abstract String getLastName();

    /**
     * Returns a list of alternative names.
     *
     * @return alternative names
     *
     * @see #getFirstName()
     * @see #getLastName()
     * @since 3.1.0
     */
    public abstract String[] getAlternativeNames();

    /**
     * Returns the main picture.
     *
     * @return main picture
     *
     * @see Picture
     * @since 3.1.0
     */
    public abstract Picture getMainPicture();

    /**
     * Returns the biography.
     *
     * @return biography
     *
     * @since 3.1.0
     */
    public abstract String getBiography();

    /**
     * Returns a list of which Anime the character has appeared in.
     *
     * @return animeography
     *
     * @see Animeography
     * @since 3.1.0
     */
    public abstract Animeography[] getAnimeography();

}