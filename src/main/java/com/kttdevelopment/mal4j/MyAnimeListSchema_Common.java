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
package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.property.AlternativeTitles;
import com.kttdevelopment.mal4j.property.Picture;

import java.util.Arrays;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_Common extends MyAnimeListSchema {

    static AlternativeTitles asAlternativeTitles(final MyAnimeList mal, final Json.JsonObject schema){
        return new AlternativeTitles() {

            private final String[] synonyms = requireNonNull(() -> schema.getStringArray("synonyms"));
            private final String english    = requireNonNull(() -> schema.getString("en"));
            private final String japanese   = requireNonNull(() -> schema.getString("ja"));

            // API methods

            @Override
            public final String[] getSynonyms() {
                return Arrays.copyOf(synonyms, synonyms.length);
            }

            @Override
            public final String getEnglish() {
                return english;
            }

            @Override
            public final String getJapanese() {
                return japanese;
            }

            //

            @Override
            public final String toString(){
                return "AlternativeTitles{" +
                       "synonyms=" + Arrays.toString(synonyms) +
                       ", english='" + english + '\'' +
                       ", japanese='" + japanese + '\'' +
                       '}';
            }

        };
    }

    static Picture asPicture(final MyAnimeList mal, final Json.JsonObject schema){
        return new Picture() {

            private final String medium = requireNonNull(() -> schema.getString("medium"));
            private final String large  = requireNonNull(() -> schema.getString("large"));

            // API methods

            @Override
            public final String getMediumURL() {
                return medium;
            }

            @Override
            public final String getLargeURL() {
                return large;
            }

            // additional methods

            @Override
            public final String toString(){
                return "Picture{" +
                       "medium='" + medium + '\'' +
                       ", large='" + large + '\'' +
                       '}';
            }

        };
    }

}
