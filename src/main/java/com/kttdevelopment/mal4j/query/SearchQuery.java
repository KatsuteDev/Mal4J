package com.kttdevelopment.mal4j.query;

/**
 * Represents a query with a search.
 *
 * @param <T> this
 * @param <R> response
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("unchecked")
public abstract class SearchQuery<T extends SearchQuery<T,R>,R> extends FieldQuery<T,R> {

    protected String query;

    SearchQuery() { }

    /**
     * Sets the search query.
     *
     * @param query query
     * @return search query
     *
     * @since 1.0.0
     */
    public final T withQuery(final String query){
        this.query = query;
        return (T) this;
    }

}
