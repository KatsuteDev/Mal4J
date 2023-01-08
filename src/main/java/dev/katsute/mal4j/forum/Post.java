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

package dev.katsute.mal4j.forum;

import dev.katsute.mal4j.forum.property.PostAuthor;
import dev.katsute.mal4j.property.ID;

import java.util.Date;

/**
 * Represents a forum post.
 *
 * @see ForumTopicDetail#getPosts()
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public abstract class Post implements ID {

    // API methods

    /**
     * Returns the post number.
     *
     * @return post number
     *
     * @since 1.0.0
     */
    public abstract Integer getNumber();

    /**
     * Returns when the post was created at.
     *
     * @return post creation time
     *
     * @see #getCreatedAtEpochMillis()
     * @since 1.0.0
     */
    public abstract Date getCreatedAt();

    /**
     * Returns when the post was created at as milliseconds since epoch.
     *
     * @return post creation time
     *
     * @see #getCreatedAt()
     * @since 1.0.0
     */
    public abstract Long getCreatedAtEpochMillis();

    /**
     * Returns the post author.
     *
     * @return post author
     *
     * @see PostAuthor
     * @since 1.0.0
     */
    public abstract PostAuthor getAuthor();

    /**
     * Returns the post body.
     *
     * @return post body
     *
     * @since 1.0.0
     */
    public abstract String getBody();

    /**
     * Returns the post signature.
     *
     * @return post signature
     *
     * @since 1.0.0
     */
    public abstract String getSignature();

    // additional methods

    /**
     * Returns the forum topic that the post is from.
     *
     * @return forum topic
     *
     * @see ForumTopicDetail
     * @since 1.0.0
     */
    public abstract ForumTopicDetail getForumTopicDetail();

}
