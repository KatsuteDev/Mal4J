package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents a poll's options.
 *
 * @see Poll
 * @see Poll#getOptions()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class PollOption implements ID {

    // API methods

    /**
     * Returns the text in the poll option.
     *
     * @return poll text
     *
     * @since 1.0.0
     */
    public abstract String getText();

    /**
     * Returns the total votes for this option.
     *
     * @return poll votes
     *
     * @since 1.0.0
     */
    public abstract int getVotes();

    // additional methods

    /**
     * Returns the poll that the option is a part of.
     *
     * @return poll
     *
     * @see Poll
     * @since 1.0.0
     */
    public abstract Poll getPoll();

}
