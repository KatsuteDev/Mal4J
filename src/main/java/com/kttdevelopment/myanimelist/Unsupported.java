package com.kttdevelopment.myanimelist;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * Unsupported annotation indicates that the method is structurally functional, however is not yet supported with an API endpoint.
 *
 * @see UnsupportedMethodException
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={MODULE, PACKAGE, TYPE, CONSTRUCTOR, METHOD})
public @interface Unsupported {

}
