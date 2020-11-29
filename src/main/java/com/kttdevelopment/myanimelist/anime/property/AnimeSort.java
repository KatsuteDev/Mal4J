package com.kttdevelopment.myanimelist.anime.property;

/**
 * Represents how to sort the Anime search query.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeSort {

    Score       ("List_score"),
    UpdatedAt   ("list_updated_at"),
    Title       ("anime_title"),
    StartDate   ("anime_start_date"),
    ID          ("anime_id");

    private final String field;

    AnimeSort(final String field){
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
    public static AnimeSort asEnum(final String string){
        for(final AnimeSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
