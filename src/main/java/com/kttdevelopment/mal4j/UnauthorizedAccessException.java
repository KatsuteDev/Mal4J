package com.kttdevelopment.mal4j;

/**
 * Throw if the state was illegally modified.
 *
 * @see MyAnimeListAuthenticator
 * @since 1.0.0
 */
public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(final String message){
        super(message);
    }

}
