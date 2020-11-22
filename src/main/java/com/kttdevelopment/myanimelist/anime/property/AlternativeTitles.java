package com.kttdevelopment.myanimelist.anime.property;

/**
 * Represents alternative titles.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AlternativeTitles {

    /**
     * Returns a list of synonyms.
     *
     * @return synonyms
     *
     * @since 1.0.0
     */
    public abstract String[] getSynonyms();

    /**
     * Returns the English name.
     *
     * @return English name
     *
     * @since 1.0.0
     */
    public abstract String getEnglish();

    /**
     * Returns the Japanese name.
     *
     * @return Japanese name
     *
     * @since 1.0.0
     */
    public abstract String getJapanese();

}
