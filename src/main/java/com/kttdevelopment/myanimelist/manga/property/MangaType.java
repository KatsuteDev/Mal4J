package com.kttdevelopment.myanimelist.manga.property;

@SuppressWarnings("SpellCheckingInspection")
public enum MangaType {

    Unknown ("unknown"),
    Manga   ("manga"),
    Novel   ("novel"),
    OneShot ("one_shot"),
    Doujin  ("doujinshi"),
    Manhwa  ("manhwa"),
    Manhua  ("manhua"),
    OEL     ("oel");

    private final String field;

    MangaType(final String field){
        this.field = field;
    }

    public final String field(){
        return field;
    }

    public static MangaType asEnum(final String string){
        for(final MangaType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaType{" +
               "field='" + field + '\'' +
               '}';
    }
}
