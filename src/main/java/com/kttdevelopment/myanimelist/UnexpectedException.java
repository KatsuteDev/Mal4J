package com.kttdevelopment.myanimelist;

final class UnexpectedException extends RuntimeException {

    UnexpectedException(final Throwable e){
        super("An unexpected exception was thrown, please report this to the developers. " + e.getMessage(), e.getCause());
    }

}
