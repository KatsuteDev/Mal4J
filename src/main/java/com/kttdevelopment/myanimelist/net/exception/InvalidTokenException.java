package com.kttdevelopment.myanimelist.net.exception;

public final class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(final String message){
        super("Failed to execute request (invalid oauth token): " + message);
    }

}
