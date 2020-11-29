package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.RankingType;

/**
 * Represents the Anime ranking type.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeRankingType {

    Airing  ("airing"),
    Upcoming("upcoming"),

    TV      ("tv"),
    OVA     ("ova"),
    Movie   ("movie"),
    Special ("special"),

    All         (RankingType.All.field()),
    ByPopularity(RankingType.ByPopularity.field()),
    Favorite    (RankingType.Favorite.field());

    private final String field;

    AnimeRankingType(final String type){
        this.field = type;
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
    public static AnimeRankingType asEnum(final String string){
        for(final AnimeRankingType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
