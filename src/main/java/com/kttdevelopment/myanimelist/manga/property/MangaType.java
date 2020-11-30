package com.kttdevelopment.myanimelist.manga.property;

/**
 * Represents the type of medium the Manga is.
 *
 * @see com.kttdevelopment.myanimelist.manga.MangaPreview#getType()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
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

    /**
     * Returns the json field name.
     *
     * @return json field name
     *
     * @since 1.0.0
     */
    public final String field(){
        return field;
    }

    /**
     * Returns the field name as an enum.
     *
     * @param string json field name
     *
     * @return enum
     *
     * @since 1.0.0
     */
    public static MangaType asEnum(final String string){
        for(final MangaType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
