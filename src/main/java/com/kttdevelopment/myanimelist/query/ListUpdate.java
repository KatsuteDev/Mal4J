package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.property.ListStatus;
import com.kttdevelopment.myanimelist.query.property.Priority;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a list update.
 *
 * @param <T> this
 * @param <R> response
 * @param <S> status type
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("unchecked")
abstract class ListUpdate<T extends ListUpdate<T,R,S>,R extends ListStatus<?>,S extends Enum<?>> {

    protected final long id;

    protected S status;
    protected Integer score;
    protected Priority priority;
    protected List<String> tags;
    protected String comments;

    ListUpdate(final long id){
        this.id = id;
    }

    /**
     * Sets the status.
     *
     * @param status status
     * @return list update
     *
     * @since 1.0.0
     */
    public final T status(final S status){
        this.status = status;
        return (T) this;
    }

    /**
     * Sets the score.
     *
     * @param score score
     * @return list update
     *
     * @since 1.0.0
     */
    public final T score(final int score){
        this.score = score;
        return (T) this;
    }

    /**
     * Sets the priority.
     *
     * @param priority priority
     * @return list update
     *
     * @see Priority
     * @since 1.0.0
     */
    public final T priority(final Priority priority){
        this.priority = priority;
        return (T) this;
    }

    /**
     * Sets the tags.
     *
     * @param tags tags
     * @return list update
     *
     * @since 1.0.0
     */
    public final T tags(final String... tags){
        this.tags = Arrays.asList(tags);
        return (T) this;
    }

    /**
     * Sets the comments.
     *
     * @param comments comments
     * @return list update
     *
     * @since 1.0.0
     */
    public final T comments(final String comments){
        this.comments = comments;
        return (T) this;
    }

    /**
     * Updates the list on MyAnimeList
     *
     * @return list
     *
     * @since 1.0.0
     */
    public abstract R update();

}
