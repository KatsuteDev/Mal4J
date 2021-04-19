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
package com.kttdevelopment.mal4j.user.property;

public final class MyAnimeListAffinityAlgorithm implements AffinityAlgorithm{

    @Override
    public final float getAffinity(final int[] a_scores, final int[] b_scores){
        final int len = a_scores.length;

        int a_total = 0, b_total = 0;
        for(int i = 0; i < len; i++){
            a_total += a_scores[i];
            b_total += b_scores[i];
        }

        final double a_mean = a_total / ((double) len);
        final double b_mean = b_total / ((double) len);

        double ab_enum = 0, a_enum = 0, b_enum = 0;

        double a_buffer, b_buffer;
        for(int i = 0; i < len; i++){
            a_buffer = a_scores[i] - a_mean;
            b_buffer = b_scores[i] - b_mean;
            ab_enum += a_buffer * b_buffer;
            a_enum  += Math.pow(a_buffer, 2);
            b_enum  += Math.pow(b_buffer, 2);
        }

        return (float) (ab_enum / (a_enum * b_enum));
    }

}
