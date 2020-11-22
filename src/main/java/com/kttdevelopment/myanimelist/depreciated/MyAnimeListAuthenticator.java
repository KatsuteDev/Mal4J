package com.kttdevelopment.myanimelist.depreciated;

import com.kttdevelopment.myanimelist.depreciated.net.*;
import com.sun.net.httpserver.*;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.zip.GZIPOutputStream;

class MyAnimeListAuthenticator {

    private static final String authorization_link =
        "https://myanimelist.net/v1/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=%s" +
         // "&state=" +
        "&code_challenge=%s" +
        "&code_challenge_method=plain";

    private static final String oauth_link =
        "https://myanimelist.net/v1/oauth2/token" +
        "?client_id=%s" +
        "&grant_type=authorization_code" +
        "&code=%s" +
        "&code_verifier=%s";

    private transient final String client_id;
    private final int port;

    private transient String auth = null, token = null;

    private final byte[] close_html = "OK to close this window".getBytes(StandardCharsets.UTF_8);

    public MyAnimeListAuthenticator(final String client_id, final int port){
        this.client_id = client_id;
        this.port = port;
    }

    public final String authenticate() throws URISyntaxException, IOException, InterruptedException{
        final String pkce = PKCE.generateCodeVerifier();
        final String url = String.format(authorization_link, client_id, pkce);
        final String verify;
        try{
            verify = PKCE.generateCodeChallange(pkce);
        }catch(final NoSuchAlgorithmException e){
            // this should never be thrown
            throw new RuntimeException(e);
        }

        if(!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            throw new UnsupportedOperationException("Failed to authenticate (unable to open browser)");
        }else{
            final ExecutorService exec = Executors.newSingleThreadExecutor();
            final CountDownLatch latch = new CountDownLatch(1);

            final HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.setExecutor(exec);
            server.createContext("/", new ResponseHandler(latch));

            Desktop.getDesktop().browse(new URI(url));
            server.start();

            latch.await();
            exec.shutdownNow();
            server.stop(0);

            final Map<String,String> args = Map.of(
                "Content-Type", "application/x-www-form-urlencoded"
            );

            final String u = String.format(oauth_link, client_id, auth, verify);
            System.out.println(u);

            final HttpRequestClient http = new HttpRequestClient();
            final JSONObject obj = new JSONObject(http.request(u, HttpRequestMethod.POST, args).getResponse());

            System.out.println(obj);

            return obj.getString("access_token");
        }
    }

    // fixme: this class is very strict, fail safes are needed
    private class ResponseHandler implements HttpHandler {

        @SuppressWarnings("FieldCanBeLocal")
        private final Function<String, Map<String,String>> parseWwwFormEnc = s -> {
            final Map<String,String> OUT = new HashMap<>();
            final String[] pairs = s.split("&");

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
        };

        private final CountDownLatch latch;

        public ResponseHandler(final CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public final void handle(final HttpExchange exchange) throws IOException{
            final Map<String,String> query = parseWwwFormEnc.apply(exchange.getRequestURI().getRawQuery());

            auth = query.get("code");

            /* send */ {
                exchange.getResponseHeaders().set("Accept-Encoding","gzip");
                exchange.getResponseHeaders().set("Content-Encoding","gzip");
                exchange.getResponseHeaders().set("Connection","keep-alive");
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                try(GZIPOutputStream OUT = new GZIPOutputStream(exchange.getResponseBody())){
                    OUT.write(close_html);
                    OUT.finish();
                    OUT.flush();
                }
            }

            latch.countDown();
        }
    }

}
