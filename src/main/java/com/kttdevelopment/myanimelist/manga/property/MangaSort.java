package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.Sort;

public enum MangaSort {

    Score       (Sort.Score.field()),
    UpdatedAt   (Sort.UpdatedAt.field()),

    Title       ("manga_title"),
    StartDate   ("manga_start_date"),
    ID          ("manga_id");

    private final String field;

    MangaSort(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static MangaSort asEnum(final String string){
        for(final MangaSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaSort{" +
               "field='" + field + '\'' +
               '}';
    }

}
