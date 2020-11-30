package com.kttdevelopment.myanimelist.forum;

import com.kttdevelopment.myanimelist.MyAnimeList;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/forum_boards_get">https://myanimelist.net/apiconfig/references/api/v2#operation/forum_boards_get</a> <br>
 *
 * Represents a forum category.
 *
 * @see MyAnimeList#getForumBoards()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumCategory {

    /**
     * Returns the forum category title.
     *
     * @return title
     *
     * @since 1.0.0
     */
    public abstract String getTitle();

    /**
     * Returns the forum boards in the category.
     *
     * @return forum boards
     *
     * @see ForumBoard
     * @since 1.0.0
     */
    public abstract ForumBoard[] getForumBoards();

}
