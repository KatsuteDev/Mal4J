/*
 * Copyright (C) 2024 Katsute <https://github.com/Katsute>
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

/**
 * Represents a genre.
 *
 * @since 2.4.0
 * @version 2.4.0
 * @author Katsute
 */
public abstract class Genre {

    /**
     * Returns the genre ID for the Anime or Manga. Note that Anime and Manga may not share the same IDs. Use {@link #isAnimeGenre()} or {@link #isMangaGenre()} to get type.
     *
     * @throws NullPointerException if ID is null
     * @return id
     *
     * @see #isAnimeGenre()
     * @see #isMangaGenre()
     * @since 2.4.0
     */
    public abstract int getID();

    /**
     * Returns the name of the Genre.
     *
     * @return genre name
     *
     * @since 2.4.0
     */
    public abstract String getName();

    /**
     * Returns if the genre is for an Anime.
     *
     * @return if is Anime genre
     *
     * @since 2.4.0
     */
    public abstract boolean isAnimeGenre();

    /**
     * Returns if the genre is for a Manga.
     *
     * @return if is Manga genre
     *
     * @since 2.4.0
     */
    public abstract boolean isMangaGenre();

}