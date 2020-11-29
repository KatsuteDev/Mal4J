package com.kttdevelopment.myanimelist;

public final class ConnectionForbiddenException extends RuntimeException {

    ConnectionForbiddenException(final String message){
        super("Failed to execute request (denied by server): " + message);
    }

}
