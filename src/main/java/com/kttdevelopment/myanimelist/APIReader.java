package com.kttdevelopment.myanimelist;

import java.lang.annotation.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

abstract class APIReader {

    @SuppressWarnings("unchecked")
    static <C> C create(final String baseURL, final Class<C> service){
        if(!service.isInterface())
            throw new IllegalArgumentException("Service must be an interface");
        final InvocationHandler handler = new APICall(baseURL);
        return (C)
            Proxy.newProxyInstance(
                service.getClassLoader(),
                new Class<?>[]{service},
               handler
            );
    }

    static class RequestMethod {

        static String
            GET = "GET",
            PATCH = "PATCH",
            DELETE = "DELETE";

    }

    /**
     * Makes a request.
     */
    @Documented
    @Target(METHOD)
    @Retention(RUNTIME)
    @interface ENDPOINT {

        String method();

        String value(); // the URL

    }

    /**
     * Indicates that the request will use form URL encoding.
     *
     * @see Field
     */
    @Documented
    @Target(METHOD)
    @Retention(RUNTIME)
    @interface FormUrlEncoded{

    }

    /**
     * Indicates that parameter is a header value.
     */
    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Header {

        String value();

    }

    /**
     * Indicates that parameter is a path value.
     */
    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Path {

        String value();

        boolean encoded() default false;

    }

    /**
     * Indicates that parameter is a query value.
     */
    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Query {

        String value();

        boolean encoded() default false;

    }

    /**
     * Indicates that parameter is a field value.
     *
     * @see FormUrlEncoded
     */
    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Field {

        String value();

        boolean encoded() default false;

    }

    interface Response<T> {

        T get();

    }

}
