package com.kttdevelopment.myanimelist;

/**
 * Thrown if the request returns a code 401 (unauthorized). Indicates that the server requires a valid authorization for the request.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class InvalidAuthException extends RuntimeException {

    InvalidAuthException(final String message){
        super("Failed to execute request (OAuth token was invalid or expired): " + message);
    }

}
