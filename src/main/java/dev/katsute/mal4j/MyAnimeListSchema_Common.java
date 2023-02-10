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
package dev.katsute.mal4j;

import dev.katsute.mal4j.property.*;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_Common extends MyAnimeListSchema {

    static AlternativeTitles asAlternativeTitles(final MyAnimeList mal, final Json.JsonObject schema){
        return schema == null ? null : new AlternativeTitles() {

            private final String[] synonyms = schema.getStringArray("synonyms");
            private final String english    = schema.getString("en");
            private final String japanese   = schema.getString("ja");

            // API methods

            @Override
            public final String[] getSynonyms() {
                return synonyms != null ? Arrays.copyOf(synonyms, synonyms.length) : null;
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
        return schema == null ? null : new Picture() {

            private final String medium = schema.getString("medium");
            private final String large  = schema.getString("large");

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

    static Genre asGenre(final MyAnimeList mal, final Json.JsonObject schema, final boolean animeGenre){
        return schema == null ? null : new Genre() {

            private final Integer id = schema.getInt("id");
            private final String name = schema.getString("name");
            private final boolean isAnimeGenre = animeGenre;

            // API methods

            @Override
            public final int getID(){
                if(id == null)
                    throw new NullPointerException("There is no such ID for this genre");
                return id;
            }

            @Override
            public final String getName(){
                return name;
            }

            // additional methods

            @Override
            public final boolean isAnimeGenre(){
                return isAnimeGenre;
            }

            @Override
            public final boolean isMangaGenre(){
                return !isAnimeGenre;
            }

            @Override
            public final String toString(){
                return "Genre{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", isAnimeGenre=" + isAnimeGenre +
                       '}';
            }

            @Override
            public final boolean equals(final Object o){
                if(this == o) return true;
                if(o == null || getClass() != o.getClass()) return false;
                return Objects.equals(name, ((Genre) o).getName());
            }

            @Override
            public final int hashCode(){
                return Objects.hash(name);
            }

        };
    }

}
