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

package dev.katsute.mal4j.manga.property;

import dev.katsute.mal4j.property.Statistics;

/**
 * Represents Manga statistics.
 *
 * @deprecated Does not exist in the API currently
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public abstract class MangaStatistics extends Statistics {

    /**
     * Returns the total users reading.
     *
     * @return total users reading
     *
     * @since 1.0.0
     */
    public abstract Integer getReading();

    /**
     * Returns the total users completed.
     *
     * @return total users completed
     *
     * @since 1.0.0
     */
    public abstract Integer getCompleted();

    /**
     * Returns the total users on hold.
     *
     * @return total users on hold
     *
     * @since 1.0.0
     */
    public abstract Integer getOnHold();

     /**
     * Returns the total users dropped.
     *
     * @return total users dropped
     *
     * @since 1.0.0
     */
    public abstract Integer getDropped();

     /**
     * Returns the total users planning to read.
     *
     * @return total users planning to read
     *
     * @since 1.0.0
     */
    public abstract Integer getPlanToRead();

}