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

import java.util.Date;

abstract class MyAnimeListSchema_People extends MyAnimeListSchema {

    static Person asPerson(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new Person(){

            private final Long id = schema.getLong("id");

            private final String firstName    = schema.getString("first_name");
            private final String lastName     = schema.getString("last_name");
            private final Long birthday       = parseDate(schema.getString("birthday"));
            private final Picture mainPicture = MyAnimeListSchema_Common.asPicture(mal, schema.getJsonObject("main_picture"));

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
            public final Picture getMainPicture(){
                return mainPicture;
            }

            // additional methods

            @Override
            public final String toString(){
                return "Person{" +
                       "id=" + id +
                       ", firstName='" + firstName + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", birthday=" + birthday +
                       ", mainPicture=" + mainPicture +
                       '}';
            }

        };
    }

}