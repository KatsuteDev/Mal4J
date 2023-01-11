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

package dev.katsute.mal4j.property;

import java.util.Date;

/**
 * Indicates that the object is a list status.
 *
 * @param <Status> status state type
 *
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public interface ListStatus<Status extends Enum<?>> {

    /**
     * Returns the status of the listing.
     *
     * @return status
     *
     * @see #getRawStatus()
     * @see dev.katsute.mal4j.anime.property.AnimeStatus
     * @see dev.katsute.mal4j.manga.property.MangaStatus
     * @since 1.0.0
     */
    Status getStatus();

    /**
     * Returns the raw status.
     * <br>
     * It is recommended to use {@link #getStatus()} rather than this method.
     * This method should only be used if the status value is missing from {@link dev.katsute.mal4j.anime.property.AnimeStatus} or {@link dev.katsute.mal4j.manga.property.MangaStatus}.
     *
     * @return status
     *
     * @see #getStatus()
     * @since 2.9.0
     */
    String getRawStatus();

    /**
     * Returns the score of the listing.
     *
     * @return score
     *
     * @since 1.0.0
     */
    Integer getScore();

    /**
     * Returns the start date for the listing.
     *
     * @return start date
     *
     * @see #getFinishDate()
     * @since 1.0.0
     */
    Date getStartDate();

    /**
     * Returns the finish date for the listing.
     *
     * @return finish date
     *
     * @see #getStartDate()
     * @since 1.0.0
     */
    Date getFinishDate();

    /**
     * Returns the priority for the listing.
     *
     * @return priority
     *
     * @see #getRawPriority()
     * @see Priority
     * @since 1.0.0
     */
    Priority getPriority();

    /**
     * Returns the raw priority.
     * <br>
     * It is recommended to use {@link #getPriority()} and {@link Priority#value()} rather than this method.
     * This method should only be used if the priority value is missing from {@link Priority}.
     *
     * @return priority
     *
     * @see #getPriority()
     * @since 2.9.0
     */
    Integer getRawPriority();

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
    Long getUpdatedAtEpochMillis();

}
