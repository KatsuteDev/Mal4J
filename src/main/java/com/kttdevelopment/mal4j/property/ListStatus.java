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

package com.kttdevelopment.mal4j.property;

import com.kttdevelopment.mal4j.query.property.Priority;

import java.util.Date;

/**
 * Indicates that the object is a list status.
 *
 * @param <Status> status state type
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface ListStatus<Status extends Enum<?>> {

    /**
     * Returns the status of the listing.
     *
     * @return status
     *
     * @see com.kttdevelopment.mal4j.anime.property.AnimeStatus
     * @see com.kttdevelopment.mal4j.manga.property.MangaStatus
     * @since 1.0.0
     */
    Status getStatus();

    /**
     * Returns the score of the listing.
     *
     * @return score
     *
     * @since 1.0.0
     */
    int getScore();

    /**
     * Returns the start date for the listing.
     *
     * @return start date
     *
     * @see #getStartDateEpochMillis()
     * @see #getFinishDate()
     * @see #getFinishDateEpochMillis()
     * @since 1.0.0
     */
    Date getStartDate();

    /**
     * Returns the start date for the listing in milliseconds since epoch.
     *
     * @return start date
     *
     * @see #getStartDate()
     * @see #getFinishDate()
     * @see #getFinishDateEpochMillis()
     * @since 1.0.0
     */
    long getStartDateEpochMillis();

    /**
     * Returns the finish date for the listing.
     *
     * @return finish date
     *
     * @see #getFinishDateEpochMillis()
     * @see #getStartDate()
     * @see #getStartDateEpochMillis()
     * @since 1.0.0
     */
    Date getFinishDate();

    /**
     * Returns the finish date for the listing in milliseconds since epoch.
     *
     * @return finish date
     *
     * @see #getFinishDate()
     * @see #getStartDate()
     * @see #getStartDateEpochMillis()
     * @since 1.0.0
     */
    long getFinishDateEpochMillis();

    /**
     * Returns the priority for the listing.
     *
     * @return priority
     *
     * @see Priority
     * @since 1.0.0
     */
    Priority getPriority();

    /**
     * Returns the tags for the listing.
     *
     * @return tags
     *
     * @since 1.0.0
     */
    String[] getTags();

    /**
     * Returns the comments for the listing.
     *
     * @return comments
     *
     * @since 1.0.0
     */
    String getComments();

    /**
     * Returns when the listing was last updated.
     *
     * @return last updated
     *
     * @see #getUpdatedAtEpochMillis()
     * @since 1.0.0
     */
    Date getUpdatedAt();

    /**
     * Returns when the listing was last updated in milliseconds since epoch.
     *
     * @return last updated
     *
     * @see #getUpdatedAt()
     * @since 1.0.0
     */
    long getUpdatedAtEpochMillis();

}
