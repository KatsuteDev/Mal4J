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

package dev.katsute.mal4j.anime.property.time;

import dev.katsute.mal4j.property.FieldEnum;

import java.util.Arrays;

/**
 * Represents an airing season.
 *
 * @see dev.katsute.mal4j.anime.property.StartSeason
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public enum Season implements FieldEnum {

    Winter  ("winter"   , new String[]{"January", "February", "March"}),
    Spring  ("spring"   , new String[]{"April", "May", "June"}),
    Summer  ("summer"   , new String[]{"July", "August", "September"}),
    Fall    ("fall"     , new String[]{"October", "November", "December"});

    private final String field;
    private final String[] months;

    Season(final String field, final String[] months){
        this.field = field;
        this.months = months;
    }

    /**
     * Returns the months that the season represents.
     *
     * @return months
     *
     * @since 1.0.0
     */
    public final String[] getMonths(){
        return Arrays.copyOf(months, months.length);
    }

    /**
     * Returns the season that a month is a part of.
     *
     * @param month month
     * @return season the month is from
     *
     * @since 1.0.0
     */
    public static Season fromMonth(final String month){
        for(final Season value : values())
            for(final String m :value.getMonths())
                if(m.equalsIgnoreCase(month))
                    return value;
        return null;
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
    public static Season asEnum(final String string){
        if(string != null)
            for(final Season value : values())
                if(value.field.equalsIgnoreCase(string))
                    return value;
        return null;
    }

    @Override
    public String toString(){
        return "Season{" +
               "field='" + field + '\'' +
               ", months=" + Arrays.toString(months) +
               '}';
    }
}