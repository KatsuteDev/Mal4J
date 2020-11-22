package com.kttdevelopment.myanimelist.anime;

@SuppressWarnings("SpellCheckingInspection")
public enum AnimeRankingEnum {

    All("all"),
    Airing("airing"),
    Upcoming("upcoming"),
    TV("tv"),
    OVA("ova"),
    Movie("movie"),
    Special("special"),
    ByPopularity("bypopularity"),
    Favorite("favorite");

    private final String ranking;

    AnimeRankingEnum(final String ranking){
        this.ranking = ranking;
    }

    public final String getRanking(){
        return ranking;
    }

    @Override
    public String toString(){
        return "AnimeRanking{" +
               "ranking='" + ranking + '\'' +
               '}';
    }

}
