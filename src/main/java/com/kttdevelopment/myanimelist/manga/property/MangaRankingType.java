package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.RankingType;

/**
 * Represents the Manga ranking type.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public enum MangaRankingType {

    Manga   ("manga"),
    Novels  ("novels"),
    OneShots("oneshots"),
    Doujin  ("doujin"),
    Manhwa  ("manhwa"),
    Manhua  ("manhua"),

    All         (RankingType.All.field()),
    ByPopularity(RankingType.ByPopularity.field()),
    Favorite    (RankingType.Favorite.field());

    private final String field;

    MangaRankingType(final String field){
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
    public static MangaRankingType asEnum(final String string){
        for(final MangaRankingType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }
}
