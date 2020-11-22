package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.manga.property.*;

/**
 * Represents the full details of a Manga.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Manga extends MangaPreview {

    /**
     * Returns the Manga type.
     *
     * @return Manga type
     *
     * @see MangaType
     * @since 1.0.0
     */
    public abstract MangaType getMangaTYpe();

    /**
     * Returns the Manga publishing status.
     *
     * @return status
     *
     * @see MangaPublishStatus
     * @since 1.0.0
     */
    public abstract MangaPublishStatus getStatus();

    /**
     * Returns the Manga list status
     *
     * @return list status
     *
     * @see MangaListStatus
     * @since 1.0.0
     */
    public abstract MangaListStatus getListStatus();

    /**
     * Returns the Manga volume count.
     *
     * @return volume count
     *
     * @see #getChapters()
     * @since 1.0.0
     */
    public abstract int getVolumes();

    /**
     * Returns the Manga chapter count.
     *
     * @return chapter count
     *
     * @see #getVolumes()
     * @since 1.0.0
     */
    public abstract int getChapters();

    /**
     * Returns a list of Manga recommendations.
     *
     * @return recommendations
     *
     * @see MangaRecommendation
     * @since 1.0.0
     */
    public abstract MangaRecommendation[] getRecommendations();

    /**
     * Returns a list of the publishers.
     *
     * @return publishers
     *
     * @see Publisher
     * @since 1.0.0
     */
    public abstract Publisher[] getSerialization();


}
