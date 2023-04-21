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
 * The MyAnimeList affinity algorithm.
 * <br>
 * <a href="https://en.wikipedia.org/wiki/Pearson_correlation_coefficient#For_a_sample">Pearson Correlation Coefficient (sample)</a>
 *
 * @see AffinityAlgorithm
 * @since 2.3.0
 * @version 2.4.0
 * @author Katsute
 */
public final class MyAnimeListAffinityAlgorithm implements AffinityAlgorithm {

    /**
     * Returns affinity between two linked score sets.
     *
     * @param a_scores shared scores for first user (in same order as second user)
     * @param b_scores shared scores for second user (in same order as first user)
     * @return affinity as a float (100% = 1f; 50% = .5f)
     * @throws IllegalArgumentException if array lengths are not the same
     *
     * @since 2.3.0
     */
    @Override
    public final float getAffinity(final int[] a_scores, final int[] b_scores){
        final int len = a_scores.length;
        if(len != b_scores.length)
            throw new IllegalArgumentException("Both arrays must be of the same length, argument 1 was of length " + a_scores.length + " while argument 2 was of length " + b_scores.length);
        else if(len == 0)
            return 0f;

        Integer diff = null;
        boolean sameDiff = true;
        int a, b;
        int x_sum = 0, y_sum = 0;
        for(int i = 0; i < len; i++){
            x_sum += a = a_scores[i];
            y_sum += b = b_scores[i];

            if(diff != null && sameDiff && diff != a - b)
                sameDiff = false;
            diff = a - b;
        }

        if(sameDiff) // edge case
            return 1f;

        final double x_mean = x_sum / (double) len;
        final double y_mean = y_sum / (double) len;

        double enumerate_xy_diff = 0;
        double enumerate_x_diff = 0;
        double enumerate_y_diff = 0;

        double x_diff, y_diff;
        for(int i = 0; i < len; i++){
            x_diff = a_scores[i] - x_mean;
            y_diff = b_scores[i] - y_mean;
            enumerate_xy_diff += x_diff * y_diff;
            enumerate_x_diff += x_diff * x_diff;
            enumerate_y_diff += y_diff * y_diff;
        }

        double xy_sqrt = Math.sqrt(enumerate_x_diff) * Math.sqrt(enumerate_y_diff);

        return (float) (xy_sqrt == 0 ? 0 : enumerate_xy_diff / xy_sqrt);
    }

}