package com.kttdevelopment.mal4j;

import java.io.*;
import java.lang.reflect.*;
import java.net.HttpURLConnection;
import java.net.URI;
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

    // initialize HTTPUrlConnection
    static {
        try{
            final Field methods = HttpURLConnection.class.getDeclaredField("methods");

            // allow variable modification
            final Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);

            modifiers.setInt(methods, methods.getModifiers() & ~Modifier.FINAL);
            methods.setAccessible(true);

            // add PATCH to methods array
            final String[] nativeMethods = (String[]) methods.get(null);
            final Set<String> newMethods = new HashSet<>(Arrays.asList(nativeMethods));
            newMethods.add("PATCH");
            methods.set(null /* static has no instance */, newMethods.toArray(new String[0]));

            // revert field to static final and close access
            modifiers.setInt(methods, methods.getModifiers() | Modifier.FINAL);
            methods.setAccessible(false);
            modifiers.setAccessible(false);
        }catch(final NoSuchFieldException | IllegalAccessException e){
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings({"RedundantThrows"})
    final APIStruct.Response<String> call() throws IOException, InterruptedException{
        final String URL =
            baseURL +
            Java9.Matcher.replaceAll(path, pathArg.matcher(path), result -> pathVars.get(result.group(1))) + // path args
            (queries.isEmpty() ? "" : '?' + queries.entrySet().stream().map(e -> e.getKey() + '=' + e.getValue()).collect(Collectors.joining("&"))); // query

        final HttpURLConnection conn = (HttpURLConnection) URI.create(Java9.Matcher.replaceAll(URL, blockedURI.matcher(URL), encoder)).toURL().openConnection();
        conn.setRequestMethod(method);

        for(final Map.Entry<String, String> entry : headers.entrySet())
            conn.setRequestProperty(entry.getKey(), entry.getValue());

        conn.setRequestProperty("Cache-Control", "no-cache, no-store, must-revalidate");
        conn.setRequestProperty("Accept", "application/json; charset=UTF-8");

        if(debug){
            System.out.println("\nCall:     " + URL);
            System.out.println("Method:   " + method);
        }

        if(formUrlEncoded){
            final String data = fields.isEmpty() ? "" : fields.entrySet().stream().map(e -> e.getKey() + '=' + e.getValue()).collect(Collectors.joining("&"));
            if(debug)
                System.out.println("Data:     " + data);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            try(final DataOutputStream OUT = new DataOutputStream(conn.getOutputStream())){
                OUT.writeBytes(data);
                OUT.flush();
            }
        }

        @SuppressWarnings("UnusedAssignment") // must be init, may be null by try catch fail
        String body = "";
        try(final BufferedReader IN = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
            String buffer;
            final StringBuilder OUT = new StringBuilder();
            while((buffer = IN.readLine()) != null)
                OUT.append(buffer);
            body = OUT.toString();
        }finally{
            conn.disconnect();
        }

        if(debug)
            System.out.println("Response: " + body);

        return new APIStruct.Response<>(URL, body, body, conn.getResponseCode());
    }

}
