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

package dev.katsute.mal4j.property;

import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.user.User;

/**
 * Represents experimental or undocumented features. If a feature is @deprecated it is no longer experimental, and you do not have to enable it.
 *
 * @see dev.katsute.mal4j.MyAnimeList#enableExperimentalFeature(ExperimentalFeature)
 * @since 2.3.0
 * @version 3.2.0
 * @author Katsute
 */
public enum ExperimentalFeature {

    /**
     * Enables all experimental features.
     */
    ALL,

    /**
     * @see Anime#getOpeningThemes()
     * @see Anime#getEndingThemes()
     */
    OP_ED_THEMES,

    /**
     * @see Anime#getVideos()
     */
    VIDEOS,

    /**
     * @see User#getAnimeAffinity()
     * @see User#getAnimeAffinity(User)
     * @see User#getAnimeAffinity(String)
     * @see User#getMangaAffinity()
     * @see User#getMangaAffinity(User)
     * @see User#getMangaAffinity(String)
     */
    AFFINITY,

    /**
     * @see dev.katsute.mal4j.MyAnimeList#getAnimeCharacters(long)
     * @see dev.katsute.mal4j.MyAnimeList#getCharacter(long)
     * @see dev.katsute.mal4j.MyAnimeList#getCharacter(long, String...)
     */
    CHARACTERS,

    /**
     * @see dev.katsute.mal4j.MyAnimeList#getPerson(long)
     * @see dev.katsute.mal4j.MyAnimeList#getPerson(long, String...)
     */
    PEOPLE;

}