package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.MediaItem;

/**
 * Represents a Manga's publishing status.
 *
 * @see MediaItem#getStatus()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum MangaPublishStatus {

    Publishing      ("currently_publishing"),
    NotYetPublished ("not_yet_published"),
    Finished        ("finished");

    private final String field;

    MangaPublishStatus(final String field){
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
    public static MangaPublishStatus asEnum(final String string){
        for(final MangaPublishStatus value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
