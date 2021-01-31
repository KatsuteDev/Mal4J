package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;

public class TestAuthorization {

    private static MyAnimeListAuthenticator authenticator;

    @BeforeAll // test token & refresh
    public static void beforeAll() throws IOException{
        TestProvider.testRequireClientID();

        final String clientId = Files.readString(TestProvider.client);
        authenticator = new MyAnimeListAuthenticator(clientId, null, 5050, true);
        final MyAnimeList mal = MyAnimeList.withAuthorization(authenticator);

        // test refresh token
        Assertions.assertNotNull(mal.getAnime().withQuery(TestProvider.AnimeQuery).search());
        mal.refreshOAuthToken();
        Assertions.assertNotNull(mal.getAnime().withQuery(TestProvider.AnimeQuery).search());

        // write stable OAuth
        Files.write(TestProvider.oauth, authenticator.getAccessToken().getToken().getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void testToken(){
        final AccessToken token = authenticator.getAccessToken();
        Assertions.assertTrue(token.getTimeUntilExpires() < 2_764_800);
        Assertions.assertTrue(token.getExpiry().getTime() - (System.currentTimeMillis()/1000) < 2_764_800);
        Assertions.assertFalse(token.isExpired());
    }

    @Test
    public void testIgnored(){
        Assertions.assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator("null", null, 90, 5));
    }

    @Test
    public void testTimeout(){
         Assertions.assertTimeout(Duration.ofSeconds(10), () ->
             Assertions.assertThrows(NullPointerException.class, () ->
                 new MyAnimeListAuthenticator("null", null, 90, 5))
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

}
