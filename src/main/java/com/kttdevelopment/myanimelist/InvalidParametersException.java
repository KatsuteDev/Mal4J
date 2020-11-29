package com.kttdevelopment.myanimelist;

public final class InvalidParametersException extends RuntimeException {

    InvalidParametersException(final String message){
        super("Failed to execute request (parameters were invalid): " + message);
    }

}
