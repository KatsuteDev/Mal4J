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
        final void testValidToken(){
            annotateTest(() -> assertDoesNotThrow(() -> new AccessToken("token", null)));
        }

        @Test
        final void testValidRefreshToken(){
            annotateTest(() -> assertDoesNotThrow(() -> new AccessToken("token", "refresh_token", -1L)));
        }

        @Test
        final void testNullToken(){
            annotateTest(() -> assertThrows(NullPointerException.class, () -> new AccessToken(null, null, -1L)));
        }

    }

    @Nested
    final class TestToken {

        @Test
        final void testToken(){
            annotateTest(() -> assertEquals("Bearer token", new AccessToken("token", null, -1L).getToken()));
        }

    }

    @Nested
    final class TestRefreshToken {

        @Test
        final void testRefreshToken(){
            annotateTest(() -> assertEquals("refresh_token", new AccessToken("token", "refresh_token", -1L).getRefreshToken()));
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Test
        final void testNullRefreshToken(){
            annotateTest(() -> assertThrows(NullPointerException.class, () -> new AccessToken("token").getRefreshToken()));
        }

    }

    @Nested
    final class TestExpiry {

        @Test
        final void testExpiry(){
            final long now = System.currentTimeMillis() / 1000;
            annotateTest(() -> assertEquals(now, new AccessToken("token", null, now).getExpiry().getTime() / 1000));
        }

        @Test
        final void testExpiryEpoch(){
            final long now = System.currentTimeMillis() / 1000;
            annotateTest(() -> assertEquals(now, new AccessToken("token", null, now).getExpiryEpochSeconds()));
        }

        @Test
        final void testExpires(){
            final long now = (System.currentTimeMillis() / 1000) + 10_000;
            final long expires = new AccessToken("token", null, now).getTimeUntilExpires();
            annotateTest(() -> assertTrue(expires <= 10_000 & expires >= 0));
        }

        @Test
        final void testExpired(){
            annotateTest(() -> assertTrue(new AccessToken("token", null, System.currentTimeMillis() / 1000).isExpired()));
        }

    }

}
