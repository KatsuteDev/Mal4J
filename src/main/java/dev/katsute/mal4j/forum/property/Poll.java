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

package dev.katsute.mal4j.forum.property;

import dev.katsute.mal4j.forum.ForumTopicDetail;
import dev.katsute.mal4j.property.ID;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get</a> <br>
 *
 * Represents a forum poll.
 *
 * @see ForumTopicDetail#getPoll()
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public abstract class Poll implements ID {

    // API methods

    /**
     * Returns the poll's question.
     *
     * @return poll question
     *
     * @since 1.0.0
     */
    public abstract String getQuestion();

    /**
     * Returns if the poll is closed.
     *
     * @return closed
     *
     * @since 1.0.0
     */
    public abstract Boolean isClosed();

    /**
     * Returns the poll's options.
     *
     * @return options
     *
     * @see PollOption
     * @since 1.0.0
     */
    public abstract PollOption[] getOptions();

    // additional methods

    /**
     * Returns the topic that the poll is from.
     *
     * @return forum topic
     *
     * @see ForumTopicDetail
     * @since 1.0.0
     */
    public abstract ForumTopicDetail getForumTopicDetail();

}