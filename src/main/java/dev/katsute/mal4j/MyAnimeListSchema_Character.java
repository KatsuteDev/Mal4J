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

import dev.katsute.mal4j.Json.JsonObject;
import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.character.Animeography;
import dev.katsute.mal4j.character.Character;
import dev.katsute.mal4j.property.Picture;

import java.util.Arrays;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_Character extends MyAnimeListSchema {

    static Character asCharacter(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Character() {

            private final Long id                 = schema.getLong("id");
            private final String firstName        = schema.getString("first_name");
            private final String lastName         = schema.getString("last_name");
            private final String alternativeNames = schema.getString("alternative_name");
            private final Picture mainPicture     = MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture"));
            private final String biography        = schema.getString("biography");
            private final Animeography[] animeography = adaptList(schema.getJsonArray("animeography"), s -> asAnimeography(mal, s), Animeography.class);

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getFirstName(){
                return firstName;
            }

            @Override
            public final String getLastName(){
                return lastName;
            }

            @Override
            public final String[] getAlternativeNames(){
                return alternativeNames != null ? alternativeNames.split(", ") : null;
            }

            @Override
            public final Picture getMainPicture(){
                return mainPicture;
            }

            @Override
            public final String getBiography(){
                return biography;
            }

            @Override
            public final Animeography[] getAnimeography(){
                return animeography != null ? Arrays.copyOf(animeography, animeography.length) : null;
            }

            // additional methods

            @Override
            public final String toString(){
                return "Character{" +
                       "id=" + id +
                       ", firstName='" + firstName + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", alternativeNames='" + alternativeNames + '\'' +
                       ", mainPicture=" + mainPicture + '\'' +
                       ", biography='" + biography + '\'' +
                       ", animeography'" + Arrays.toString(animeography) + '\'' +
                       '}';
            }

        };
    }

    static Animeography asAnimeography(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Animeography() {

            private final Anime anime = MyAnimeListSchema_Anime.asAnimePreview(mal, schema.getJsonObject("node"));
            private final String role = schema.getString("role");

            @Override
            public final Anime getAnime(){
                return anime;
            }

            @Override
            public final String getRole(){
                return role;
            }

            // additional methods

            @Override
            public final String toString(){
                return "Animeography{" +
                       "anime=" + anime +
                       ", role='" + role + '\'' +
                       '}';
            }

        };
    }

}