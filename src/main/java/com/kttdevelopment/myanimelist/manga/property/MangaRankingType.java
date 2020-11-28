package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.RankingType;

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

    public final String field(){
        return field;
    }

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
