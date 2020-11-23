package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.property.AnimeAirStatus;

/**
 * Represents the sort order.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum Sort {

    Score       ("list_score"),
    UpdatedAt   ("updated_at");

    private final String field;

    Sort(final String field){
        this.field = field;
    }

    /**
     * Returns the field name.
     *
     * @return field name
     *
     * @since 1.0.0
     */
    public final String field(){
        return field;
    }

    /**
     * Returns the field as an enum.
     *
     * @param string string
     * @return enum
     *
     * @since 1.0.0
     */
    public static Sort asEnum(final String string){
        for(final Sort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "Sort{" +
               "field='" + field + '\'' +
               '}';
    }

}
