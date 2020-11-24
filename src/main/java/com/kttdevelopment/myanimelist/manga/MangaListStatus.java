package com.kttdevelopment.myanimelist.manga;


import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.property.ListStatus;

/**
 * Represents a Manga list status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface MangaListStatus extends ListStatus {

    /**
     * Returns Manga status.
     *
     * @return status
     *
     * @see MangaStatus
     * @since 1.0.0
     */
    MangaStatus getStatus();

    /**
     * Returns total volumes read.
     *
     * @return volumes read
     *
     * @since 1.0.0
     */
    int getVolumesRead();

    /**
     * Returns total chapters read.
     *
     * @return chapters read
     *
     * @since 1.0.0
     */
    int getChaptersRead();

    /**
     * Returns if rereading.
     *
     * @return rereading
     *
     * @since 1.0.0
     */
    boolean isRereading();

}
