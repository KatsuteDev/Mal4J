package com.kttdevelopment.mal4j;

/**
 * Thrown if the Android version is not compatible with a genuine Java runtime.
 *
 * @since 2.2.1
 * @version 2.2.1
 * @author Katsute
 */
public final class AndroidCompatibilityException extends RuntimeException {

    AndroidCompatibilityException(final String message){
        super(message);
    }

}
