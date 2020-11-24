package com.kttdevelopment.myanimelist.manga.property;

/**
 * Represents a Manga publish status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum MangaPublishStatus {

    Publishing  ("publishing"),
    Published   ("not_yet_published"),
    Finished    ("finished_publishing");

    private final String field;

    MangaPublishStatus(final String field){
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
    public static MangaPublishStatus asEnum(final String string){
        for(final MangaPublishStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaPublishStatus{" +
               "field='" + field + '\'' +
               '}';
    }

}
