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
package com.kttdevelopment.mal4j.user.property;

/**
 * Represents a user affinity.
 *
 * @param <T> affinity shared type
 *
 * @see AnimeAffinity
 * @see MangaAffinity
 * @since 3.0.0
 * @version 3.0.0
 * @author Ktt Development
 */
public abstract class Affinity<T> {

    /**
     * Returns the results that are shared between both users.
     *
     * @return shared
     *
     * @since 3.0.0
     */
    public abstract T[] getShared();

    /**
     * Returns the total amount shared.
     *
     * @return shared count
     *
     * @since 3.0.0
     */
    public abstract int getSharedCount();

    /**
     * Returns the affinity value
     *
     * @return affinity
     *
     * @since 3.0.0
     */
    public abstract float getAffinity();

}
