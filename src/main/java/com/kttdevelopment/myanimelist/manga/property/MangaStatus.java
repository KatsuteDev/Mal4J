package com.kttdevelopment.myanimelist.manga.property;

public enum MangaStatus {

    Reading     ("reading"),
    Completed   ("completed"),
    OnHold      ("on_hold"),
    Dropped     ("dropped"),
    PlanToRead  ("plan_to_read");

    private final String field;

    MangaStatus(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static MangaStatus asEnum(final String string){
        for(final MangaStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaStatus{" +
               "field='" + field + '\'' +
               '}';
    }

}
