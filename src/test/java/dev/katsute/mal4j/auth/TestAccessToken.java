package dev.katsute.mal4j.auth;

import dev.katsute.mal4j.AccessToken;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class TestAccessToken {

    @Nested
    final class TestConstructor {

        @Test
        final void testValidToken(){
            assertDoesNotThrow(() -> new AccessToken("token", null));
        }

        @Test
        final void testValidRefreshToken(){
            assertDoesNotThrow(() -> new AccessToken("token", "refresh_token", -1L));
        }

        @Test
        final void testNullToken(){
            assertThrows(NullPointerException.class, () -> new AccessToken(null, null, -1L));
        }

    }

    @Nested
    final class TestToken {

        @Test
        final void testToken(){
            assertEquals("Bearer token", new AccessToken("token", null, -1L).getToken());
        }

    }

    @Nested
    final class TestRefreshToken {

        @Test
        final void testRefreshToken(){
            assertEquals("refresh_token", new AccessToken("token", "refresh_token", -1L).getRefreshToken());
        }

        @Test
        final void testNullRefreshToken(){
            assertThrows(NullPointerException.class, () -> new AccessToken("token").getRefreshToken());
        }

    }

    @Nested
    final class TestExpiry {

        @Test
        final void testExpiry(){
            final long now = System.currentTimeMillis() / 1000;
            assertEquals(now, new AccessToken("token", null, now).getExpiry().getTime() / 1000);
        }

        @Test
        final void testExpiryEpoch(){
            final long now = System.currentTimeMillis() / 1000;
            assertEquals(now, new AccessToken("token", null, now).getExpiryEpochSeconds());
        }

        @Test
        final void testExpires(){
            final long now = (System.currentTimeMillis() / 1000) + 10_000;
            final long expires = new AccessToken("token", null, now).getTimeUntilExpires();
            assertTrue(expires <= 10_000 & expires >= 0);
        }

        @Test
        final void testExpired(){
            assertTrue(new AccessToken("token", null, System.currentTimeMillis() / 1000).isExpired());
        }

    }

}