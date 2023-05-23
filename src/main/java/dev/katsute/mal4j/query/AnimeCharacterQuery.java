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

package dev.katsute.mal4j.query;

import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.character.Character;

/**
 * Represents a Anime character query.
 *
 * @see dev.katsute.mal4j.MyAnimeList#getAnimeCharacters(long)
 * @see Anime#getCharacters()
 * @see LimitOffsetQuery
 * @since 3.1.0
 * @version 3.1.0
 * @author Katsute
 */
public abstract class AnimeCharacterQuery extends FieldQuery<AnimeCharacterQuery,Character> {

    /**
     * Creates an Anime character query.
     * <br>
     * Do not use this constructor, use {@link dev.katsute.mal4j.MyAnimeList#getAnimeCharacters(long)} or {@link Anime#getCharacters()} instead.
     *
     * @see dev.katsute.mal4j.MyAnimeList#getAnimeCharacters(long)
     * @see Anime#getCharacters()
     * @since 3.1.0
     */
    public AnimeCharacterQuery(){ }

}