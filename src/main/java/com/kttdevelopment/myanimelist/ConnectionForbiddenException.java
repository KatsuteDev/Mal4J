package com.kttdevelopment.myanimelist;

/**
 * Thrown if the request returns a code 403 (forbidden). Indicates that the server will not allow the request.
 */
public final class ConnectionForbiddenException extends RuntimeException {

    ConnectionForbiddenException(final String message){
        super("Failed to execute request (denied by server): " + message);
    }

}
