package com.kttdevelopment.myanimelist;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

final class APICall {

    private final String method;
    private final String baseURL;

    APICall(final String method, final String baseURL){
        this.method = method;
        this.baseURL = baseURL;
    }

    private final Map<String,String> headers = new HashMap<>();

    // \{(.*?)\}
    private static final Pattern pathArg = Pattern.compile("\\{(.*?)}");

    private String path = "";
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

    final APICall withQuery(final String query, final Object value){
        return withQuery(query, value);
    }

    final APICall withQuery(final String query, final Object value, final boolean encoded){
        if(value == null)
            queries.remove(query);
        else
            queries.put(query, encoded ? value.toString() : URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
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
            fields.put(field, encoded ? value.toString() : URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
        return this;
    }

    final Response<String> call() throws IOException{
        final String URL =
            baseURL +
            pathArg.matcher(path).replaceAll(result -> pathVars.get(result.group())) + // path args
            (queries.isEmpty() ? "" : queries.entrySet().stream().map(e -> e.getKey() + '=' +e.getValue()).collect( Collectors.joining("&"))); // query

        final HttpURLConnection conn = (HttpURLConnection) new URL(URL).openConnection();
        conn.setRequestMethod(method);
        for(final Map.Entry<String, String> entry : headers.entrySet())
            conn.setRequestProperty(entry.getKey(), entry.getValue());
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);

        if(formUrlEncoded){
            final String data = fields.isEmpty() ? "" : fields.entrySet().stream().map(e -> e.getKey() + '=' +e.getValue()).collect( Collectors.joining("&"));
            final byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(bytes.length));

            try(final DataOutputStream OUT = new DataOutputStream(conn.getOutputStream())){
                OUT.write(bytes);
            }
        }

        final int rcode = conn.getResponseCode();

        return new Response<>(
            new BufferedReader(new InputStreamReader(rcode >= 200 && rcode < 300 ? conn.getInputStream() : conn.getErrorStream()))
                .lines()
                .collect(Collectors.joining("\n")),
            rcode
        );
    }

    final <T> Response<T> call(final Function<String,T> processor) throws IOException{
        final Response<String> call = call();
        return new Response<T>(processor.apply(call.response), call.code);
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

    static final class Response<T>{

        private final T response;
        private final int code;

        Response(final T response, final int code){
            this.response = response;
            this.code = code;
        }

        final T response(){
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
