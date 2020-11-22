package com.kttdevelopment.myanimelist.depreciated.net.exception;

public final class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(final String message){
        super("Failed to execute request (invalid paramaters): " + message);
    }

}
