package com.kttdevelopment.myanimelist.property;

/**
 * Indicates that the object has a ranking.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface Ranking {

    /**
     * The item's current ranking.
     *
     * @return ranking
     *
     * @see #getPreviousRank()
     * @since 1.0.0
     */
    int getRanking();

    /**
     * Returns the item's previous ranking.
     *
     * @return previous ranking
     *
     * @see #getRanking()
     * @since 1.0.0
     */
    int getPreviousRank();

}
