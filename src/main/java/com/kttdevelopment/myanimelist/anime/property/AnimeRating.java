package com.kttdevelopment.myanimelist.anime.property;

public enum AnimeRating {

    G   ("g"),
    PG  ("pg"),
    PG13("pg_13"),
    R   ("r"),
    RP  ("r+"),
    RX  ("rx");

    private final String field;

    AnimeRating(String field) {
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static AnimeRating asEnum(final String string){
        for(final AnimeRating value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
