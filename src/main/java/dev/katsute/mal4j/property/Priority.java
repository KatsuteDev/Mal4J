/*
 * Copyright (C) 2025 Katsute <https://github.com/Katsute>
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

package dev.katsute.mal4j.property;

/**
 * Represents a listing priority.
 *
 * @see dev.katsute.mal4j.query.AnimeListUpdate#priority(Priority)
 * @see dev.katsute.mal4j.query.MangaListUpdate#priority(Priority)
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public enum Priority {

    Low     (0),
    Medium  (1),
    High    (2);

    private final int value;

    Priority(final int value){
        this.value = value;
    }

    /**
     * Returns the json field value.
     *
     * @return json field value
     *
     * @since 1.0.0
     */
    public final int value(){
        return value;
    }

    /**
     * Returns the field value as an enum.
     *
     * @param num json field value
     *
     * @return enum
     *
     * @since 1.0.0
     */
    public static Priority asEnum(final Integer num){
        if(num != null)
            for(final Priority value : values())
                if(value.value == num)
                    return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}