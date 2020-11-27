package com.kttdevelopment.myanimelist.anime.property;

public enum AnimeAirStatus {

    Airing      ("currently_airing"),
    NotYetAired ("not_yet_aired"),
    Finished    ("finished_airing");

    private final String field;

    AnimeAirStatus(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static AnimeAirStatus asEnum(final String string){
        for(final AnimeAirStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "AnimeAirStatus{" +
               "field='" + field + '\'' +
               '}';
    }

}
