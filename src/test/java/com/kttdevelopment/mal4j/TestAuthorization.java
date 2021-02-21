package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@SuppressWarnings("SpellCheckingInspection")
public class TestAuthorization {

    @Test
    public void testIgnored(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator.LocalServerBuilder(null, 5050).setTimeout(5).build());
    }

    @Test
    public void testTimeout(){
         Assertions.assertTimeout(Duration.ofSeconds(10), () ->
             Assertions.assertThrows(NullPointerException.class, () ->
                 new MyAnimeListAuthenticator.LocalServerBuilder(null, 5050).setTimeout(5).build())
         );
    }

    @Test
    public void testURL(){
        {
            final String URL = MyAnimeListAuthenticator.getAuthorizationURL("client", "challenge");
            Assertions.assertTrue(URL.contains("&client_id=client"));
            Assertions.assertTrue(URL.contains("&code_challenge=challenge"));
            Assertions.assertFalse(URL.contains("&redirect_uri="));
            Assertions.assertFalse(URL.contains("&state="));
        }
        {
            final String URL = MyAnimeListAuthenticator.getAuthorizationURL("client", "challenge", "redirect");
            Assertions.assertTrue(URL.contains("&client_id=client"));
            Assertions.assertTrue(URL.contains("&code_challenge=challenge"));
            Assertions.assertTrue(URL.contains("&redirect_uri=redirect"));
            Assertions.assertFalse(URL.contains("&state="));
        }
        {
            final String URL = MyAnimeListAuthenticator.getAuthorizationURL("client", "challenge", "redirect", "state");
            Assertions.assertTrue(URL.contains("&client_id=client"));
            Assertions.assertTrue(URL.contains("&code_challenge=challenge"));
            Assertions.assertTrue(URL.contains("&redirect_uri=redirect"));
            Assertions.assertTrue(URL.contains("&state=state"));
        }
    }

    @Test
    public void testNullURL(){
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeListAuthenticator.getAuthorizationURL(null, ""));
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeListAuthenticator.getAuthorizationURL("", null));
    }

    @Test
    public void testNullBuilder(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator.LocalServerBuilder(null, 5050));
    }

    @Test
    public void testNull(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator(null, null, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator("?", null, null, null));
    }

    @Test
    public void testPKCE(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator("?", null, "?", null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MyAnimeListAuthenticator("?", null, "?", "42xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MyAnimeListAuthenticator("?", null, "?", "129xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
    }

}
