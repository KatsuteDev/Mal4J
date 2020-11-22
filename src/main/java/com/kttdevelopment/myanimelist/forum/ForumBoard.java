package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents a forum board.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumBoard implements ID {

    /**
     * Returns the board title.
     *
     * @return title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    /**
     * Returns the board description.
     *
     * @return description
     *
     * @since 1.0.0
     */
    public abstract String getDescription();

    /**
     * Returns a list of subboards.
     *
     * @return subboard
     *
     * @see ForumSubBoard
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public abstract ForumSubBoard[] getSubBoards();

}
