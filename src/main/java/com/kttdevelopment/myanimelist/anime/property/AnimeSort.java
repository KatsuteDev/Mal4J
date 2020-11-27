package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.Sort;

public enum AnimeSort {

    Score       (Sort.Score.field()),
    UpdatedAt   (Sort.UpdatedAt.field()),

    Title       ("anime_title"),
    StartDate   ("anime_start_date"),
    ID          ("anime_id");

    private final String field;

    AnimeSort(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static AnimeSort asEnum(final String string){
        for(final AnimeSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "AnimeSort{" +
               "field='" + field + '\'' +
               '}';
    }

}
