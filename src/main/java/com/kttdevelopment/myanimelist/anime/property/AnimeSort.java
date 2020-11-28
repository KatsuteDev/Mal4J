package com.kttdevelopment.myanimelist.anime.property;

public enum AnimeSort {

    Score       ("List_score"),
    UpdatedAt   ("list_updated_at"),
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
    public final String toString(){
        return name();
    }

}
