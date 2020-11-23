package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.RankingType;

/**
 * Represents Anime ranking types.
 *
 * @see AnimeType
 * @see RankingType
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeRankingType {

    Airing  ("airing"),
    Upcoming("upcoming"),

    TV      (AnimeType.TV.field()),
    OVA     (AnimeType.OVA.field()),
    Movie   (AnimeType.Movie.field()),
    Special (AnimeType.Special.field()),

    All         (RankingType.All.field()),
    ByPopularity(RankingType.ByPopularity.field()),
    Favorite    (RankingType.Favorite.field());

    private final String field;

    AnimeRankingType(final String type){
        this.field = type;
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
    public static AnimeRankingType asEnum(final String string){
        for(final AnimeRankingType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "AnimeRankingType{" +
               "field='" + field + '\'' +
               '}';
    }
}
