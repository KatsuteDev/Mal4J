package com.kttdevelopment.myanimelist.manga.property;

public enum MangaPublishStatus {

    Publishing  ("currently_publishing"),
    NotYetPublished   ("not_yet_published"),
    Finished    ("finished");

    private final String field;

    MangaPublishStatus(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static MangaPublishStatus asEnum(final String string){
        for(final MangaPublishStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaPublishStatus{" +
               "field='" + field + '\'' +
               '}';
    }

}
