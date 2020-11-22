package com.kttdevelopment.myanimelist.forum;

/**
 * Represents a forum category.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumCategory {

    /**
     * Returns the category title.
     *
     * @return category title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    /**
     * Returns the forum boards.
     *
     * @return forum boards
     *
     * @see ForumBoard
     * @since 1.0.0
     */
    public abstract ForumBoard[] getForumBoards();

}
