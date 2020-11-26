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
    public static AnimeRating asEnum(final String string){
        for(final AnimeRating value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString() {
        return "AnimeRating{" +
                "field='" + field + '\'' +
                '}';
    }
}
