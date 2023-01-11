/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
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

package dev.katsute.mal4j.query;

import dev.katsute.mal4j.property.*;

import java.util.*;

/**
 * Represents a list update.
 *
 * @param <T> this
 * @param <R> response
 * @param <S> status type
 *
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
@SuppressWarnings("unchecked")
public abstract class ListUpdate<T extends ListUpdate<T,R,S>,R extends ListStatus<?>,S extends FieldEnum> {

    protected final long id;

    protected String status;
    protected Integer score;

    protected Long startDate, finishDate;

    protected Integer priority;
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
     * @see #status(String)
     * @see dev.katsute.mal4j.anime.property.AnimeStatus
     * @see dev.katsute.mal4j.manga.property.MangaStatus
     * @since 1.0.0
     */
    public final T status(final S status){
        return status(status.field());
    }

    /**
     * Sets the status.
     * <br>
     * It is recommended to use {@link #status(FieldEnum)} rather than this method.
     * This method should only be used if the status is missing from {@link dev.katsute.mal4j.anime.property.AnimeStatus} or {@link dev.katsute.mal4j.manga.property.MangaStatus}.
     *
     * @param status raw status
     * @return list status
     *
     * @see #status(FieldEnum)
     * @since 2.9.0
     */
    public final T status(final String status){
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
     * @see #priority(Integer)
     * @see Priority
     * @since 1.0.0
     */
    public final T priority(final Priority priority){
        return priority(priority.value());
    }

    /**
     * Sets the priority.
     * <br>
     * It is recommended to use {@link #priority(Priority)} rather than this method.
     * This method should only be used if the priority is missing from {@link Priority}.
     *
     * @param priority raw priority
     * @return list update
     *
     * @see #priority(Priority)
     * @since 2.9.0
     */
    public final T priority(final Integer priority){
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
