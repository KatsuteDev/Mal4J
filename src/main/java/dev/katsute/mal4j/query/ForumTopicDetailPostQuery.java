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

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.forum.Post;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_topic_get</a> <br>
 * Represents a forum topic detail post query.
 *
 * @see MyAnimeList#getForumTopicDetailPostQuery(long)
 * @see LimitOffsetQuery
 * @since 2.0.0
 * @version 2.0.0
 * @author Katsute
 */
public abstract class ForumTopicDetailPostQuery extends LimitOffsetQuery<ForumTopicDetailPostQuery,Post> {

    /**
     * Creates a forum topic detail post query.
     * <br>
     * Do not use this constructor, use {@link MyAnimeList#getForumTopicDetailPostQuery(long)} instead.
     *
     * @see MyAnimeList#getForumTopicDetailPostQuery(long)
     * @since 2.0.0
     */
    public ForumTopicDetailPostQuery() { }

}
