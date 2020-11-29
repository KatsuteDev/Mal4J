package com.kttdevelopment.myanimelist.anime.property;

/**
 * Represents how to sort the Anime season query.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeSeasonSort {

    Score   ("anime_score"),
    Users   ("anime_num_list_users");

    private final String field;

    AnimeSeasonSort(final String field){
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
    public static AnimeSeasonSort asEnum(final String string){
        for(final AnimeSeasonSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
