package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.RankingType;

public enum AnimeRankingType {

    All("all"),
    Airing("airing"),
    Upcoming("upcoming"),

    TV(AnimeType.TV.getType()),
    OVA(AnimeType.OVA.getType()),
    Movie(AnimeType.Movie.getType()),
    Special(AnimeType.Special.getType()),

    ByPopularity(RankingType.ByPopularity.getType()),
    Favorite(RankingType.Favorite.getType());

    private final String type;

    AnimeRankingType(final String type){
        this.type = type;
    }

    public final String getType(){
        return type;
    }

    @Override
    public String toString(){
        return "AnimeRankingType{" +
               "type='" + type + '\'' +
               '}';
    }
}
