package com.kttdevelopment.myanimelist.anime.property;

/**
 * Represents Anime types.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
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

    /**
     * Returns the field name.
     *
     * @return field name
     *
     * @since 1.0.0
     */
    public final String field(){
        return field;
    }

    /**
     * Returns the field as an enum.
     *
     * @param string string
     * @return enum
     *
     * @since 1.0.0
     */
    public static AnimeType asEnum(final String string){
        for(final AnimeType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "AnimeType{" +
               "field='" + field + '\'' +
               '}';
    }

}
