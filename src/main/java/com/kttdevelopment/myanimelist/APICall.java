package com.kttdevelopment.myanimelist;

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

import static com.kttdevelopment.myanimelist.APIStruct.*;

final class APICall {

    private final String method;
    private final String baseURL;
    private final String path;

    APICall(final String method, final String baseURL, final String path){
        this.method     = method;
        this.baseURL    = baseURL;
        this.path       = path;
    }

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

    private final Map<String,String> headers = new HashMap<>();

    // \{(.*?)\}
    private static final Pattern pathArg = Pattern.compile("\\{(.*?)}");

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

    // [{}|\\^\[\]`]
    private static Pattern blockedURI = Pattern.compile("[{}|\\\\^\\[\\]`]");

    private static URIEncoder encoder = new URIEncoder();

    final Response<String> call() throws IOException, InterruptedException{
        final String URL =
                baseURL +
                pathArg.matcher(path).replaceAll(result -> pathVars.get(result.group(1))) + // path args
                (queries.isEmpty() ? "" : '?' + queries.entrySet().stream().map(e -> e.getKey() + '=' +e.getValue()).collect( Collectors.joining("&"))); // query

        final HttpRequest.Builder request = HttpRequest.newBuilder();

        request.uri(URI.create(blockedURI.matcher(URL).replaceAll(encoder)));
        request.method(method, HttpRequest.BodyPublishers.noBody());
        for(final Map.Entry<String, String> entry : headers.entrySet())
            request.header(entry.getKey(), entry.getValue());

        request.header("Cache-Control", "no-cache");

        if(formUrlEncoded){
            final String data = fields.isEmpty() ? "" : fields.entrySet().stream().map(e -> e.getKey() + '=' +e.getValue()).collect( Collectors.joining("&"));
            request.header("Content-Type", "application/x-www-form-urlencoded");
            request.method(method, HttpRequest.BodyPublishers.ofString(data));
        }
        final HttpResponse<String> response = HttpClient
            .newBuilder()
            .build()
            .send(request.build(), HttpResponse.BodyHandlers.ofString());

        return new Response<>(response.body(), response.statusCode());
    }

    final <T> Response<T> call(final Function<String,T> processor) throws IOException, InterruptedException, URISyntaxException{
        final Response<String> call = call();
        return new Response<>(processor.apply(call.body()), call.code());
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

    //

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
