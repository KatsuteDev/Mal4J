package com.kttdevelopment.mal4j;

/**
 * Thrown if the token is either invalid or expired.
 *
 * @since 2.3.0
 * @version 2.3.0
 * @author Katsute
 */
public final class InvalidTokenException extends RuntimeException {

    InvalidTokenException(final String message){
        super(message);
    }

}
