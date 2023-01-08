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

import dev.katsute.mal4j.property.ID;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_boards_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_boards_get</a> <br>
 * Represents a forum board.
 *
 * @see ForumCategory#getForumBoards()
 * @since 1.0.0
 * @version 1.0.0
 * @author Katsute
 */
public abstract class ForumBoard implements ID {

    // API methods

    /**
     * Returns the forum title.
     *
     * @return forum title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    /**
     * Returns the forum description.
     *
     * @return forum description.
     * @since 1.0.0
     */
    public abstract String getDescription();

    /**
     * Returns the forum subboards.
     *
     * @return forum subboards
     *
     * @see ForumSubBoard
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public abstract ForumSubBoard[] getSubBoards();

    // additional methods

    /**
     * Returns the category that the forum is from.
     *
     * @return forum category
     *
     * @see ForumCategory
     * @since 1.0.0
     */
    public abstract ForumCategory getCategory();

}
