package com.kttdevelopment.mal4j.auth;

import com.kttdevelopment.mal4j.AccessToken;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestAccessToken {

    @Nested
    final class TestConstructor {

        @Test
        public final void testValidToken(){
            assertDoesNotThrow(
                () -> new AccessToken("token", null),
                errorSupplier("Expected valid token to not throw an exception")
            );
        }

        @Test
        public final void testValidRefreshToken(){
            assertDoesNotThrow(
                () -> new AccessToken("token", "refresh_token", -1L),
                errorSupplier("Expected valid refresh token to not throw an exception")
            );
        }

        @Test
        public final void testNullToken(){
            assertThrows(
                NullPointerException.class, () -> new AccessToken(null, null, -1L),
                errorSupplier("Expected null token to throw an exception")
            );
        }

    }

    @Nested
    final class TestToken {

        @Test
        public final void testToken(){
            assertEquals(
                "Bearer token", new AccessToken("token", null, -1L).getToken(),
                errorSupplier("Expected token to be the same")
            );
        }

    }

    @Nested
    final class TestRefreshToken {

        @Test
        public final void testRefreshToken(){
            assertEquals(
                "refresh_token", new AccessToken("token", "refresh_token", -1L).getRefreshToken(),
                errorSupplier("Expected refresh token to be the same")
            );
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Test
        public final void testNullRefreshToken(){
            assertThrows(
                NullPointerException.class,
                () -> new AccessToken("token").getRefreshToken(),
                errorSupplier("Expected null refresh token to throw an exception")
            );
        }

    }

    @Nested
    final class TestExpiry {

        @Test
        public final void testExpiry(){
            final long now = System.currentTimeMillis() / 1000;
            assertEquals(
                now, new AccessToken("token", null, now).getExpiry().getTime() / 1000,
                 errorSupplier("Expected expiry time to be the same")
            );
        }

        @Test
        public final void testExpiryEpoch(){
            final long now = System.currentTimeMillis() / 1000;
            assertEquals(
                now, new AccessToken("token", null, now).getExpiryEpochSeconds(),
                 errorSupplier("Expected expiry time to be the same")
            );
        }

        @Test
        public final void testExpires(){
            final long now = (System.currentTimeMillis() / 1000) + 10_000;
            final long expires = new AccessToken("token", null, now).getTimeUntilExpires();
            assertTrue(
                expires <= 10_000 & expires >= 0,
                errorSupplier("Expected time until expires to be between now and expiry, was " + expires)
            );
        }

        @Test
        public final void testExpired(){
            assertTrue(
                new AccessToken("token", null, System.currentTimeMillis() / 1000).isExpired(),
                errorSupplier("Expected token to be expired")
            );
        }

    }

}
