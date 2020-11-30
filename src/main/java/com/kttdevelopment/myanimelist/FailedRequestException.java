package com.kttdevelopment.myanimelist;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Thrown if a request can not be completed due to a client side issue.
 */
public final class FailedRequestException extends UncheckedIOException {

    FailedRequestException(final IOException exception){
        super("Failed to execute request (client side error)", exception);
    }

}
