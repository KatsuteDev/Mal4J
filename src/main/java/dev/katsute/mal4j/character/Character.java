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

package dev.katsute.mal4j.character;

import dev.katsute.mal4j.property.ID;
import dev.katsute.mal4j.property.Picture;

public abstract class Character implements ID {

    public abstract String getFirstName();

    public abstract String getLastName();

    public abstract String[] getAlternativeNames();

    public abstract Picture getMainPicture();

    public abstract String getBiography();

    public abstract Animeography[] getAnimeography();

}