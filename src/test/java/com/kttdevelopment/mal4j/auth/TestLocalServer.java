package com.kttdevelopment.mal4j.auth;

import com.kttdevelopment.mal4j.MyAnimeListAuthenticator.LocalServerBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestLocalServer {

    @Nested
    final class TestConstructor {

        @Test
        public final void testValidBuilder(){
            assertDoesNotThrow(
                () -> new LocalServerBuilder("client_id", null, 80),
                errorSupplier("Expected valid local server builder to not throw an exception")
            );
        }

        @Test
        public final void testNullClientID(){
            assertThrows(
                NullPointerException.class,
                () -> new LocalServerBuilder(null, null, 80),
                errorSupplier("Expected null client id to throw an exception")
            );
        }

    }

    @Nested
    final class TestTimeout {

        @Test
        public final void testTimeout(){
            assertTimeoutPreemptively(Duration.ofSeconds(2),
                () -> {
                    try{
                        new LocalServerBuilder("client_id", 80)
                            .setTimeout(1)
                            .build();
                    }catch(final Throwable ignored){ }
                },
                errorSupplier("Expected timeout to work")
            );
        }

    }

    @Nested
    final class TestURLCallback {

        @Test
        public final void testURLCallback(){
            final AtomicReference<String> callback = new AtomicReference<>();
            try{
                new LocalServerBuilder("client_id", 80)
                    .setURLCallback(callback::set)
                    .setTimeout(1)
                    .build();
            }catch(final Throwable ignored){ }

            final String url = callback.get();

            assertTrue(
                url.startsWith("https://myanimelist.net/v1/oauth2/authorize?response_type=code"),
                errorSupplier("Expected callback url to match url")
            );
            assertTrue(
                url.contains("&client_id=client_id"),
                errorSupplier("Expected client id to match url")
            );
            assertTrue(
                url.contains("&code_challenge="),
                errorSupplier("Expected code challenge to match url")
            );
            assertTrue(
                url.contains("&code_challenge_method=plain"),
                errorSupplier("Expected code challenge method to match url")
            );
            assertTrue(
                url.contains("&state="),
                errorSupplier("Expected state to match url")
            );
        }

    }

}
