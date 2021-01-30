package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TestAuthRefresh {

    private static MyAnimeListAuthenticator authenticator;

    @BeforeAll
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
        // todo: test server ignored
    }

    @Test
    public void testTimeout(){
        // todo: test timeout (server should expire before, disregard exception)
    }

    @Test
    public void testURL(){
        // todo: test #getAuthorizationURL
    }

}
