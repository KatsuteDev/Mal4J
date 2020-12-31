package com.kttdevelopment.myanimelist;

/**
 * Thrown if a request returns a code 400 (bad request). Indicates that the current parameters are invalid.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class InvalidParametersException extends RuntimeException {

    InvalidParametersException(final String message){
        super("Failed to execute request (parameters were invalid): " + message);
    }

}
