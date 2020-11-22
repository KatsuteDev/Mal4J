package com.kttdevelopment.myanimelist.property;

/**
 * Represents a relationship.
 *
 * @see com.kttdevelopment.myanimelist.anime.AnimeRelation
 * @see com.kttdevelopment.myanimelist.manga.MangaRelation
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
    public abstract String getRelationType();

    /**
     * Returns the display name for the relationship.
     *
     * @return relationship display name
     *
     * @since 1.0.0
     */
    public abstract String getRelationTypeFormat();

}
