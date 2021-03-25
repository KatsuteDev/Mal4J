package com.kttdevelopment.mal4j;

import java.io.*;
import java.lang.reflect.*;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

final class HttpBridge extends APICall {

    HttpBridge(final String method, final String baseURL, final String path){
        super(method, baseURL, path);
    }

    HttpBridge(final String baseURL, final Method method, final Object... args){
        super(baseURL, method, args);
    }

    final APIStruct.Response<String> call() throws IOException, InterruptedException{
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

        return new APIStruct.Response<>(URL, body, body, response.statusCode());
    }

}
