package com.kttdevelopment.myanimelist.depreciated.net.exception;

public final class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(final String message){
        super("Failed to execute request (invalid oauth token): " + message);
    }

}
