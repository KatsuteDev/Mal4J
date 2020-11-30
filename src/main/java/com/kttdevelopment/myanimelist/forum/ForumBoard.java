package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_boards_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_boards_get</a> <br>
 *
 * Represents a forum board.
 *
 * @see ForumCategory#getForumBoards()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
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
