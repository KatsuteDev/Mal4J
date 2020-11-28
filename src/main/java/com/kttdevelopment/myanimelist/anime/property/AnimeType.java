package com.kttdevelopment.myanimelist.anime.property;

public enum AnimeType {

    Unknown ("unknown"),
    TV      ("tv"),
    OVA     ("ova"),
    Movie   ("movie"),
    Special ("special"),
    ONA     ("ona"),
    Music   ("music");

    private final String field;

    AnimeType(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static AnimeType asEnum(final String string){
        for(final AnimeType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
