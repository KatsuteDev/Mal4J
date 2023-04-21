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

import dev.katsute.mal4j.Logging;
import dev.katsute.mal4j.property.FieldEnum;

/**
 * Represents the type of media that the Anime is.
 *
 * @see dev.katsute.mal4j.anime.Anime#getType()
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public enum AnimeType implements FieldEnum {

    Unknown     ("unknown"),

    TV          ("tv"),
    OVA         ("ova"),
    Movie       ("movie"),
    Special     ("special"),
    ONA         ("ona"),
    Music       ("music"),

    MixedMedia  ("mixed_media");

    private final String field;

    AnimeType(final String field){
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
    public static AnimeType asEnum(final String string){
        if(string != null){
            for(final AnimeType value : values())
                if(value.field.equalsIgnoreCase(string))
                    return value;
            Logging.getLogger().warning(String.format("Unrecognized Anime type '%s', please report this to the maintainers of Mal4J", string));
        }

        return Unknown;
    }

    @Override
    public final String toString(){
        return name();
    }

}