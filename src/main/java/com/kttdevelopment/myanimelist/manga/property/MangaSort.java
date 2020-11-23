package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.Sort;

/**
 * Represents the sort order.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum MangaSort {

    Score       (Sort.Score.field()),
    UpdatedAt   (Sort.UpdatedAt.field()),

    Title       ("manga_title"),
    StartDate   ("manga_start_date"),
    ID          ("manga_id");

    private final String field;

    MangaSort(final String field){
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
    public static MangaSort asEnum(final String string){
        for(final MangaSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaSort{" +
               "field='" + field + '\'' +
               '}';
    }

}
