package com.kttdevelopment.myanimelist;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

abstract class APIStruct {

    @Documented
    @Target(METHOD)
    @Retention(RUNTIME)
    @interface Endpoint {

        String method() default "GET";

        String value();

    }

    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Path {

        String value();

        boolean encoded() default false;

    }

    @Documented
    @Target(METHOD)
    @Retention(RUNTIME)
    @interface FormUrlEncoded {

    }

    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Header {

        String value();

    }

    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Query {

        String value();

        boolean encoded() default false;

    }

    @Documented
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Field {

        String value();

        boolean encoded() default false;

    }

    static final class Response<T>{

        private final T response;
        private final int code;

        Response(final T response, final int code){
            this.response = response;
            this.code = code;
        }

        final T body(){
            return response;
        }

        final int code(){
            return code;
        }

        @Override
        public String toString(){
            return "Response{" +
                   "response=" + response +
                   ", code=" + code +
                   '}';
        }

    }

}
