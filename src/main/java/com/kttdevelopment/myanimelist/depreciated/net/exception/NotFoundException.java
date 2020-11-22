package com.kttdevelopment.myanimelist.depreciated.net.exception;

public final class NotFoundException extends RuntimeException {

    public NotFoundException(final String message){
        super("Failed to execute request (not found):" + message);
    }

}
