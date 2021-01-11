package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TestAuthRefresh {

    @BeforeAll
    public static void beforeAll() throws IOException{
        TestProvider.testRequireLiveUser();

        final String clientId = Files.readString(TestProvider.client);
        final MyAnimeListAuthenticator authenticator = new MyAnimeListAuthenticator(clientId, null, 5050);
        final MyAnimeList mal = MyAnimeList.withAuthorization(authenticator);

        Assertions.assertNotNull(mal.getAnime().withQuery(TestProvider.AnimeQuery).search());
        mal.refreshOAuthToken();
        Assertions.assertNotNull(mal.getAnime().withQuery(TestProvider.AnimeQuery).search());

        Files.write(TestProvider.oauth, authenticator.getAccessToken().getToken().getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void runStaticTest(){ } // just force the above static test to run

}
