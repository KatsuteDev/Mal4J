package com.kttdevelopment.myanimelist;

/**
 * Thrown if the server does not return a 200 OK response.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class HTTPException extends RuntimeException {

    HTTPException(final String URL, final int code, final String message){
        super("Server returned code " + code + " from '" + URL + "': " + message);
    }

}
