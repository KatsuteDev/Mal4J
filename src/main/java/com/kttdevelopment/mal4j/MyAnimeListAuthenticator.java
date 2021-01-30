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

import com.kttdevelopment.mal4j.APIStruct.Response;
import com.sun.net.httpserver.*;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPOutputStream;

import static com.kttdevelopment.mal4j.Json.*;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/authorization">https://myanimelist.net/apiconfig/references/authorization</a> <br>
 * Authenticator used to retrieve OAuth2 tokens given a client id and client secret.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class MyAnimeListAuthenticator {

    // required fields
    private static final String authUrl =
        "https://myanimelist.net/v1/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=%s" +
        "&code_challenge=%s" +
        "&code_challenge_method=plain";

    // optional fields
    private static final String authState   = "&state=%s";
    private static final String redirectURI = "&redirect_uri=%s";

    private final MyAnimeListAuthenticationService authService = MyAnimeListAuthenticationService.create();

    private static final int DEFAULT_TIMEOUT = 60 * 3;
    private static final boolean DEFAULT_OPEN_BROWSER = false;

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private final String client_id, client_secret, authorizationCode, pkce;
    private AccessToken token;

    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final int port) throws IOException{
        this(client_id, client_secret, port, DEFAULT_OPEN_BROWSER, DEFAULT_TIMEOUT, null);
    }

    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final int port, final boolean openBrowser) throws IOException{
        this(client_id, client_secret, port, openBrowser, DEFAULT_TIMEOUT, null);
    }

    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final int port, final long timeout) throws IOException{
        this(client_id, client_secret, port, DEFAULT_OPEN_BROWSER, timeout, null);
    }

    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final int port, final String redirectURI) throws IOException{
        this(client_id, client_secret, port, DEFAULT_OPEN_BROWSER, DEFAULT_TIMEOUT, redirectURI);
    }

    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final int port, final String redirectURI, final boolean openBrowser) throws IOException{
        this(client_id, client_secret, port, openBrowser, DEFAULT_TIMEOUT, redirectURI);
    }

    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final int port, final String redirectURI, final long timeout) throws IOException{
        this(client_id, client_secret, port, DEFAULT_OPEN_BROWSER, timeout, redirectURI);
    }

    /**
     * Creates a MyAnimeListAuthenticator (easy) and deploys a server to retrieve the OAuth2 token. Local server will be active until timeout.
     *
     * @param client_id client id
     * @param client_secret client secret (optional)
     * @param port port to run the retrieval server
     * @param timeout how long in seconds that the local authentication server will live for
     * @throws BindException if selected port is already being used or is blocked
     * @throws IllegalArgumentException if client id malformed or if selected port is invalid
     * @throws IOException if client could not contact auth server
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see #MyAnimeListAuthenticator(String, String, int)
     * @see MyAnimeList#withAuthorization(MyAnimeListAuthenticator)
     * @since 1.0.0
     */
    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final int port, final boolean openBrowser, final long timeout, final String redirect_URI) throws IOException{
        final Authorization auth = authenticateWithLocalServer(client_id, port, openBrowser, timeout, redirect_URI);

        this.client_id          = client_id;
        this.client_secret      = client_secret;
        this.authorizationCode  = auth.getAuthorization();
        this.pkce               = auth.getVerifier();

        token = parseToken(authService
            .getToken(
                client_id,
                client_secret,
                "authorization_code",
                authorizationCode,
                pkce
            )
        );
    }

    /**
     * Creates a MyAnimeListAuthenticator.
     *
     * @param client_id client id
     * @param client_secret client secret (optional)
     * @param authorization_code authorization code
     * @param PKCE_code_challenge PKCE code challenge used to obtain authorization code
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see MyAnimeList#withAuthorization(MyAnimeListAuthenticator)
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final String authorization_code, final String PKCE_code_challenge){
        this.client_id          = client_id;
        this.client_secret      = client_secret;
        this.authorizationCode  = authorization_code;
        this.pkce               = PKCE_code_challenge;

        token = parseToken(authService
            .getToken(
                client_id,
                client_secret,
                "authorization_code",
                authorization_code,
                PKCE_code_challenge
            )
        );
    }

// access token

    /**
     * Returns the access token.
     *
     * @return access token
     *
     * @see AccessToken
     * @since 1.0.0
     */
    public final AccessToken getAccessToken(){
        return token;
    }

    /**
     * Refreshes the access token.
     *
     * @return updated access token
     * @throws HttpException if request failed
     * @throws UncheckedIOException if client failed to execute request
     *
     * @see AccessToken
     * @since 1.0.0
     */
    public final AccessToken refreshAccessToken(){
        return token = parseToken(authService
            .refreshToken(
                client_id,
                client_secret,
                "refresh_token",
                authorizationCode,
                pkce,
                token.getRefreshToken()
            )
        );
    }

// URL

    /**
     * Returns the authorization URL for a client id.
     *
     * @param client_id client id. <i>required</i>
     * @param PKCE_code_challenge PKCE code challenge. <i>required</i>
     * @return authorization URL
     *
     * @see #getAuthorizationURL(String, String, String)
     * @see #getAuthorizationURL(String, String, String, String)
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static String getAuthorizationURL(final String client_id, final String PKCE_code_challenge){
        return getAuthorizationURL(client_id, PKCE_code_challenge, null, null);
    }

    /**
     * Returns the authorization URL for a client id.
     *
     * @param client_id client id. <i>required</i>
     * @param PKCE_code_challenge PKCE code challenge. <i>required</i>
     * @param redirect_URI preregistered URI, only needed if you want to select a specific application redirect URI. <i>optional</i>
     * @return authorization URL
     *
     * @see #getAuthorizationURL(String, String)
     * @see #getAuthorizationURL(String, String, String, String)
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static String getAuthorizationURL(final String client_id, final String PKCE_code_challenge, final String redirect_URI){
        return getAuthorizationURL(client_id, PKCE_code_challenge, redirect_URI, null);
    }

    /**
     * Returns the authorization URL for a client id.
     *
     * @param client_id client id. <i>required</i>
     * @param PKCE_code_challenge PKCE code challenge. <i>required</i>
     * @param redirect_URI preregistered URI, only needed if you want to select a specific application redirect URI. <i>optional</i>
     * @param state 0Auth2 state. <i>optional</i>
     * @return authorization URL
     *
     * @see #getAuthorizationURL(String, String)
     * @see #getAuthorizationURL(String, String, String)
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static String getAuthorizationURL(final String client_id, final String PKCE_code_challenge, final String redirect_URI, final String state){
        return
            String.format(authUrl, URLEncoder.encode(client_id, StandardCharsets.UTF_8), PKCE_code_challenge) +
            (redirect_URI != null ? String.format(redirectURI, redirect_URI) : "") +
            (state != null ? String.format(authState, state) : "");
    }

// support methods

    /**
     * Returns if the code is allowed to open the client browser.
     *
     * @return if code can open browser
     *
     * @since 1.0.0
     */
    public static boolean canOpenBrowser(){
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    private static final PrintWriter sysOut = new PrintWriter(System.out);

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    private static Authorization authenticateWithLocalServer(final String client_id, final int port, final boolean openBrowser, final long timeout, final String redirectURI) throws IOException{
        final String verify = PKCE.generateCodeVerifier();
        final String state  = Hash.generateSha256(client_id + '&' + verify);
        final String url    = getAuthorizationURL(client_id, verify, redirectURI, state);

        // get auth call back from local server
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final CountDownLatch latch = new CountDownLatch(1);

        final HttpServer server    = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(exec);
        final AuthHandler handler = new AuthHandler(latch);
        server.createContext("/", handler);
        server.start();

        if(openBrowser)
            if(!canOpenBrowser())
                sysOut.println("Desktop is not supported on this operating system. Please go to this URL manually: " + url);
            else
                try{ Desktop.getDesktop().browse(new URI(url));
                }catch(final URISyntaxException ignored){
                    exec.shutdownNow();
                    server.stop(0);
                    throw new IllegalArgumentException("URL syntax was invalid (most likely the client id or redirect URI wasn't encoded correctly).");
                }

        try{ latch.await(timeout, TimeUnit.SECONDS);
        }catch(final InterruptedException ignored){ } // soft failure
        exec.shutdownNow();
        server.stop(0);

        if(!state.equals(handler.getState()))
            throw new UnauthorizedAccessException("Failed to authorize request (packet was intercepted by an unauthorized source).");
        if(handler.getAuth() == null)
            throw new NullPointerException("Failed to authorize request (server was closed before a response could be received).");

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

    private AccessToken parseToken(final Response<JsonObject> response){
        final JsonObject body = response.body();
        if(response.code() == HttpURLConnection.HTTP_OK)
            return new AccessToken(
                body.getString("token_type"),
                body.getLong("expires_in"),
                body.getString("access_token"),
                body.getString("refresh_token")
            );
        else
            throw new HttpException(response.URL(), response.code(), (body.getString("body") + ' ' + body.getString("error")).trim());

    }

    @SuppressWarnings("SpellCheckingInspection")
    private static final class PKCE {

        static String generateCodeVerifier(){
            final SecureRandom secureRandom = new SecureRandom();
            byte[] codeVerifier = new byte[64];
            secureRandom.nextBytes(codeVerifier);
            return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
        }

    }

    private static final class Hash{

        static String generateSha256(final String str){
            final MessageDigest digest;
            try{
                digest = MessageDigest.getInstance("SHA-256");
            }catch(final NoSuchAlgorithmException e){ // should NEVER occur
                throw new RuntimeException(e);
            }

            final byte[] encodedHash        = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString   = new StringBuilder(2 * encodedHash.length);
            for(final byte hash : encodedHash){
                final String hex = Integer.toHexString(0xff & hash);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }

    }

    private static abstract class Authorization {

        // auth code
        public abstract String getAuthorization();

        // PKCE
        @SuppressWarnings("SpellCheckingInspection")
        public abstract String getVerifier();

    }

    private static final class AuthHandler implements HttpHandler {

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
            this.latch = latch;
        }

        private transient final AtomicReference<String> auth    = new AtomicReference<>();
        private transient final AtomicReference<String> state   = new AtomicReference<>();

        private static final String OK   = "&#10004;&#65039;";
        private static final String FAIL = "&#10060;";

        @SuppressWarnings("SpellCheckingInspection")
        private static final String HTML = "<!DOCTYPE html><html><head><title>MyAnimeList Authenticator</title><style>html,body{width:100%;height:100%;-webkit-user-select: none;-ms-user-select: none;user-select: none;}body{display:flex;align-items:center;justify-content:center;background-color:#2E51A2;margin:0px;*{width:100%}}*{font-family:Helvetica,Arial,sans-serif;color:white;text-align:center}</style></head><body><div><h1>Authentication {{ state }}</h1><p title=\"{{ hint }}\">{{ message }}</p></div></body></html>";

        @Override
        public final void handle(final HttpExchange exchange) throws IOException{
            final Map<String,String> query = parseWwwFormEnc(exchange.getRequestURI().getRawQuery());
            state.set(query.get("state"));

            final String code       = query.get("code");
            final String pass       = code == null ? "Failed " + FAIL : "Completed " + OK;
            final String hint       = query.getOrDefault("hint", "");
            final String message    = code == null
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
                            .replace("{{ state }}", pass)
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

        public final String getState(){
            return state.get();
        }

    }

}
