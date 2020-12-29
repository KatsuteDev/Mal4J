package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.APIStruct.Response;
import com.sun.net.httpserver.*;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPOutputStream;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/authorization">https://myanimelist.net/apiconfig/references/authorization</a> <br>
 * Authenticator used to retrieve OAuth2 tokens given a client id and client secret.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public final class MyAnimeListAuthenticator {

    private static final String authUrl =
        "https://myanimelist.net/v1/oauth2/authorize" +
        "?response_type=code" +
        "&client_id=%s" +
        "&state=RequestID4" +
        "&code_challenge=%s" +
        "&code_challenge_method=plain";

    private final MyAnimeListAuthenticationService authService = MyAnimeListAuthenticationService.create();

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private final String client_id, client_secret, authorizationCode, pkce;
    private AccessToken token;

    /**
     * Creates a MyAnimeListAuthenticator (easy) and deploys a server to retrieve the OAuth2 token.
     *
     * @param client_id client id
     * @param client_secret client secret (optional)
     * @param port port to run the retrieval server
     * @throws IOException if client could not contact auth server
     *
     * @see MyAnimeList#withAuthorization(MyAnimeListAuthenticator)
     * @since 1.0.0
     */
    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final int port) throws IOException{
        final Authorization auth = authenticateWithLocalServer(client_id, Math.min(Math.max(0, port), 65535));

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
     * @param PKCE_code_challenge PKCE code challenge
     * @throws IOException if client could not contact auth server
     *
     * @see MyAnimeList#withAuthorization(MyAnimeListAuthenticator)
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public MyAnimeListAuthenticator(final String client_id, final String client_secret, final String authorization_code, final String PKCE_code_challenge) throws IOException{
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
     * @throws IOException if client could not contact the auth server
     *
     * @see AccessToken
     * @since 1.0.0
     */
    public final AccessToken refreshAccessToken() throws IOException {
        return token = parseToken( authService
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

    /**
     * Returns the authorization URL for a client id.
     *
     * @param client_id client id
     * @param PKCE_code_challenge PKCE code challenge
     * @return authorization URL
     *
     * @since 1.0.0
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static String getAuthorizationURL(final String client_id, final String PKCE_code_challenge){
        return String.format(authUrl, URLEncoder.encode(client_id, StandardCharsets.UTF_8), PKCE_code_challenge);
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    private static Authorization authenticateWithLocalServer(final String client_id, final int port) throws IOException{
        final String verify = PKCE.generateCodeVerifier();
        final String url = getAuthorizationURL(client_id, verify);

        if(!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
            throw new UnsupportedOperationException("Failed to authorize client (unable to open browser link.");

        // get auth call back from local server
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final CountDownLatch latch = new CountDownLatch(1);

        final HttpServer server    = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(exec);
        final AuthHandler handler = new AuthHandler(latch);
        server.createContext("/", handler);
        server.start();

        try{ Desktop.getDesktop().browse(new URI(url));
        }catch(final URISyntaxException ignored){ } // URL is guaranteed to be valid

        try{
            latch.await(1, TimeUnit.MINUTES);
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

    private AccessToken parseToken(final Response<Map<String,?>> response){
        final Map<String,?> body = response.body();
        // todo: handle response codes
        return new AccessToken(
            (String) body.get("token_type"),
            Integer.valueOf((int) body.get("expires_in")).longValue(),
            (String) body.get("access_token"),
            (String) body.get("refresh_token")
        );
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

        private transient final AtomicReference<String> auth = new AtomicReference<>();

        private static final String OK   = "&#10004;&#65039;";
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

}
