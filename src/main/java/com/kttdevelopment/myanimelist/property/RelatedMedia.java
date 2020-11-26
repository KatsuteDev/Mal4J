package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.RelatedAnime;
import com.kttdevelopment.myanimelist.manga.RelatedManga;

/**
 * Represents a relationship.
 *
 * @see RelatedAnime
 * @see RelatedManga
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class RelatedMedia {

    /**
     * Returns what type of relationship the object has.
     *
     * @return relationship type
     *
     * @see RelationType
     * @since 1.0.0
     */
    public abstract RelationType getRelationType();

    /**
     * Returns the display name for the relationship.
     *
     * @return relationship display name
     *
     * @since 1.0.0
     */
    public abstract String getRelationTypeFormat();

}
