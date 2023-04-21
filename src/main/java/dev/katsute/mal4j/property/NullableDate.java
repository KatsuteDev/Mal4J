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

import java.util.Date;

/**
 * A date with an <b>optional</b> year, month, and day. Does not convert around timezones, will return the date exactly how it is written on the website.
 *
 * @see java.util.Date
 * @since 3.0.0
 * @version 3.0.0
 * @author Katsute
 */
public abstract class NullableDate {

    /**
     * Returns the 4 digit year (19XX-20XX). Null if the year is missing
     *
     * @return year
     *
     * @since 3.0.0
     */
    public abstract Integer getYear();

    /**
     * Returns the numeric month (1-12). Null if the month is missing.
     *
     * @return month
     *
     * @since 3.0.0
     */
    public abstract Integer getMonth();

    /**
     * Returns the day (1-31). Null if the day is missing.
     *
     * @return day
     *
     * @since 3.0.0
     */
    public abstract Integer getDay();

    /**
     * Returns the date as milliseconds since epoch. If the month or day is null, it will fallback to January 1st. Null if everything is missing.
     *
     * @return milliseconds since epoch
     *
     * @since 3.0.0
     */
    public abstract Long getMillis();

    /**
     * Returns the date as a {@link Date}. If the month or day is null, it will fallback to January 1st. Null if everything is missing.
     *
     * @return date
     *
     * @see Date
     * @since 3.0.0
     */
    public abstract Date getDate();

}