package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents a forum poll.
 *
 * @see PollOption
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Poll implements ID {

    /**
     * Returns the question.
     *
     * @return question
     *
     * @since 1.0.0
     */
    public abstract String getQuestion();

    /**
     * Returns if the poll's closed state.
     *
     * @return closed
     *
     * @since 1.0.0
     */
    public abstract int getClosed();

    /**
     * Returns the poll's options.
     *
     * @return poll options
     *
     * @see PollOption
     * @since 1.0.0
     */
    public abstract PollOption[] getOptions();

}
