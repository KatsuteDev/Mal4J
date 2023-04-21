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

import dev.katsute.mal4j.forum.property.ForumTopicCreator;
import dev.katsute.mal4j.property.ID;

import java.util.Date;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get</a> <br>
 * Represents a forum topic's details.
 *
 * @since 1.0.0
 * @version 2.0.0
 * @author Katsute
 */
public abstract class ForumTopic implements ID {

    /**
     * Returns the board id.
     *
     * @return board id
     *
     * @see #getSubBoardID()
     * @since 2.0.0
     */
    public abstract Long getBoardID();

    /**
     * Returns the subboard id.
     *
     * @return subboard id
     *
     * @see #getBoardID()
     * @since 2.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public abstract Long getSubBoardID();

    /**
     * Returns the title of the topic.
     *
     * @return title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    /**
     * Returns when the topic was created at.
     *
     * @return topic creation time
     *
     * @see #getCreatedAtEpochMillis()
     * @since 1.0.0
     */
    public abstract Date getCreatedAt();

    /**
     * Returns when the topic was created at as milliseconds since epoch.
     *
     * @return topic creation time
     *
     * @see #getCreatedAt()
     * @since 1.0.0
     */
    public abstract Long getCreatedAtEpochMillis();

    /**
     * Returns who created the topic.
     *
     * @return topic creator
     *
     * @see ForumTopicCreator
     * @since 1.0.0
     */
    public abstract ForumTopicCreator getCreatedBy();

    /**
     * Returns the total post count.
     *
     * @return total posts
     *
     * @since 1.0.0
     */
    public abstract Integer getPostsCount();

    /**
     * Returns when the last post was created at.
     *
     * @return last posted time
     *
     * @see #getLastPostCreatedAtEpochMillis()
     * @since 1.0.0
     */
    public abstract Date getLastPostCreatedAt();

    /**
     * Returns when the last post was created at as milliseconds since epoch.
     *
     * @return last posted time
     *
     * @see #getLastPostCreatedAt()
     * @since 1.0.0
     */
    public abstract Long getLastPostCreatedAtEpochMillis();

    /**
     * Returns who the last post was created by.
     *
     * @return last poster
     *
     * @see ForumTopicCreator
     * @since 1.0.0
     */
    public abstract ForumTopicCreator getLastPostCreatedBy();

    /**
     * Returns if the topic is locked.
     *
     * @return locked
     *
     * @since 1.0.0
     */
    public abstract Boolean isLocked();

}