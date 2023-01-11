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

package dev.katsute.mal4j.anime.property.time;

/**
 * Represents the time. Does not convert around timezones, will return the time exactly how it is written on the website.
 *
 * @see dev.katsute.mal4j.anime.property.Broadcast
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public abstract class Time {

    /**
     * Returns the hour on a 24-hour clock (0-23).
     *
     * @return hour
     *
     * @since 1.0.0
     */
    public abstract Integer getHour();

    /**
     * Returns the hour on a 12-hour clock (1-12).
     *
     * @return hour
     *
     * @since 1.0.0
     */
    public abstract Integer get12Hour();

    /**
     * Returns if the time is AM.
     *
     * @return if time is AM
     *
     * @see #get12Hour()
     * @see #isPM()
     * @since 1.0.0
     */
    public abstract Boolean isAM();

    /**
     * Returns if the time is PM.
     *
     * @return if time is PM
     *
     * @see #get12Hour()
     * @see #isAM()
     * @since 1.0.0
     */
    public abstract Boolean isPM();

    /**
     * Returns the minute (0-59).
     *
     * @return minute
     *
     * @since 1.0.0
     */
    public abstract Integer getMinute();

}
