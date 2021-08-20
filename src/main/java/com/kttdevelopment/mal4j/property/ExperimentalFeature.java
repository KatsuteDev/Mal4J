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

import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.user.User;

/**
 * Represents experimental or undocumented features. If a feature is @deprecated it is no longer experimental, and you do not have to enable it.
 *
 * @see com.kttdevelopment.mal4j.MyAnimeList#enableExperimentalFeature(ExperimentalFeature)
 * @since 2.3.0
 * @version 2.3.0
 * @author Katsute
 */
public enum ExperimentalFeature {

    /**
     * @see Anime#getOpeningThemes()
     * @see Anime#getEndingThemes()
     */
    OP_ED_THEMES,

    /**
     * @see User#getAnimeAffinity()
     * @see User#getAnimeAffinity(User)
     * @see User#getAnimeAffinity(String)
     * @see User#getMangaAffinity()
     * @see User#getMangaAffinity(User)
     * @see User#getMangaAffinity(String)
     */
    AFFINITY

}
