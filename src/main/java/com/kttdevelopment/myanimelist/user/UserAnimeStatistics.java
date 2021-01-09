/*
 * Copyright (C) 2021 Ktt Development
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

package com.kttdevelopment.myanimelist.user;

/**
 * Represents a User's Anime statistics.
 *
 * @see User#getAnimeStatistics()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Deveopment
 */
public abstract class UserAnimeStatistics {

    /**
     * Returns total items watching.
     *
     * @return total items watching
     *
     * @since 1.0.0
     */
    public abstract int getItemsWatching();

    /**
     * Returns total items completed.
     *
     * @return total items completed
     *
     * @since 1.0.0
     */
    public abstract int getItemsCompleted();

    /**
     * Returns total items on hold.
     *
     * @return total items on hold
     *
     * @since 1.0.0
     */
    public abstract int getItemsOnHold();

    /**
     * Returns total items dropped.
     *
     * @return total items dropped
     *
     * @since 1.0.0
     */
    public abstract int getItemsDropped();

    /**
     * Returns total items planned to watch.
     *
     * @return total items planned to watch
     *
     * @since 1.0.0
     */
    public abstract int getItemsPlanToWatch();

    /**
     * Returns total items.
     *
     * @return total items
     *
     * @since 1.0.0
     */
    public abstract int getItems();

    /**
     * Returns total days watched.
     *
     * @return total days watched
     *
     * @since 1.0.0
     */
    public abstract float getDaysWatched();

    /**
     * Returns total days watching.
     *
     * @return total days watching
     *
     * @since 1.0.0
     */
    public abstract float getDaysWatching();

    /**
     * Returns total days completed.
     *
     * @return total days completed
     *
     * @since 1.0.0
     */
    public abstract float getDaysCompleted();

    /**
     * Returns total days on hold.
     *
     * @return total days on hold
     *
     * @since 1.0.0
     */
    public abstract float getDaysOnHold();

    /**
     * Returns total days dropped.
     *
     * @return total days dropped
     *
     * @since 1.0.0
     */
    public abstract float getDaysDropped();

    /**
     * Returns total days.
     *
     * @return total days
     *
     * @since 1.0.0
     */
    public abstract float getDays();

    /**
     * Returns total episodes watched.
     *
     * @return total episodes watching
     *
     * @since 1.0.0
     */
    public abstract int getEpisodes();

    /**
     * Returns times rewatched.
     *
     * @return times rewatched
     *
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public abstract int getTimesRewatched();

    /**
     * Returns the average score.
     *
     * @return mean score
     *
     * @since 1.0.0
     */
    public abstract float getMeanScore();

}
