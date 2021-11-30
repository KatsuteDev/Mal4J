package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("SpellCheckingInspection")
public class TestAuthorization {

    @Test
    public void testURLCallback(){
        final AtomicReference<String> str = new AtomicReference<>();
        try{
            new MyAnimeListAuthenticator
                .LocalServerBuilder("cid", 5050)
                .setURLCallback(str::set)
                .setTimeout(2)
                .build();
        }catch(final Throwable ignored){ }
        final String s = str.get();
        Assertions.assertTrue(s.startsWith("https://myanimelist.net/v1/oauth2/authorize?response_type=code"),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
        Assertions.assertTrue(s.contains("&client_id=cid"),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
        Assertions.assertTrue(s.contains("&code_challenge="),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
        Assertions.assertTrue(s.contains("&code_challenge_method=plain"),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
        Assertions.assertTrue(s.contains("&state="),
                              Workflow.errorSupplier("Invalid callback URL: " + s));
    }

    @Test
    public void testURL(){
        {
            final String URL = MyAnimeListAuthenticator.getAuthorizationURL("client", "challenge");
            Assertions.assertTrue(URL.contains("&client_id=client"),
                                  Workflow.errorSupplier("Expected auth URL to contain client ID"));
            Assertions.assertTrue(URL.contains("&code_challenge=challenge"),
                                  Workflow.errorSupplier("Expected auth URL to contain code challenge"));
            Assertions.assertFalse(URL.contains("&redirect_uri="),
                                   Workflow.errorSupplier("Expected auth URL to not contain redirect URI"));
            Assertions.assertFalse(URL.contains("&state="),
                                   Workflow.errorSupplier("Expected auth URL to not contain state"));
        }
        {
            final String URL = MyAnimeListAuthenticator.getAuthorizationURL("client", "challenge", "redirect");
            Assertions.assertTrue(URL.contains("&client_id=client"),
                                  Workflow.errorSupplier("Expected auth URL to contain client ID"));
            Assertions.assertTrue(URL.contains("&code_challenge=challenge"),
                                  Workflow.errorSupplier("Expected auth URL to contain code challenge"));
            Assertions.assertTrue(URL.contains("&redirect_uri=redirect"),
                                  Workflow.errorSupplier("Expected auth URL to contain redirect URI"));
            Assertions.assertFalse(URL.contains("&state="),
                                   Workflow.errorSupplier("Expected auth URL to not contain state"));
        }
        {
            final String URL = MyAnimeListAuthenticator.getAuthorizationURL("client", "challenge", "redirect", "state");
            Assertions.assertTrue(URL.contains("&client_id=client"),
                                  Workflow.errorSupplier("Expected auth URL to contain client ID"));
            Assertions.assertTrue(URL.contains("&code_challenge=challenge"),
                                  Workflow.errorSupplier("Expected auth URL to contain code challenge"));
            Assertions.assertTrue(URL.contains("&redirect_uri=redirect"),
                                  Workflow.errorSupplier("Expected auth URL to contain redirect URI"));
            Assertions.assertTrue(URL.contains("&state=state"),
                                  Workflow.errorSupplier("Expected auth URL to contain state"));
        }
    }

    @Test
    public void testNullURL(){
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeListAuthenticator.getAuthorizationURL(null, ""),
                                Workflow.errorSupplier("Expected MyAnimeListAuthenticator#getAuthorizationURL with null client ID to throw a NullPointerException"));
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeListAuthenticator.getAuthorizationURL("", null),
                                Workflow.errorSupplier("Expected MyAnimeListAUthenticator#getAuthorizationURL with null PKCE to throw a NullPointerException"));
    }

    @Test
    public void testNullBuilder(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator.LocalServerBuilder(null, 5050),
                                Workflow.errorSupplier("Expected MyAnimeListAuthenticator#LocalServerBuilder with null client ID to throw a NullPointerException"));
    }

    @Test
    public void testNull(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator(null));
    }

    @Test
    public void testPKCE(){
        Assertions.assertThrows(NullPointerException.class, () -> new Authorization("?", null, "?", null),
                                Workflow.errorSupplier("Expected MyAnimeListAuthenticator with null PKCe to throw a NullPointerException"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Authorization("?", null, "?", "42xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"),
                                Workflow.errorSupplier("Expected a PKCE of less than 43 chars to throw an IllegalArgumentException"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Authorization("?", null, "?", "129xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"),
                                Workflow.errorSupplier("Expected a PKCE of more than 128 chars to throw an IllegalArgumentException"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Authorization("?", null, "?", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx%7E"),
                                Workflow.errorSupplier("Expected a PKCE with invalid character % to throw an IllegalArgumentException"));
        Assertions.assertDoesNotThrow(() -> new Authorization("?", null, "?", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY0123456789-_.~"),
                                Workflow.errorSupplier("Expected a valid PKCE to not throw an IllegalArgumentException"));
    }

    @Test
    public void testPKCEGeneration(){
        Assertions.assertEquals(0, MyAnimeListAuthenticator.generatePKCE(0).length());
        Assertions.assertEquals(1, MyAnimeListAuthenticator.generatePKCE(1).length());
        Assertions.assertEquals(100, MyAnimeListAuthenticator.generatePKCE(100).length());
    }

    @Test
    public void testAuthorization(){
        Assertions.assertThrows(NullPointerException.class, () -> new Authorization(null, null, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Authorization("client_id", null, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Authorization("client_id", null, "client_secret", null));
        Assertions.assertDoesNotThrow(() -> new Authorization("client_id", null, "client_secret", "43xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testAccessToken(){
        Assertions.assertDoesNotThrow(() -> new AccessToken("token"));
        Assertions.assertDoesNotThrow(() -> new AccessToken("token", "refresh_token"));
        Assertions.assertDoesNotThrow(() -> new AccessToken("token", "refresh_token", -1L));
        Assertions.assertThrows(NullPointerException.class, () -> new AccessToken(null));

        Assertions.assertThrows(NullPointerException.class, () -> new AccessToken("token").getRefreshToken());
    }

    @Test
    public void testAccessTokenExpiry(){
        Assertions.assertThrows(NullPointerException.class, () -> new AccessToken("token").getExpiry());
        Assertions.assertThrows(NullPointerException.class, () -> new AccessToken("token").getTimeUntilExpires());
        Assertions.assertThrows(NullPointerException.class, () -> new AccessToken("token").isExpired());
        Assertions.assertEquals(1000L, new AccessToken("token", "refresh_token", 1000L).getTimeUntilExpires());
        Assertions.assertTrue((new AccessToken("token", "refresh_token", 1000L).getExpiry().getTime()/1000)-(System.currentTimeMillis()/1000) <= 1000);
    }

}
