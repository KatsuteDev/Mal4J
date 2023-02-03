/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
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

package dev.katsute.mal4j;

import com.sun.net.httpserver.*;
import dev.katsute.mal4j.APIStruct.Response;
import dev.katsute.mal4j.exception.*;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.zip.GZIPOutputStream;

import static dev.katsute.mal4j.Json.*;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/authorization">https://myanimelist.net/apiconfig/references/authorization</a> <br>
 * The authenticator is used to retrieve OAuth2 tokens given a client id and client secret.
 * <br><br>
 * MyAnimeList can be authenticated by with either:
 * <ul>
 *     <li>An authorization code using {@link MyAnimeListAuthenticator(String, String, String, String)}.</li>
 *     <li>A local server using {@link LocalServerBuilder}.</li>
 * </ul>
 *
 * @since 1.0.0
 * @version 2.11.0
 * @author Katsute
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

    private final Authorization authorization;
    private AccessToken token;

    @SuppressWarnings("SpellCheckingInspection")
    private MyAnimeListAuthenticator(
        final String client_id,
        final String client_secret,
        final int port,
        final AuthResponseHandler handler,
        final Consumer<String> urlCallback,
        final boolean openBrowser,
        final long timeout,
        final String redirect_URI
    ) throws IOException{
        // [authorization, PKCE verify]
        final String[] auth = authenticateWithLocalServer(
            client_id,
            port,
            handler,
            urlCallback,
            openBrowser,
            timeout,
            redirect_URI
        );

        this.authorization = new Authorization(client_id, client_secret, auth[0], auth[1], redirect_URI);

        token = parseToken(authService
            .getToken(
                client_id,
                client_secret,
                "authorization_code",
                auth[0],
                auth[1],
                redirect_URI
            )
        );
    }

    /**
     * Creates an authenticator.
     *
     * @param authorization authorization
     * @throws InvalidTokenException if token is expired or invalid
     *
     * @see Authorization
     * @see #MyAnimeListAuthenticator(Authorization)
     * @since 2.7.0
     */
    public MyAnimeListAuthenticator(final Authorization authorization){
        this(authorization, null);
    }

    /**
     * Creates an authenticator.
     *
     * @param authorization authorization
     * @param token token (optional, use an existing access token)
     * @throws InvalidTokenException if token is expired or invalid
     *
     * @see Authorization
     * @see AccessToken
     * @see #MyAnimeListAuthenticator(Authorization)
     * @since 2.7.0
     */
    public MyAnimeListAuthenticator(final Authorization authorization, final AccessToken token){
        Objects.requireNonNull(authorization, "Authorization must not be null");
        this.authorization = authorization;

        if(token != null){
            Logging.addMask(token::getToken);
            Logging.addMask(token::getRefreshToken);
        }

        this.token = token != null
            ? token
            : parseToken(authService
                .getToken(
                    authorization.getClientID(),
                    authorization.getClientSecret(),
                    "authorization_code",
                    authorization.getAuthorizationCode(),
                    authorization.getPKCE(),
                    authorization.getRedirectURI()
                )
            );
    }

// authorization

    public final Authorization getAuthorization(){
        return this.authorization;
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
     * Refreshes the access token. This does not refresh the token inside the MyAnimeList object, you must use {@link MyAnimeList#refreshToken()} instead.
     *
     * @return updated access token
     *
     * @see AccessToken
     * @since 1.0.0
     */
    public final AccessToken refreshAccessToken(){
        token = parseToken(authService
            .refreshToken(
                authorization.getClientID(),
                authorization.getClientSecret(),
                "refresh_token",
                authorization.getAuthorizationCode(),
                authorization.getPKCE(),
                token.getRefreshToken()
            )
        );

        Logging.addMask(token::getToken);
        Logging.addMask(token::getRefreshToken);

        return token;
    }

// URL

    /**
     * Returns the authorization URL for a client id.
     *
     * @param client_id client id <b>(required)</b>
     * @param PKCE_code_challenge PKCE code challenge <b>(required)</b>
     * @return authorization URL
     * @throws NullPointerException if client ID or PKCE is null
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
     * @param client_id client id <b>(required)</b>
     * @param PKCE_code_challenge PKCE code challenge <b>(required)</b>
     * @param redirect_URI preregistered URI, only needed if you want to select a specific application redirect URI <b>(optional)</b>
     * @return authorization URL
     * @throws NullPointerException if client ID or PKCE is null
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
     * @param client_id client id <b>(required)</b>
     * @param PKCE_code_challenge PKCE code challenge <b>(required)</b>
     * @param redirect_URI preregistered URI, only needed if you want to select a specific application redirect URI <b>(optional)</b>
     * @param state 0Auth2 state <b>(optional)</b>
     * @return authorization URL
     * @throws NullPointerException if client ID or PKCE is null
     *
     * @see #getAuthorizationURL(String, String)
     * @see #getAuthorizationURL(String, String, String)
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static String getAuthorizationURL(final String client_id, final String PKCE_code_challenge, final String redirect_URI, final String state){
        Objects.requireNonNull(client_id, "Client ID must not be null");
        Objects.requireNonNull(PKCE_code_challenge, "PKCE must not be null");
        return
            String.format(authUrl, client_id, PKCE_code_challenge) +
            (redirect_URI != null ? String.format(redirectURI, APICall.encodeUTF8(redirect_URI)) : "") +
            (state != null ? String.format(authState, state) : "");
    }

// builder methods

    /**
     * Creates a MyAnimeList Authenticator by deploying a local server to authenticate the user.
     *
     * @see MyAnimeListAuthenticator
     * @since 1.1.0
     * @version 2.4.0
     * @author Katsute
     */
    public static final class LocalServerBuilder {

        private final String client_id, client_secret;
        private final int port;

        private boolean openBrowser = false;
        private long timeout = 60 * 3;
        private String redirect_URI = null;

        private AuthResponseHandler responseHandler = null;
        private Consumer<String> urlCallback = null;

        /**
         * Instantiates a local server builder with a client id and port.
         *
         * @param client_id client id
         * @param port port
         *
         * @see #LocalServerBuilder(String, String, int)
         * @since 1.1.0
         */
        public LocalServerBuilder(final String client_id, final int port){
            this(client_id, null, port);
        }

        /**
         * Instantiates a local server builder with a client id, client secret, and port.
         *
         * @param client_id client id
         * @param client_secret client secret
         * @param port port
         *
         * @see #LocalServerBuilder(String, int)
         * @since 1.1.0
         */
        public LocalServerBuilder(final String client_id, final String client_secret, final int port){
            Objects.requireNonNull(client_id, "Client ID must not be null");

            this.client_id     = client_id;
            this.client_secret = client_secret;
            this.port          = port;
        }

        /**
         * Indicates that the authorization page should be opened in the user's browser automatically.
         *
         * @return builder
         *
         * @see #openBrowser(boolean)
         * @since 1.1.0
         */
        public final LocalServerBuilder openBrowser(){
            return openBrowser(true);
        }

        /**
         * If true, a browser will automatically be opened to the authorization page. Note that if this is false you will have to go to the page manually using {@link #getAuthorizationURL(String, String)}, {@link #getAuthorizationURL(String, String, String)}, or {@link #getAuthorizationURL(String, String, String, String)}.
         *
         * @param openBrowser if browser should be opened
         * @return builder
         *
         * @see #openBrowser()
         * @since 1.1.0
         */
        public final LocalServerBuilder openBrowser(final boolean openBrowser){
            if(!canOpenBrowser())
                Logging.getLogger().warning("System does not support openBrowser()");
            this.openBrowser = openBrowser;
            return this;
        }

        /**
         * Sets how long (in seconds) that the server will expire in. Once the timeout has passed a new authentication builder must be used.
         *
         * @param timeout server timeout
         * @return builder
         *
         * @since 1.1.0
         */
        public final LocalServerBuilder setTimeout(final int timeout){
            this.timeout = timeout;
            return this;
        }

        /**
         * Sets the application redirect URI. Only required if your application has more than one redirect URI registered.
         *
         * @param redirectURI redirect URI
         * @return builder
         *
         * @since 1.1.0
         */
        public final LocalServerBuilder setRedirectURI(final String redirectURI){
            this.redirect_URI = redirectURI;
            return this;
        }

        /**
         * Sets the response handler.
         *
         * @param responseHandler response handler
         * @return builder
         *
         * @see AuthResponseHandler
         * @since 1.1.0
         */
        public final LocalServerBuilder setResponseHandler(final AuthResponseHandler responseHandler){
            this.responseHandler = responseHandler;
            return this;
        }

        /**
         * Sets the auth URL callback method. Used to retrieve the URL that generates the authorization code. Runs in its own thread.
         * <br>
         * This method can be used to handle the auth URL in the case that the system doesn't support the {@link LocalServerBuilder#openBrowser()} method.
         *
         * @param consumer consumer
         * @return builder
         *
         * @see Consumer
         * @since 2.0.0
         */
        public final LocalServerBuilder setURLCallback(final Consumer<String> consumer){
            this.urlCallback = consumer;
            return this;
        }

        /**
         * Returns the built authenticator.
         *
         * @return authenticator
         * @throws IllegalArgumentException if port was invalid, or client id or redirect URI was malformed
         * @throws BindException if port was blocked
         * @throws NullPointerException if server was closed before the client could be authorized
         *
         * @see MyAnimeListAuthenticator
         * @since 1.1.0
         */
        public final MyAnimeListAuthenticator build() throws IOException{
            return new MyAnimeListAuthenticator(
                client_id,
                client_secret,
                port,
                responseHandler,
                urlCallback,
                openBrowser,
                timeout,
                redirect_URI
            );
        }

        @Override
        public final String toString(){
            return "MyAnimeListAuthenticator.LocalServerBuilder{" +
                   "port=" + port +
                   ", openBrowser=" + openBrowser +
                   ", timeout=" + timeout +
                   ", redirect_URI='" + redirect_URI + '\'' +
                   ", responseHandler=" + responseHandler +
                   '}';
        }

    }

// local server

    /**
     * Returns if Java has permission to open the client browser.
     *
     * @return if Java can open the browser
     *
     * @since 1.0.0
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean canOpenBrowser(){
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "SpellCheckingInspection"})
    private static String[/* [authorization, PKCE verify] */] authenticateWithLocalServer(
        final String client_id,
        final int port,
        final AuthResponseHandler responseHandler,
        final Consumer<String> urlCallback,
        final boolean openBrowser,
        final long timeout,
        final String redirectURI
    ) throws IOException{
        Objects.requireNonNull(client_id, "Client ID must not be null");

        final String verify = generatePKCE(128);
        final String state  = generateSHA256(client_id + '&' + verify);
        final String url    = getAuthorizationURL(client_id, verify, redirectURI, state);

        if(urlCallback != null)
            new Thread(() -> urlCallback.accept(url)).start();

        // get auth call back from local server
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final CountDownLatch latch = new CountDownLatch(1);

        final HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(exec);
        final AuthHandler handler = new AuthHandler(latch, responseHandler);
        server.createContext("/", handler);
        server.start();

        if(openBrowser)
            if(!canOpenBrowser())
                Logging.getLogger().severe("Desktop is not supported on this operating system. Please go to this URL manually: '" + url + "' or use a URL callback");
            else
                try{ Desktop.getDesktop().browse(new URI(url));
                }catch(final URISyntaxException ignored){
                    exec.shutdownNow();
                    server.stop(0);
                    throw new IllegalArgumentException("URL syntax was invalid (most likely the client id or redirect URI wasn't encoded correctly)");
                }

        try{ latch.await(timeout, TimeUnit.SECONDS);
        }catch(final InterruptedException ignored){ } // soft failure
        exec.shutdownNow();
        server.stop(0);

        if(handler.getAuth() == null)
            throw new NullPointerException("Failed to authorize request (server was closed before a response could be received)");
        if(!state.equals(handler.getState()))
            throw new UnauthorizedAccessException("Failed to authorize request (packet was intercepted by an unauthorized source)");

        return new String[]{handler.getAuth(), verify};
    }

    @SuppressWarnings("DataFlowIssue")
    private AccessToken parseToken(final Response<JsonObject> response){
        final JsonObject body = response.body();
        if(response.code() == HttpURLConnection.HTTP_OK)
            return new AccessToken(
                body.getString("token_type"),
                body.getString("access_token"),
                body.getString("refresh_token"),
                // now in seconds + how long until expires in seconds
                (System.currentTimeMillis() / 1000) + body.getLong("expires_in")
            );
        else if(response.code() == HttpURLConnection.HTTP_UNAUTHORIZED)
            throw new InvalidTokenException("The OAuth token provided is either invalid or expired");
        else{
            final String error = body.getString("error");
            throw new HttpException(response.URL(), response.code(), error != null ? error.trim() : response.raw());
        }
    }

    private static final class AuthHandler implements HttpHandler {

        private Map<String,String> parseWwwFormEnc(final String query){
            final Map<String,String> OUT = new HashMap<>();
            final String[] pairs = query.split("&");

            for(final String pair : pairs){
                if(pair.contains("=")){
                    final String[] kv = pair.split("=");
                    OUT.put(
                        APICall.decodeUTF8(kv[0]),
                        kv.length == 2 ? APICall.decodeUTF8(kv[1]) : null
                    );
                }
            }
            return OUT;
        }

        private final CountDownLatch latch;
        private final AuthResponseHandler handler;

        private AuthHandler(final CountDownLatch latch){
            this(latch, defaultHandler);
        }

        private AuthHandler(final CountDownLatch latch, final AuthResponseHandler handler){
            this.latch = latch;
            this.handler = handler == null ? defaultHandler : handler;
        }

        private transient final AtomicReference<String> auth    = new AtomicReference<>();
        private transient final AtomicReference<String> state   = new AtomicReference<>();

        @Override
        public final void handle(final HttpExchange exchange) throws IOException{
            final Map<String,String> query = parseWwwFormEnc(exchange.getRequestURI().getRawQuery());
            state.set(query.get("state"));
            auth.set(query.get("code"));

            /* send */ {
                exchange.getResponseHeaders().set("Accept-Encoding", "gzip");
                exchange.getResponseHeaders().set("Content-Encoding", "gzip");
                exchange.getResponseHeaders().set("Connection", "keep-alive");
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                try(GZIPOutputStream OUT = new GZIPOutputStream(exchange.getResponseBody())){
                    OUT.write(
                        handler.getResponse(
                            query.get("code"),
                            query.get("error"),
                            query.get("message"),
                            query.get("hint")
                        ).getBytes(StandardCharsets.UTF_8)
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

    private static final AuthResponseHandler defaultHandler = new AuthResponseHandler(){

        @SuppressWarnings("SpellCheckingInspection")
        private static final String HTML = "<!DOCTYPE html><html><head><title>MyAnimeList Authenticator</title><style>html,body{width:100%;height:100%;-webkit-user-select: none;-ms-user-select: none;user-select: none;}body{display:flex;align-items:center;justify-content:center;background-color:#2E51A2;margin:0px;*{width:100%}}*{font-family:Helvetica,Arial,sans-serif;color:white;text-align:center}</style></head><body><div><h1>Authentication {{ state }}</h1><p title=\"{{ hint }}\">{{ message }}</p></div></body></html>";

        private static final String OK   = "&#10004;&#65039;";
        private static final String FAIL = "&#10060;";

        @Override
        public final String getResponse(final String code, final String error, final String message, final String hint){
            final String pass = code == null ? "Failed " + FAIL : "Completed " + OK;
            final String err = error == null ? "" : error;
            final String msg = code == null
                ? "<b>" + err.substring(0, 1).toUpperCase() + err.substring(1).replace('_', ' ') + "</b>: " + (message == null ? "" : message)
                : "You may now close the window.";

            return HTML
                .replace("{{ state }}", pass)
                .replace("{{ hint }}", hint != null ? hint : "")
                .replace("{{ message }}", msg);
        }

    };

// generator methods

    public static String generatePKCE(final int length){
        final SecureRandom random = new SecureRandom();
        byte[] codeVerifier = new byte[length];
        random.nextBytes(codeVerifier);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier).substring(0, length);
    }

    private static String generateSHA256(final String str){
        final MessageDigest digest;
        try{
            digest = MessageDigest.getInstance("SHA-256");
        }catch(final NoSuchAlgorithmException e){ // should NEVER occur
            throw new RuntimeException("An exception that should not have been thrown has been thrown, please report this to the maintainers of Mal4J", e);
        }

        final byte[] encodedHash        = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        final StringBuilder hexString   = new StringBuilder(2 * encodedHash.length);
        for(final byte hash : encodedHash){
            final String hex = Integer.toHexString(0xff & hash);
            hexString.append(hex.length() == 1 ? '0' : hex);
        }
        return hexString.toString();
    }

//

    @Override
    public final String toString(){
        return "MyAnimeListAuthenticator{" +
               "authorization=" + authorization +
               ", token=" + token +
               '}';
    }

}
