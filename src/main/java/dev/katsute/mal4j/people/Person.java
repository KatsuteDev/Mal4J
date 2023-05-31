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

package dev.katsute.mal4j.people;

import dev.katsute.mal4j.property.ID;
import dev.katsute.mal4j.property.Picture;

import java.util.Date;
import java.util.Map;

/**
 * Represents a person.
 *
 * @see dev.katsute.mal4j.MyAnimeList#getPerson(long)
 * @see dev.katsute.mal4j.MyAnimeList#getPerson(long, String...)
 * @since 3.2.0
 * @version 3.4.0
 * @author Katsute
 */
public abstract class Person implements ID {

    /**
     * Returns the first name.
     *
     * @return first name
     *
     * @see #getLastName()
     * @see #getAlternativeNames()
     * @since 3.2.0
     */
    public abstract String getFirstName();

    /**
     * Returns the last name.
     *
     * @return last name
     *
     * @see #getFirstName()
     * @see #getAlternativeNames()
     * @since 3.2.0
     */
    public abstract String getLastName();

    /**
     * Returns a list of alternative names.
     *
     * @return alternative names
     *
     * @see #getFirstName()
     * @see #getLastName()
     * @since 3.4.0
     */
    public abstract String[] getAlternativeNames();

    /**
     * Returns the birthday.
     *
     * @return birthday
     *
     * @since 3.2.0
     */
    public abstract Date getBirthday();

    /**
     * Returns the main picture.
     *
     * @return main picture
     *
     * @see Picture
     * @since 3.2.0
     */
    public abstract Picture getMainPicture();

    /**
     * Returns the number of favorites.
     *
     * @return favorites
     *
     * @since 3.4.0
     */
    public abstract Integer getFavorites();

    /**
     * Returns the more field.
     *
     * @return more fields
     *
     * @see #getMoreDetails()
     * @since 3.4.0
     */
    public abstract String getMore();

    /**
     * Returns more details as a map. Sorted in the same order as {@link #getMore()}, more content is saved under the <code>*</code> key.
     *
     * @return more fields
     *
     * @see #getMore()
     * @since 3.4.0
     */
    public abstract Map<String,String> getMoreDetails();

}