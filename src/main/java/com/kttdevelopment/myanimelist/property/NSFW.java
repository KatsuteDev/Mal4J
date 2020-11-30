package com.kttdevelopment.myanimelist.property;

/**
 * Represents the NSFW status.
 *
 * @see MediaItem#getNSFW()
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
