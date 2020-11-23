package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.anime.property.AnimeAirStatus;

/**
 * Represents Manga types.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public enum MangaType {

    Manga   ("manga"),
    Novels  ("novels"),
    OneShots("oneshots"),
    Doujin  ("doujin"),
    Manhwa  ("manhwa"),
    Manhua  ("manhua");

    private final String field;

    MangaType(final String field){
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
    public static MangaType asEnum(final String string){
        for(final MangaType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaType{" +
               "field='" + field + '\'' +
               '}';
    }
}
