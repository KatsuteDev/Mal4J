package com.kttdevelopment.myanimelist.user.property;

import com.kttdevelopment.myanimelist.user.User;

/**
 * Indicates that a User can be retrieved from the object.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public interface UserRetrievable {

    /**
     * Returns the user.
     *
     * @return user
     *
     * @see User
     * @since 1.0.0
     */
    User getUser();

}
