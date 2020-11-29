package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.MediaItem;

/**
 * Represents an Anime's airing status.
 *
 * @see MediaItem#getStatus()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeAirStatus {

    Airing      ("currently_airing"),
    NotYetAired ("not_yet_aired"),
    Finished    ("finished_airing");

    private final String field;

    AnimeAirStatus(final String field){
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
    public static AnimeAirStatus asEnum(final String string){
        for(final AnimeAirStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
