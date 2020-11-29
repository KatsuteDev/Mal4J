package com.kttdevelopment.myanimelist;

public final class InvalidAuthException extends RuntimeException {

    InvalidAuthException(final String message){
        super("Failed to execute request (OAuth token was invalid or expired): " + message);
    }

}
