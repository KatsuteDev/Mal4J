package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.anime.property.AnimeAirStatus;

/**
 * Represents a Manga status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum MangaStatus {

    Reading     ("reading"),
    Completed   ("completed"),
    OnHold      ("on_hold"),
    Dropped     ("dropped"),
    PlanToRead  ("plan_to_read");

    private final String field;

    MangaStatus(final String field){
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
    public static MangaStatus asEnum(final String string){
        for(final MangaStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaStatus{" +
               "field='" + field + '\'' +
               '}';
    }

}
