package dev.katsute.mal4j.auth;

import dev.katsute.mal4j.MyAnimeListAuthenticator.LocalServerBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

final class TestLocalServer {

    @Nested
    final class TestConstructor {

        @Test
        final void testValidBuilder(){
            assertDoesNotThrow(() -> new LocalServerBuilder("client_id", null, 80));
        }

        @Test
        final void testNullClientID(){
            assertThrows(NullPointerException.class, () -> new LocalServerBuilder(null, null, 80));
        }

    }

    @Nested
    final class TestTimeout {

        @Test
        final void testTimeout(){
            assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
                try{
                    new LocalServerBuilder("client_id", 80)
                        .setTimeout(1)
                        .build();
                }catch(final Throwable ignored){ }
            });
        }

    }

    @Nested
    final class TestURLCallback {

        @Test
        final void testURLCallback() throws InterruptedException{
            final AtomicReference<String> callback = new AtomicReference<>();
            try{
                new LocalServerBuilder("client_id", 80)
                    .setURLCallback(callback::set)
                    .setTimeout(1)
                    .build();
            }catch(final Throwable ignored){ }

            Thread.sleep(2000);

            final String url = callback.get();

            assertTrue(url.startsWith("https://myanimelist.net/v1/oauth2/authorize?response_type=code"));
            assertTrue(url.contains("&client_id=client_id"));
            assertTrue(url.contains("&code_challenge="));
            assertTrue(url.contains("&code_challenge_method=plain"));
            assertTrue(url.contains("&state="));
        }

    }

}
