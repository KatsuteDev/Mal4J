package dev.katsute.mal4j.auth;

import dev.katsute.mal4j.TestProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static dev.katsute.mal4j.MyAnimeListAuthenticator.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestLocalServerRedirect {

    @BeforeAll
    static void beforeAll(){
        TestProvider.requireHuman();
    }

    @Test
    final void testRedirectURI() throws IOException{
        final String client_id = TestProvider.readFile(TestProvider.client);
        assertDoesNotThrow(() ->
            new LocalServerBuilder(client_id, 5050)
                .setRedirectURI("http://localhost:5050")
                .openBrowser()
                .build()
        );
    }

}