package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.AnimePreview;

/**
 * Represents the Anime's TV viewing rating (ex: pg13).
 *
 * @see AnimePreview#getRating()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeRating {

    G   ("g"),
    PG  ("pg"),
    PG13("pg_13"),
    R   ("r"),
    RP  ("r+"),
    RX  ("rx");

    private final String field;

    AnimeRating(String field) {
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
    public static AnimeRating asEnum(final String string){
        for(final AnimeRating value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
