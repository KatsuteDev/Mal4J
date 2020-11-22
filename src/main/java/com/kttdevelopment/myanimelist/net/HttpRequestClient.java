package com.kttdevelopment.myanimelist.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpRequestClient {

    public final Response request(final String url) {
        return request(url, HttpRequestMethod.GET, Collections.emptyMap());
    }

    public final Response request(final String url, final String request_method, final Map<String,String> args) {
        try{
            final HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(request_method);
        args.forEach(connection::setRequestProperty);
        try(final InputStream IN = connection.getInputStream()){
            try(final Scanner scanner = new Scanner(IN, StandardCharsets.UTF_8)){
                return new Response(connection.getResponseCode(), scanner.useDelimiter("\\A").next());
            }
        }
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static class Response{

        private final int code;
        private final String response;

        Response(final int code, final String response){
            this.code = code;
            this.response = response;
        }

        public final int getCode(){
            return code;
        }

        public final String getResponse(){
            return response;
        }

        @Override
        public String toString(){
            return "Response{" +
                   "code=" + code +
                   ", response='" + response + '\'' +
                   '}';
        }

    }

}
