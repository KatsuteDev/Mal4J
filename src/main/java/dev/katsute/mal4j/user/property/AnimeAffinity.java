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

package dev.katsute.mal4j.user.property;

import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.user.User;

/**
 * Represents an Anime affinity.
 *
 * @see User#getAnimeAffinity()
 * @see User#getAnimeAffinity(String)
 * @see User#getAnimeAffinity(User)
 * @since 2.3.0
 * @version 2.12.0
 * @author Katsute
 */
public abstract class AnimeAffinity {

    /**
     * Returns shared Anime. Only includes Anime that have a score.
     *
     * @return shared Anime
     *
     * @see Anime
     * @since 2.3.0
     */
    public abstract Anime[] getShared();

    /**
     * Returns amount of Anime shared.
     *
     * @return shared Anime count
     *
     * @since 2.3.0
     */
    public abstract int getSharedCount();

    /**
     * Returns affinity using MyAnimeList affinity algorithm.
     *
     * @return affinity as a float (100% = 1f; 50% = .5f)
     *
     * @see MyAnimeListAffinityAlgorithm
     * @since 2.3.0
     */
    public abstract float getAffinity();

    /**
     * Returns affinity using a custom {@link AffinityAlgorithm}.
     *
     * @param algorithm {@link AffinityAlgorithm}
     * @return affinity as a float
     *
     * @see AffinityAlgorithm
     * @since 2.3.0
     */
    public abstract float getAffinity(final AffinityAlgorithm algorithm);

}