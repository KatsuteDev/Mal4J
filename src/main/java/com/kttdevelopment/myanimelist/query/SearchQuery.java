package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.*;

import java.util.List;

/**
 * Represents a search query.
 *
 * @param <T> this
 * @param <R> response
 */
@SuppressWarnings({"unchecked"})
abstract class SearchQuery<T extends SearchQuery<T,R>,R> {

    protected final MyAnimeListService service;

    protected Integer limit;
    protected Integer offset;

    SearchQuery(final MyAnimeListService service) {
        this.service = service;
    }

    /**
     * Sets maximum amount of listings to return.
     *
     * @param limit limit
     * @return search query
     *
     * @since 1.0.0
     */
    public final T withLimit(final int limit){
        this.limit = limit;
        return (T) this;
    }

    /**
     * Sets the offset.
     *
     * @param offset offset
     * @return search query
     *
     * @since 1.0.0
     */
    public final T withOffset(final int offset){
        this.offset = offset;
        return (T) this;
    }

    /**
     * Runs the search query.
     *
     * @return search listings
     * @throws InvalidParametersException if parameters are invalid
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     *
     * @since 1.0.0
     */
    public abstract List<R> search();

    /**
     * Runs the search query and returns an iterable.
     *
     * @return search iterable
     * @throws InvalidParametersException if parameters are invalid
     * @throws InvalidAuthException if auth token is invalid or expired
     * @throws ConnectionForbiddenException if the server does not allow the request
     * @throws FailedRequestException if the client failed to execute the request
     *
     * @since 1.0.0
     */
    public abstract PaginatedIterator<R> searchAll();

}
