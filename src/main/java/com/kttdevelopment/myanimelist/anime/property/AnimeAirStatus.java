package com.kttdevelopment.myanimelist.anime.property;

/**
 * Represents an Anime air status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeAirStatus {

    Airing      ("airing"),
    NotYetAired ("not_yet_aired"),
    Finished    ("finished_airing");

    private final String field;

    AnimeAirStatus(final String field){
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
    public static AnimeAirStatus asEnum(final String string){
        for(final AnimeAirStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "AnimeAirStatus{" +
               "field='" + field + '\'' +
               '}';
    }

}
