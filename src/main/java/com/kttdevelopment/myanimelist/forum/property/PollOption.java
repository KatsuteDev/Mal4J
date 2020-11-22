package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.property.ID;

/**
 * Represents a poll option.
 *
 * @see Poll
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class PollOption implements ID {

    /**
     * Returns the option.
     *
     * @return option
     *
     * @since 1.0.0
     */
    public abstract String text();

    /**
     * Returns the total votes
     *
     * @return votes
     *
     * @since 1.0.0
     */
    public abstract int votes();

}
