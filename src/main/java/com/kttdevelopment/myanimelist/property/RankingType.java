package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.property.AnimeAirStatus;

/**
 * Represents general ranking types.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum RankingType {

    All         ("all"),
    @SuppressWarnings("SpellCheckingInspection")
    ByPopularity("bypopularity"),
    Favorite    ("favorite");

    private final String field;

    RankingType(final String field){
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
    public static RankingType asEnum(final String string){
        for(final RankingType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "RankingType{" +
               "field='" + field + '\'' +
               '}';
    }
}
