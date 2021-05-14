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

package com.kttdevelopment.mal4j.anime.property;

public final class MyAnimeListAffinityAlgorithm implements AffinityAlgorithm {

    @Override
    public final float getAffinity(final int[] a_scores, final int[] b_scores){
        final int len = a_scores.length;
        if(len != b_scores.length)
            throw new IllegalStateException("Both arrays must be of the same length");
        else if(len == 0)
            return 0f;

        int x_sum = 0, y_sum = 0;
        for(int i = 0; i < len; i++){
            x_sum += a_scores[i];
            y_sum += b_scores[i];
        }

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
