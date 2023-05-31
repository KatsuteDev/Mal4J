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
import dev.katsute.mal4j.people.Person;
import dev.katsute.mal4j.property.Picture;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

abstract class MyAnimeListSchema_People extends MyAnimeListSchema {

    static Person asPerson(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Person(){

            private final Long id = schema.getLong("id");

            private final String firstName           = schema.getString("first_name");
            private final String lastName            = schema.getString("last_name");
            private final Long birthday              = parseDate(schema.getString("birthday"));
            private final String[] alternative_names = schema.getStringArray("alternative_names");
            private final Integer favorites          = schema.getInt("num_favorites");
            private final Picture mainPicture        = MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture"));
            private final String more                = schema.getString("more");
            private final Map<String,String> moreDetails = more == null ? null : MyAnimeListSchema_Common.asMap(more);

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
            public final Date getBirthday(){
                return birthday == null ? null : new Date(birthday);
            }

            @Override
            public final String[] getAlternativeNames(){
                return alternative_names == null ? null : Arrays.copyOf(alternative_names, alternative_names.length);
            }

            @Override
            public final Picture getMainPicture(){
                return mainPicture;
            }

            @Override
            public final Integer getFavorites(){
                return favorites;
            }

            @Override
            public final String getMore(){
                return more;
            }

            @Override
            public final Map<String,String> getMoreDetails(){
                return moreDetails == null ? null : new HashMap<>(moreDetails);
            }

            // additional methods

            @Override
            public final String toString(){
                return "Person{" +
                       "id=" + id +
                       ", firstName='" + firstName + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", birthday=" + birthday +
                       ", alternative_names=" + Arrays.toString(alternative_names) +
                       ", favorites=" + favorites +
                       ", mainPicture=" + mainPicture +
                       ", more='" + more + '\'' +
                       ", moreDetails=" + moreDetails +
                       '}';
            }

        };
    }

}