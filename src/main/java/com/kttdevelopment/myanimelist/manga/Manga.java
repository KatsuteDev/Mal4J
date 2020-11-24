package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.ExtendedPreview;

/**
 * Represents the full details of a Manga.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Manga implements ExtendedPreview {

    /**
     * Returns the Manga type.
     *
     * @return Manga type
     *
     * @see MangaType
     * @since 1.0.0
     */
    public abstract MangaType getMangaType();

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
     * Returns the authors.
     *
     * @return authors
     *
     * @since 1.0.0
     */
    public abstract Author[] getAuthors();

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

    /**
     * Returns the statistics for the Manga.
     *
     * @return statistics
     *
     * @see MangaStatistics
     * @since 1.0.0
     */
    public abstract MangaStatistics getStatistics();

}
