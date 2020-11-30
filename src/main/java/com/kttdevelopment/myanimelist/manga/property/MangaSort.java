package com.kttdevelopment.myanimelist.manga.property;

/**
 * Represents how to sort the Manga search query.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum MangaSort {

    Score       ("list_score"),
    UpdatedAt   ("list_updated_at"),

    Title       ("manga_title"),
    StartDate   ("manga_start_date"),
    ID          ("manga_id");

    private final String field;

    MangaSort(final String field){
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
    public static MangaSort asEnum(final String string){
        for(final MangaSort value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
