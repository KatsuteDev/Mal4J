package com.kttdevelopment.myanimelist.anime.property;

public enum AnimeType {

    TV("tv"),
    OVA("ova"),
    Movie("movie"),
    Special("special");

    private final String type;

    AnimeType(final String type){
        this.type = type;
    }

    public final String getType(){
        return type;
    }

    @Override
    public String toString(){
        return "AnimeType{" +
               "type='" + type + '\'' +
               '}';
    }

}
