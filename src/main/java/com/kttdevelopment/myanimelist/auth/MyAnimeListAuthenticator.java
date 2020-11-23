package com.kttdevelopment.myanimelist.auth;

import com.kttdevelopment.myanimelist.MyAnimeListAuthenticationService;
import com.sun.net.httpserver.HttpServer;
import retrofit2.*;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class MyAnimeListAuthenticator {

    private static final String authUrl =
        "https://myanimelist.net/v1/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=%s" +
        "&state=RequestID4" +
        "&code_challenge=%s" +
        "&code_challenge_method=plain";

    private final MyAnimeListAuthenticationService authService = MyAnimeListAuthenticationService.create();

    private transient AccessToken token;

    public MyAnimeListAuthenticator(final String client_id, final int port) throws IOException{
        final Authorization auth = getAuthorization(client_id, Math.min(Math.max(0, port), 65535));

        token = authService
            .getToken(
                client_id,
                "authorization_code",
                auth.getAuthorization(),
                auth.getVerifier())
            .execute()
            .body();

        authService
            .getToken(
                client_id,
                "authorization_code",
                auth.getAuthorization(),
                auth.getVerifier())
            .enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(final Call<AccessToken> call, final Response<AccessToken> response){
                    System.out.println(response);
                }

                @Override
                public void onFailure(final Call<AccessToken> call, final Throwable t){
                    System.out.println(t);
                }
            });

    }

    public final AccessToken getToken(){
        return token;
    }

    private static Authorization getAuthorization(final String client_id, final int port) throws IOException{
        final String verify = PKCE.generateCodeVerifier();
        final String url = String.format(authUrl, URLEncoder.encode(client_id, StandardCharsets.UTF_8), verify);

        if(!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
            throw new UnsupportedOperationException("Failed to authorize client (unable to open browser link.");

        // get auth from local server
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final CountDownLatch latch = new CountDownLatch(1);

        final HttpServer server    = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(exec);
        final AuthHandler handler = new AuthHandler(
            latch,
            "<!DOCTYPE html><html><head><style>html,body{width:100%;height:100%}body{display:flex;align-items:center;justify-content:center;background-color:#2E51A2;margin:0px;*{width:100%}}*{font-family:Helvetica,Arial,sans-serif;color:white;text-align:center}</style></head><body><div><h1>Authentication Completed</h1><p>You may now close this window.</p></div></body></html>",
            "<!DOCTYPE html><html><head><style>html,body{width:100%;height:100%}body{display:flex;align-items:center;justify-content:center;background-color:#2E51A2;margin:0px;*{width:100%}}*{font-family:Helvetica,Arial,sans-serif;color:white;text-align:center}</style></head><body><div><h1>Authentication Failed</h1><p>You may now close this window.</p></div></body></html>");
        server.createContext("/", handler);
        server.start();

        try{
            Desktop.getDesktop().browse(new URI(url));
        }catch(final URISyntaxException e){
            // should only occur if failed to open browser
            throw new RuntimeException(e);
        }

        try{
            latch.await();
        }catch(final InterruptedException ignored){ } // soft failure
        exec.shutdownNow();
        server.stop(0);

        return new Authorization(){

            @Override
            public final String getAuthorization(){
                return handler.getAuth();
            }

            @Override
            public final String getVerifier(){
                return verify;
            }

        };
    }

}
