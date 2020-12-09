package com.kttdevelopment.myanimelist.auth;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPOutputStream;

final class AuthHandler implements HttpHandler {

    private Map<String,String> parseWwwFormEnc(final String query){
        final Map<String,String> OUT = new HashMap<>();
        final String[] pairs = query.split("&");

        for(final String pair : pairs){
            if(pair.contains("=")){
                final String[] kv = pair.split("=");
                OUT.put(
                        URLDecoder.decode(kv[0], StandardCharsets.UTF_8),
                    kv.length == 2 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : null
                );
            }
        }
        return OUT;
    }

    private final byte[] success_html, failure_html;
    private final CountDownLatch latch;

    @SuppressWarnings("SameParameterValue")
    AuthHandler(final CountDownLatch latch, final String success_html, final String failure_html){
        this.latch          = latch;
        this.success_html   = success_html.getBytes(StandardCharsets.UTF_8);
        this.failure_html   = failure_html.getBytes(StandardCharsets.UTF_8);
    }

    private transient final AtomicReference<String> auth = new AtomicReference<>();

    @Override
    public final void handle(final HttpExchange exchange) throws IOException{
        final Map<String,String> query = parseWwwFormEnc(exchange.getRequestURI().getRawQuery());

        auth.set(query.get("code"));

        /* send */ {
            exchange.getResponseHeaders().set("Accept-Encoding","gzip");
            exchange.getResponseHeaders().set("Content-Encoding","gzip");
            exchange.getResponseHeaders().set("Connection","keep-alive");
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            try(GZIPOutputStream OUT = new GZIPOutputStream(exchange.getResponseBody())){
                OUT.write(query.containsKey("code") ? success_html : failure_html);
                OUT.finish();
                OUT.flush();
            }
        }

        latch.countDown();
    }

    public final String getAuth(){
        return auth.get();
    }

}
