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
public abstract class RelationType {

    /**
     * Returns what type of relationship the object has.
     *
     * @return relationship type
     *
     * @since 1.0.0
     */
    public abstract String getRelationType(); // todo: could be enum

    /**
     * Returns the display name for the relationship.
     *
     * @return relationship display name
     *
     * @since 1.0.0
     */
    public abstract String getRelationTypeFormat();

}
