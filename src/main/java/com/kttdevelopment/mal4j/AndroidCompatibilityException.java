package com.kttdevelopment.mal4j;

/**
 * <b>This exception is no longer thrown.</b>
 * <br>
 * Thrown if the Android version is not compatible with a genuine Java runtime.
 *
 * @since 2.2.1
 * @version 2.4.0
 * @author Katsute
 */
@Deprecated
public final class AndroidCompatibilityException extends RuntimeException {

    @SuppressWarnings("SameParameterValue")
    AndroidCompatibilityException(final String message){
        super(message);
    }

}
