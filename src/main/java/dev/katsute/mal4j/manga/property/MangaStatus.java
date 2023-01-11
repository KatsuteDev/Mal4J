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

import dev.katsute.mal4j.property.FieldEnum;

/**
 * Represents a Manga's status on a users Manga list.
 *
 * @see dev.katsute.mal4j.manga.Manga#getStatus()
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public enum MangaStatus implements FieldEnum {

    Reading     ("reading"),
    Completed   ("completed"),
    OnHold      ("on_hold"),
    Dropped     ("dropped"),
    PlanToRead  ("plan_to_read");

    private final String field;

    MangaStatus(final String field){
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
    public static MangaStatus asEnum(final String string){
        if(string != null)
            for(final MangaStatus value : values())
                if(value.field.equalsIgnoreCase(string))
                    return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
