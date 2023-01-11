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

/**
 * The affinity algorithm returns an affinity score based on the shared scores between two people.
 *
 * @see MyAnimeListAffinityAlgorithm
 * @since 2.3.0
 * @version 2.3.0
 * @author Katsute
 */
@FunctionalInterface
public interface AffinityAlgorithm {

    /**
     * Returns affinity between two linked score sets.
     *
     * @param a_scores shared scores for first user (in same order as second user)
     * @param b_scores shared scores for second user (in same order as first user)
     * @return affinity
     *
     * @since 2.3.0
     */
    float getAffinity(int[] a_scores, int[] b_scores);

}
