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

package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.property.time.Season;

/**
 * Represents the start season for an Anime.
 *
 * @see Anime#getStartSeason()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Develoment
 */
public abstract class StartSeason {

    /**
     * The year that the Anime will start in.
     *
     * @return start year
     *
     * @since 1.0.0
     */
    public abstract int getYear();

    /**
     * The broadcast season that the Anime will start in
     *
     * @return start season
     *
     * @see Season
     * @since 1.0.0
     */
    public abstract Season getSeason();

}
