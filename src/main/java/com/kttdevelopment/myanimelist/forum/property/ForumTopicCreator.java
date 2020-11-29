package com.kttdevelopment.myanimelist.forum.property;

import com.kttdevelopment.myanimelist.forum.ForumTopicDetail;
import com.kttdevelopment.myanimelist.property.IDN;
import com.kttdevelopment.myanimelist.user.User;

/**
 * Represents a forum topic creator.
 *
 * @see ForumTopicDetail#getCreatedBy()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class ForumTopicCreator implements IDN {

    // additional methods

    public abstract User getUser();

}
