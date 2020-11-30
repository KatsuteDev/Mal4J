package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents a forum subboard.
 *
 * @see ForumBoard#getSubBoards()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class ForumSubBoard implements ID {

    // API methods

    /**
     * Returns the subboard title.
     *
     * @return title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    // additional methods

    /**
     * Returns the forum board that the subboard is from.
     *
     * @return forum board
     *
     * @see ForumBoard
     * @since 1.0.0
     */
    public abstract ForumBoard getBoard();

}
