package com.kttdevelopment.myanimelist.property;

public enum RankingType {

    @SuppressWarnings("SpellCheckingInspection")
    ByPopularity("bypopularity"),
    Favorite("favorite");

    private final String type;

    RankingType(final String type){
        this.type = type;
    }

    public final String getType(){
        return type;
    }

    @Override
    public String toString(){
        return "RankingType{" +
               "type='" + type + '\'' +
               '}';
    }
}
