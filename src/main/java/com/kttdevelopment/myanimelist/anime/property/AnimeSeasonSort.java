package com.kttdevelopment.myanimelist.anime.property;

public enum AnimeSeasonSort {

    Score   ("anime_score"),
    Users   ("anime_num_list_users");

    private final String field;

    AnimeSeasonSort(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static AnimeSeasonSort asEnum(final String string){
        for(final AnimeSeasonSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "AnimeSeasonSort{" +
               "field='" + field + '\'' +
               '}';
    }

}
