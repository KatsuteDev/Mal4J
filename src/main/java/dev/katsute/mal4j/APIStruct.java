/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package dev.katsute.mal4j;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * Annotations used to structure API calls.
 *
 * @see APICall
 * @see APICall#APICall(String, Method, Object...)
 */
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

    /**
     * Represents HTTP response.
     *
     * @param <T> response type
     */
    public static final class Response<T>{

        private final String URL;
        private final String raw;
        private final T response;
        private final int code;

        Response(final String URL, final String raw, final T response, final int code){
            this.URL        = URL;
            this.raw        = raw;
            this.response   = response;
            this.code       = code;
        }

        final String URL(){
            return URL;
        }

        final String raw(){
            return raw;
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