/*
 * Copyright (C) 2021 Ktt Development
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.kttdevelopment.mal4j.query;

import com.kttdevelopment.mal4j.property.ListStatus;
import com.kttdevelopment.mal4j.property.Priority;

import java.util.*;

/**
 * Represents a list update.
 *
 * @param <T> this
 * @param <R> response
 * @param <S> status type
 *
 * @since 1.0.0
 * @version 1.1.0
 * @author Ktt Development
 */
@SuppressWarnings("unchecked")
public abstract class ListUpdate<T extends ListUpdate<T,R,S>,R extends ListStatus<?>,S extends Enum<?>> {

    protected final long id;

    protected S status;
    protected Integer score;

    protected Long startDate, finishDate;

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
     * Sets the start date.
     *
     * @param date date
     * @return list update
     *
     * @since 1.0.0
     * @see Date
     * @see GregorianCalendar#GregorianCalendar(int, int, int)
     * @see GregorianCalendar#getTime()
     * @see #finishDate(Date)
     */
    public final T startDate(final Date date){
        this.startDate = date.getTime();
        return (T) this;
    }

    /**
     * Sets the finish date.
     *
     * @param date date
     * @return list update
     *
     * @since 1.0.0
     * @see Date
     * @see GregorianCalendar#GregorianCalendar(int, int, int)
     * @see GregorianCalendar#getTime()
     * @see #startDate(Date)
     */
    public final T finishDate(final Date date){
        this.finishDate = date.getTime();
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
        this.tags = tags == null ? null : Arrays.asList(tags);
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
