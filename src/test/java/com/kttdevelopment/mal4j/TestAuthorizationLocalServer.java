package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TestAuthorizationLocalServer {

    private static MyAnimeListAuthenticator authenticator;

    @BeforeAll // test token & refresh
    public static void beforeAll() throws IOException{
        TestProvider.testRequireClientID();

        final String clientId = TestProvider.readFile(TestProvider.client);
        authenticator = new MyAnimeListAuthenticator.LocalServerBuilder(clientId, 5050).openBrowser().build();
        final MyAnimeList mal = MyAnimeList.withAuthorization(authenticator);

        // test refresh token
        Assertions.assertNotNull(mal.getAnime().withQuery(TestProvider.AnimeQuery).search(),
                                 Workflow.errorSupplier("Expected query to not be null"));
        mal.refreshOAuthToken();
         Assertions.assertNotNull(mal.getAnime().withQuery(TestProvider.AnimeQuery).search(),
                                  Workflow.errorSupplier("Expected query to not be null"));

        // write stable OAuth
        Files.write(TestProvider.oauth.toPath(), authenticator.getAccessToken().getToken().getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void testToken(){
        final AccessToken token = authenticator.getAccessToken();
        Assertions.assertTrue(token.getTimeUntilExpires() < 2_764_800,
                              Workflow.errorSupplier("Expected expiry to be 31 days"));
        Assertions.assertTrue(token.getExpiry().getTime() - (System.currentTimeMillis()/1000) < 2_764_800,
                              Workflow.errorSupplier("Expected expiry to be 31 days from now"));
        Assertions.assertFalse(token.isExpired(),
                               Workflow.errorSupplier("Expected token to not be expired"));
    }

    @Test
    public void testRedirectURI() throws IOException{
        final String clientId = TestProvider.readFile(TestProvider.client);
        Assertions.assertDoesNotThrow(() -> new MyAnimeListAuthenticator.LocalServerBuilder(clientId, 5050)
                                            .setRedirectURI("http://localhost:5050")
                                            .openBrowser()
                                            .build(),
                                      Workflow.errorSupplier("Expected redirect URI to validate token"));
    }

}
