package com.kttdevelopment.myanimelist.property;

/**
 * Represents a related media.
 *
 * @see com.kttdevelopment.myanimelist.anime.RelatedAnime
 * @see com.kttdevelopment.myanimelist.manga.RelatedManga
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Deveopment
 */
public abstract class RelatedMedia {

    /**
     * Returns how the media is related.
     *
     * @return relation type
     *
     * @see RelationType
     * @since 1.0.0
     */
    public abstract RelationType getRelationType();

    /**
     * Returns how the media is related by its display name.
     *
     * @return relation type
     *
     * @see #getRelationType()
     * @since 1.0.0
     */
    public abstract String getRelationTypeFormat();

}
