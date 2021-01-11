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

package com.kttdevelopment.mal4j.user;

/**
 * Represents a User's Manga statistics.
 *
 * @deprecated Does not exist in the API currently
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Deveopment
 */
@Deprecated
public abstract class UserMangaStatistics {

/**
     * Returns total items reading.
     *
     * @return total items reading
     *
     * @since 1.0.0
     */
    public abstract int getItemsReading();

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
     * Returns total items planned to read.
     *
     * @return total items planned to read
     *
     * @since 1.0.0
     */
    public abstract int getItemsPlanToRead();

    /**
     * Returns total items.
     *
     * @return total items
     *
     * @since 1.0.0
     */
    public abstract int getItems();

    /**
     * Returns total days read.
     *
     * @return total days read
     *
     * @since 1.0.0
     */
    public abstract float getDaysWatched();

    /**
     * Returns total days reading.
     *
     * @return total days reading
     *
     * @since 1.0.0
     */
    public abstract float getDaysReading();

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
     * Returns total volumes read.
     *
     * @return total volumes
     *
     * @since 1.0.0
     */
    public abstract int getVolumes();

    /**
     * Returns total chapters read.
     *
     * @return total chapters
     *
     * @since 1.0.0
     */
    public abstract int getChapters();

    /**
     * Returns times reread
     *
     * @return times reread
     *
     * @since 1.0.0
     */
    public abstract int getTimesReread();

    /**
     * Returns the average score.
     *
     * @return mean score
     *
     * @since 1.0.0
     */
    public abstract float getMeanScore();

}
