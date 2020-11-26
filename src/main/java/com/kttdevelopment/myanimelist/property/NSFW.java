package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;

/**
 * Represents the NSFW type.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum NSFW {

    White   ("white"),
    Gray    ("gray"),
    Black   ("black");

    private final String field;

    NSFW(String field) {
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
    public static NSFW asEnum(final String string){
        for(final NSFW value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString() {
        return "NSFW{" +
                "field='" + field + '\'' +
                '}';
    }
}
