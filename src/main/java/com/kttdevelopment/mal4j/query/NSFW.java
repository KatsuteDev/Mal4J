package com.kttdevelopment.mal4j.query;

/**
 * Indicates that the query accepts a NSFW parameter
 *
 * @param <T> this
 *
 * @see NSFWSearchQuery
 * @since 1.0.0
 */
interface NSFW<T> {

    /**
     * Sets query to also return NSFW results.
     *
     * @return query
     *
     * @see #includeNSFW(boolean)
     * @since 1.0.0
     */
    T includeNSFW();

    /**
     * Sets if the query will return NSFW results.
     *
     * @param nsfw nsfw
     * @return query
     *
     * @see #includeNSFW()
     * @since 1.0.0
     */
    T includeNSFW(final boolean nsfw);


}
