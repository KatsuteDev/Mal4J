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

package dev.katsute.mal4j.anime.property;

import dev.katsute.mal4j.property.FieldEnum;

/**
 * Represents a TV viewing rating (ex: pg13).
 *
 * @see dev.katsute.mal4j.anime.Anime#getRating()
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public enum AnimeRating implements FieldEnum {

    G   ("g"),
    PG  ("pg"),
    PG13("pg_13"),
    R   ("r"),
    RP  ("r+"),
    RX  ("rx");

    private final String field;

    AnimeRating(String field){
        this.field = field;
    }

    @Override
    public final String field(){
        return field;
    }

    /**
     * Returns the field name as an enum.
     *
     * @param string json field name
     *
     * @return enum
     *
     * @since 1.0.0
     */
    public static AnimeRating asEnum(final String string){
        if(string != null)
            for(final AnimeRating value : values())
                if(value.field.equalsIgnoreCase(string))
                    return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}