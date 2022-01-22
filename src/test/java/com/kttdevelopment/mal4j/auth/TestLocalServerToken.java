package com.kttdevelopment.mal4j.auth;

import com.kttdevelopment.mal4j.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

import static com.kttdevelopment.mal4j.MyAnimeListAuthenticator.*;
import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

public final class TestLocalServerToken {

    private static MyAnimeListAuthenticator authenticator;

    @BeforeAll
    public static void beforeAll() throws IOException{
        TestProvider.requireHuman();

        final String client_id = TestProvider.readFile(TestProvider.client);
        authenticator = new LocalServerBuilder(client_id, 5050)
            .openBrowser()
            .build();

        final MyAnimeList mal = MyAnimeList.withOAuth2(authenticator);

        // test refresh token
        {
            annotateTest(() -> assertDoesNotThrow(() -> Objects.requireNonNull(mal.getAnime(TestProvider.AnimeID, Fields.NO_FIELDS))));

            mal.refreshToken();

            annotateTest(() -> assertDoesNotThrow(() -> Objects.requireNonNull(mal.getAnime(TestProvider.AnimeID, Fields.NO_FIELDS))));
        }

        // write token
        Files.write(TestProvider.token.toPath(), authenticator.getAccessToken().getToken().getBytes(StandardCharsets.UTF_8));
    }

    @Nested
    final class TestToken {

        private AccessToken token;

        @BeforeEach
        final void beforeEach(){
            this.token = authenticator.getAccessToken();
        }

        @Test
        final void testExpiryTime(){
            final long expiry = token.getTimeUntilExpires();
            annotateTest(() -> assertTrue(expiry < 2_764_800));
        }

        @Test
        final void testExpired(){
            annotateTest(() -> assertFalse(token.isExpired()));
        }

    }

}
