package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.Sort;

/**
 * Represents the sort order.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeSort {

    Score       (Sort.Score.field()),
    UpdatedAt   (Sort.UpdatedAt.field()),

    Title       ("anime_title"),
    StartDate   ("anime_start_date"),
    ID          ("anime_id");

    private final String field;

    AnimeSort(final String field){
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
    public static AnimeSort asEnum(final String string){
        for(final AnimeSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "AnimeSort{" +
               "field='" + field + '\'' +
               '}';
    }

}
