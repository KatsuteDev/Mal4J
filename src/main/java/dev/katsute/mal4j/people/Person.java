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

/**
 * Represents a person.
 *
 * @since 3.2.0
 * @version 3.2.0
 * @author Katsute
 */
public abstract class Person implements ID {

    /**
     * Returns the first name.
     *
     * @return first name
     *
     * @see #getLastName()
     * @since 3.2.0
     */
    public abstract String getFirstName();

    /**
     * Returns the last name.
     *
     * @return last name
     *
     * @see #getFirstName()
     * @since 3.2.0
     */
    public abstract String getLastName();

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

}