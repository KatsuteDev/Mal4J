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

package dev.katsute.mal4j.anime.property;

import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.property.Statistics;

/**
 * Represents Anime statistics.
 *
 * @see Anime#getStatistics()
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public abstract class AnimeStatistics extends Statistics {

    /**
     * The total amount of users watching.
     *
     * @return total users watching
     *
     * @since 1.0.0
     */
    public abstract Integer getWatching();

    /**
     * The total amount of users completed.
     *
     * @return total users completed
     *
     * @since 1.0.0
     */
    public abstract Integer getCompleted();

    /**
     * The total amount of users on hold.
     *
     * @return total users on hold
     *
     * @since 1.0.0
     */
    public abstract Integer getOnHold();

    /**
     * The total amount of users dropped.
     *
     * @return total users dropped
     *
     * @since 1.0.0
     */
    public abstract Integer getDropped();

    /**
     * The total amount of users planning to watch.
     *
     * @return total users planning to watch
     *
     * @since 1.0.0
     */
    public abstract Integer getPlanToWatch();

}
