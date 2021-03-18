/*
 * Copyright (C) 2021 Ktt Development
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

package com.kttdevelopment.mal4j;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.kttdevelopment.mal4j.APIStruct.*;

/**
 * Represents an API call.
 */
@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
final class APICall {

    static boolean debug = false;

    private final String method;
    private final String baseURL;
    private final String path;

    /**
     * API call builder.
     *
     * @param method request method
     * @param baseURL base url
     * @param path path
     */
    APICall(final String method, final String baseURL, final String path){
        this.method     = method;
        this.baseURL    = baseURL;
        this.path       = path;
    }

    /**
     * API call from annotated interface method.
     *
     * @param baseURL base url
     * @param method method
     * @param args method arguments
     *
     * @see APIStruct
     */
    APICall(final String baseURL, final Method method, final Object... args){
        this.baseURL = baseURL;

        final Endpoint endpoint = method.getAnnotation(Endpoint.class);
        if(endpoint != null){
            this.method = endpoint.method();
            path = endpoint.value();
        }else{
            this.method = "GET";
            this.path = "";
        }

        if(method.getAnnotation(FormUrlEncoded.class) != null)
            formUrlEncoded = true;

        for(int i = 0, size = method.getParameterAnnotations().length; i < size; i++){
            final Object arg = args[i];
            for(final Annotation annotation : method.getParameterAnnotations()[i]){
                if(arg != null){
                    final Class<? extends Annotation> type = annotation.annotationType();
                    if(type == Path.class)
                        withPathVar(((Path) annotation).value(), arg, ((Path) annotation).encoded());
                    else if(type == Header.class)
                        withHeader(((Header) annotation).value(), Objects.toString(arg));
                    else if(type == Query.class)
                        withQuery(((Query) annotation).value(), arg, ((Query) annotation).encoded());
                    else if(type == Field.class)
                        withField(((Field) annotation).value(), arg, ((Field) annotation).encoded());
                }
            }
        }
    }

    private final Map<String,String> headers = new HashMap<>();

    // \{(.*?)\}
    @SuppressWarnings("RegExpRedundantEscape") // android requires this syntax (#133)
    private static final Pattern pathArg = Pattern.compile("\\{(.*?)\\}");

    private final Map<String,String> pathVars = new HashMap<>();
    private final Map<String,String> queries  = new HashMap<>();

    private boolean formUrlEncoded = false;
    private final Map<String,String> fields = new HashMap<>();

    final APICall withHeader(final String header, final String value){
        if(value == null)
            headers.remove(header);
        else
            headers.put(header, value);
        return this;
    }

    final APICall withPathVar(final String pathVar, final String value){
        return withPathVar(pathVar, value, false);
    }

    final APICall withPathVar(final String pathVar, final Object value, final boolean encoded){
        if(value == null)
            pathVars.remove(pathVar);
        else
            pathVars.put(pathVar, encoded ? Objects.toString(value) : URLEncoder.encode(Objects.toString(value), StandardCharsets.UTF_8));
        return this;
    }

    final APICall withQuery(final String query, final Object value){
        return withQuery(query, value, false);
    }

    final APICall withQuery(final String query, final Object value, final boolean encoded){
        if(value == null)
            queries.remove(query);
        else
            queries.put(query, encoded ? Objects.toString(value) : URLEncoder.encode(Objects.toString(value), StandardCharsets.UTF_8));
        return this;
    }

    final APICall formUrlEncoded(){
        return formUrlEncoded(true);
    }

    final APICall formUrlEncoded(final boolean formUrlEncoded){
        this.formUrlEncoded = formUrlEncoded;
        return this;
    }

    final APICall withField(final String field, final Object value){
        return withField(field, value, false);
    }

    final APICall withField(final String field, final Object value, final boolean encoded){
        if(value == null)
            fields.remove(field);
        else
            fields.put(field, encoded ? Objects.toString(value) : URLEncoder.encode(Objects.toString(value), StandardCharsets.UTF_8));
        return this;
    }

    // call

    // [{}|\\^\[\]`]
    private static final Pattern blockedURI = Pattern.compile("[{}|\\\\^\\[\\]`]");

    private static final URIEncoder encoder = new URIEncoder();

    final Response<String> call() throws IOException, InterruptedException{
        final String URL =
            baseURL +
            pathArg.matcher(path).replaceAll(result -> pathVars.get(result.group(1))) + // path args
            (queries.isEmpty() ? "" : '?' + queries.entrySet().stream().map(e -> e.getKey() + '=' + e.getValue()).collect(Collectors.joining("&"))); // query

        final HttpRequest.Builder request = HttpRequest.newBuilder();

        request.uri(URI.create(blockedURI.matcher(URL).replaceAll(encoder)));
        request.method(method, HttpRequest.BodyPublishers.noBody());
        for(final Map.Entry<String, String> entry : headers.entrySet())
            request.header(entry.getKey(), entry.getValue());

        request.header("Cache-Control", "no-cache, no-store, must-revalidate");
        request.header("Accept", "application/json; charset=UTF-8");

        if(debug){
            System.out.println("\nCall:     " + URL);
            System.out.println("Method:   " + method);
        }

        if(formUrlEncoded){
            final String data = fields.isEmpty() ? "" : fields.entrySet().stream().map(e -> e.getKey() + '=' + e.getValue()).collect(Collectors.joining("&"));
            if(debug)
                System.out.println("Data:     " + data);
            request.header("Content-Type", "application/x-www-form-urlencoded");
            request.method(method, HttpRequest.BodyPublishers.ofString(data));
        }
        final HttpResponse<String> response = HttpClient
            .newBuilder()
            .build()
            .send(request.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        final String body = response.body();

        if(debug)
            System.out.println("Response: " + body);

        return new Response<>(URL, body, body, response.statusCode());
    }

    final <T> Response<T> call(final Function<String,T> processor) throws IOException, InterruptedException{
        final Response<String> response = call();
        final String body = response.body();
        return new Response<>(response.URL(), body, processor.apply(body), response.code());
    }

    @Override
    public String toString(){
        return "APICall{" +
               "method='" + method + '\'' +
               ", baseURL='" + baseURL + '\'' +
               ", headers=" + headers +
               ", path='" + path + '\'' +
               ", pathVars=" + pathVars +
               ", queries=" + queries +
               ", formUrlEncoded=" + formUrlEncoded +
               ", fields=" + fields +
               '}';
    }

    // replace bad URI chars

    private static class URIEncoder implements Function<MatchResult,String> {

        @Override
        public final String apply(final MatchResult matchResult){
            final char ch = matchResult.group().charAt(0);
            switch(ch){
                case '{':
                    return "%7B";
                case '}':
                    return "%7D";
                case '|':
                    return "%7C";
                case '\\':
                    return "%5C";
                case '^':
                    return "%5E";
                case '[':
                    return "%5B";
                case ']':
                    return "%5D";
                case '`':
                    return "%60";
                default:
                    return matchResult.group(0);
            }
        }

    }

    // interface instantiation

    @SuppressWarnings("unchecked")
    static <C> C create(final String baseURL, final Class<C> service){
        if(!service.isInterface())
            throw new IllegalArgumentException("Service must be an interface");
        final InvocationHandler handler = new InterfaceInvocation(baseURL, service);
        return (C)
            Proxy.newProxyInstance(
                service.getClassLoader(),
                new Class<?>[]{service},
               handler
            );
    }

    private static class InterfaceInvocation implements InvocationHandler {

        private final String baseURL;
        private final Class<?> service;

        public InterfaceInvocation(final String baseURL, final Class<?> service){
            this.baseURL = baseURL;
            this.service = service;
        }

        @Override
        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable{
            if(method.getDeclaringClass() != service)
                return method.invoke(this, args);
            try{
                return new APICall(
                    baseURL,
                    method,
                    args
                ).call(Json::parse);
            }catch(final IOException e){
                throw new UncheckedIOException(e);
            }catch(final InterruptedException e){
                throw new RuntimeException(e);
            }
        }

    }

}
