package com.kttdevelopment.myanimelist.depreciated.net.exception;

public final class ForbiddenRequestException extends RuntimeException {

    public ForbiddenRequestException(final String message){
        super("Failed to execute request (connection forbidden):" + message);
    }

}
