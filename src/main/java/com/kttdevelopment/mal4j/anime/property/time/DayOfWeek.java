/*
 * Copyright (C) 2021-2022 Katsute <https://github.com/Katsute>
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

package com.kttdevelopment.mal4j.anime.property.time;

import com.kttdevelopment.mal4j.property.FieldEnum;

/**
 * Represents a day of the week.
 *
 * @see com.kttdevelopment.mal4j.anime.property.Broadcast
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public enum DayOfWeek implements FieldEnum {

    Sunday      ("sunday"),
    Monday      ("monday"),
    Tuesday     ("tuesday"),
    Wednesday   ("wednesday"),
    Thursday    ("thursday"),
    Friday      ("friday"),
    Saturday    ("saturday"),
    Other       ("other");

    private final String field;

    DayOfWeek(final String field){
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
    public static DayOfWeek asEnum(final String string){
        for(final DayOfWeek value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }
}
