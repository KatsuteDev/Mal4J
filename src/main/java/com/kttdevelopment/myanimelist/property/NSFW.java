package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;

public enum NSFW {

    White   ("white"),
    Gray    ("gray"),
    Black   ("black");

    private final String field;

    NSFW(String field) {
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static NSFW asEnum(final String string){
        for(final NSFW value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
