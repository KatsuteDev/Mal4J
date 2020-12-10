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

    private final CountDownLatch latch;

    @SuppressWarnings("SameParameterValue")
    AuthHandler(final CountDownLatch latch){
        this.latch          = latch;
    }

    private transient final AtomicReference<String> auth = new AtomicReference<>();

    private static final String OK = "&#10004;&#65039;";
    private static final String FAIL = "&#10060;";

    @SuppressWarnings("SpellCheckingInspection")
    private static final String HTML = "<!DOCTYPE html><html><head><title>MyAnimeList Authenticator</title><style>html,body{width:100%;height:100%;-webkit-user-select: none;-ms-user-select: none;user-select: none;}body{display:flex;align-items:center;justify-content:center;background-color:#2E51A2;margin:0px;*{width:100%}}*{font-family:Helvetica,Arial,sans-serif;color:white;text-align:center}</style></head><body><div><h1>Authentication {{ state }}</h1><p title=\"{{ hint }}\">{{ message }}</p></div></body></html>";

    @Override
    public final void handle(final HttpExchange exchange) throws IOException{
        final Map<String,String> query = parseWwwFormEnc(exchange.getRequestURI().getRawQuery());

        final String code = query.get("code");

        final String state = code == null ? "Failed " + FAIL : "Completed " + OK;
        final String hint = query.getOrDefault("hint", "");
        final String message = code == null
            ? "<b>" + query.get("error").substring(0, 1).toUpperCase() + query.get("error").substring(1).replace('_', ' ') + "</b>: " + query.get("message")
            : "You may now close the window.";

        auth.set(code);

        /* send */ {
            exchange.getResponseHeaders().set("Accept-Encoding","gzip");
            exchange.getResponseHeaders().set("Content-Encoding","gzip");
            exchange.getResponseHeaders().set("Connection","keep-alive");
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            try(GZIPOutputStream OUT = new GZIPOutputStream(exchange.getResponseBody())){
                OUT.write(
                    HTML
                        .replace("{{ state }}", state)
                        .replace("{{ hint }}", hint)
                        .replace("{{ message }}", message)
                        .getBytes(StandardCharsets.UTF_8)
                );
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
