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

package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.query.property.Priority;

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
     * @see com.kttdevelopment.myanimelist.anime.property.AnimeStatus
     * @see com.kttdevelopment.myanimelist.manga.property.MangaStatus
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
     * @see #getFinishDate()
     * @since 1.0.0
     */
    long getStartDate();

    /**
     * Returns the finish date for the listing.
     *
     * @return finish date
     *
     * @see #getStartDate()
     * @since 1.0.0
     */
    long getFinishDate();

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
     * @since 1.0.0
     */
    long getUpdatedAt();

}
