package com.kttdevelopment.mal4j.auth;

import com.kttdevelopment.mal4j.TestProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.kttdevelopment.mal4j.MyAnimeListAuthenticator.*;
import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestLocalServerRedirect {

    @BeforeAll
    public static void beforeAll(){
        TestProvider.requireHuman();
    }

    @Test
    public final void testRedirectURI() throws IOException{
        final String client_id = TestProvider.readFile(TestProvider.client);
        assertDoesNotThrow(
            () -> new LocalServerBuilder(client_id, 5050)
                .setRedirectURI("http://localhost:5050")
                .openBrowser()
                .build(),
            errorSupplier("Expected redirect URI to validate token")
        );
    }

}
