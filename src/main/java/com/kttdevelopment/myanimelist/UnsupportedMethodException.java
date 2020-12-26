package com.kttdevelopment.myanimelist;

/**
 * Unsupported exception indicates that the method is structurally functional, however is not yet supported with an API endpoint.
 *
 * @see Unsupported
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class UnsupportedMethodException extends RuntimeException {

    UnsupportedMethodException(){
        super("Method is not currently supported by the API. This may be changed in the future.");
    }

}
