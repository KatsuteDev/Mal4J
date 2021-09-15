/*
 * Copyright (C) 2021 Katsute <https://github.com/Katsute>
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

package com.kttdevelopment.mal4j.property;

import java.util.Objects;

/**
 * Represents a Genre.
 *
 * @since 1.0.0
 * @version 2.4.0
 * @author Katsute
 */
public final class Genre {

    private final int id;
    private final String name;
    private final boolean isAnimeGenre;

    Genre(final int id, final String name, final boolean isAnimeGenre){
        this.id = id;
        this.name = name;
        this.isAnimeGenre = isAnimeGenre;
    }

    /**
     * Returns the genre ID for the Anime or Manga. Note that Anime and Manga may not share the same IDs. Use {@link #isAnimeGenre()} or {@link #isMangaGenre()} to get type.
     *
     * @return id
     *
     * @see #isAnimeGenre()
     * @see #isMangaGenre()
     * @since 2.4.0
     */
    public final int getID(){
        return id;
    }

    /**
     * Returns the name of the Genre.
     *
     * @return genre name
     *
     * @since 2.4.0
     */
    public final String getName(){
        return name;
    }

    /**
     * Returns if the genre ID is for an Anime.
     *
     * @return if is Anime genre
     *
     * @since 2.4.0
     */
    public final boolean isAnimeGenre(){
        return isAnimeGenre;
    }

    /**
     * Returns if the genre ID is for a Manga.
     *
     * @return if is Manga genre
     *
     * @since 2.4.0
     */
    public final boolean isMangaGenre(){
        return !isAnimeGenre;
    }

    @Override
    public final String toString(){
        return getName();
    }

    @Override
    public final boolean equals(final Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        return Objects.equals(name, ((Genre) o).name);
    }

}