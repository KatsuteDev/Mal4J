package com.kttdevelopment.myanimelist.manga.property;

public enum MangaSort {

    Score       ("list_score"),
    UpdatedAt   ("list_updated_at"),

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
