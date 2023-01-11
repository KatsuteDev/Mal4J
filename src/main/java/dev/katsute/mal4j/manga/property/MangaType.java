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

package dev.katsute.mal4j.manga.property;

import dev.katsute.mal4j.Logging;
import dev.katsute.mal4j.property.FieldEnum;

/**
 * Represents the type of medium a Manga is.
 *
 * @see dev.katsute.mal4j.manga.Manga#getType()
 * @since 1.0.0
 * @version 2.10.0
 * @author Katsute
 */
@SuppressWarnings("SpellCheckingInspection")
public enum MangaType implements FieldEnum {

    Unknown     ("unknown"),

    Manga       ("manga"),
    LightNovel  ("light_novel"),
    OneShot     ("one_shot"),
    Doujin      ("doujinshi"),
    Manhwa      ("manhwa"),
    Manhua      ("manhua"),
    OEL         ("oel");

    private final String field;

    MangaType(final String field){
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
    public static MangaType asEnum(final String string){
        if(string != null){
            for(final MangaType value : values())
                if(value.field.equalsIgnoreCase(string))
                    return value;
            Logging.getLogger().warning(String.format("Unrecognized Manga type '%s', please report this to the maintainers of Mal4J", string));
        }

        return Unknown;
    }

    @Override
    public final String toString(){
        return name();
    }

}
