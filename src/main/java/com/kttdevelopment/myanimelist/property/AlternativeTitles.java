package com.kttdevelopment.myanimelist.property;

/**
 * Represents the alternative titles of a media item.
 *
 * @see MediaItem#getAlternativeTitles()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AlternativeTitles {

    /**
     * Returns the synonyms for the title.
     *
     * @return synonyms
     *
     * @since 1.0.0
     */
    public abstract String[] getSynonyms();

    /**
     * Returns the English title.
     *
     * @return English title
     *
     * @since 1.0.0
     */
    public abstract String getEnglish();

    /**
     * Returns the Japanese title.
     *
     * @return Japanese title
     *
     * @since 1.0.0
     */
    public abstract String getJapanese();

}
