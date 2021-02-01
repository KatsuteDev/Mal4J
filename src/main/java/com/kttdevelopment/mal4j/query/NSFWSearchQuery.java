package com.kttdevelopment.mal4j.query;

/**
 * Indicates that the query accepts a NSFW parameter
 *
 * @param <T> this
 * @param <R> response
 *
 * @see NSFW
 * @since 1.0.0
 */
abstract class NSFWSearchQuery<T extends NSFWSearchQuery<T,R>,R> extends SearchQuery<T,R> implements NSFW<T> {

    protected Boolean nsfw;

    @Override
    public final T includeNSFW(){
        return includeNSFW(true);
    }

    @SuppressWarnings("unchecked")
    public final T includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return (T) this;
    }

}
