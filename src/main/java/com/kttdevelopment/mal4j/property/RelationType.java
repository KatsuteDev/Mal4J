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

package com.kttdevelopment.mal4j.property;

import com.kttdevelopment.mal4j.Logging;

/**
 * Represents a relation type.
 *
 * @see RelatedMedia#getRelationType()
 * @since 1.0.0
 * @version 2.8.2
 * @author Katsute
 */
public enum RelationType {

    Unknown             ("unknown"),

    Other               ("other"),
    Sequel              ("sequel"),
    Prequel             ("prequel"),
    AlternativeSetting  ("alternative_setting"),
    AlternativeVersion  ("alternative_version"),
    SideStory           ("side_story"),
    ParentStory         ("parent_story"),
    Summary             ("summary"),
    FullStory           ("full_story"),

    SpinOff             ("spin_off"),
    Character           ("character");

    private final String field;

    RelationType(String field) {
        this.field = field;
    }

    /**
     * Returns the json field name.
     *
     * @return json field name
     *
     * @since 1.0.0
     */
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
    public static RelationType asEnum(final String string){
        for(final RelationType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        if(string != null)
            Logging.getLogger().warning(String.format("Unrecognized Relation type '%s', please report this to the maintainers of Mal4J", string));
        return Unknown;
    }

    @Override
    public final String toString() {
        return name();
    }

}
