package com.kttdevelopment.myanimelist.manga.property;

/**
 * Represents a Manga's status on a users Manga list.
 *
 * @see com.kttdevelopment.myanimelist.manga.MangaPreview#getStatus()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum MangaStatus {

    Reading     ("reading"),
    Completed   ("completed"),
    OnHold      ("on_hold"),
    Dropped     ("dropped"),
    PlanToRead  ("plan_to_read");

    private final String field;

    MangaStatus(final String field){
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
    public static MangaStatus asEnum(final String string){
        for(final MangaStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
