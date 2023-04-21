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

package dev.katsute.mal4j.anime.property;

import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.property.ID;

import java.util.Date;

/**
 * Represents a promotional video (PV) or trailer of an Anime.
 *
 * @see Anime#getVideos()
 * @since 2.10.0
 * @version 2.10.0
 * @author Katsute
 */
public abstract class Video implements ID {

    /**
     * Returns the video title.
     *
     * @return video title
     *
     * @since 2.10.0
     */
    public abstract String getTitle();

    /**
     * Returns the video URL.
     *
     * @return video URL
     *
     * @see #getThumbnail()
     * @since 2.10.0
     */
    public abstract String getURL();

    /**
     * Returns the thumbnail URL.
     *
     * @return thumbnail URL
     *
     * @see #getURL()
     * @since 2.10.0
     */
    public abstract String getThumbnail();

    /**
     * Returns when the video was added.
     *
     * @return video add time
     *
     * @see #getCreatedAtEpochMillis()
     * @since 2.10.0
     */
    public abstract Date getCreatedAt();

    /**
     * Returns when the video was added as milliseconds since epoch.
     *
     * @return video add time
     *
     * @see #getCreatedAt()
     * @since 2.10.0
     */
    public abstract Long getCreatedAtEpochMillis();

    /**
     * Returns when the video was updated.
     *
     * @return video update time
     *
     * @see #getUpdatedAtEpochMillis()
     * @since 2.10.0
     */
    public abstract Date getUpdatedAt();

    /**
     * Returns when the video was updated as milliseconds since epoch.
     *
     * @return video update time
     *
     * @see #getUpdatedAt()
     * @since 2.10.0
     */
    public abstract Long getUpdatedAtEpochMillis();

}