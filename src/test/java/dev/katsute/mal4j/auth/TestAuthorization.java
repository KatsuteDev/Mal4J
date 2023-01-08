package dev.katsute.mal4j.auth;

import dev.katsute.mal4j.Authorization;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class TestAuthorization {

    @SuppressWarnings("SpellCheckingInspection")
    private static final String PKCE = "PKCExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    @SuppressWarnings("SpellCheckingInspection")
    @Nested
    final class TestConstructor {

        @Test
        final void testValidAuthorization(){
            assertDoesNotThrow(() -> new Authorization("client_id", "client_secret", "authorization_code", PKCE));
        }

        @Test
        final void testValidAuthorizationRedirectURI(){
            assertDoesNotThrow(() -> new Authorization("client_id", "client_secret", "authorization_code", PKCE, "redirect_uri"));
        }

        @Test
        final void testValidAuthorizationNullClientSecret(){
            assertDoesNotThrow(() -> new Authorization("client_id", null, "authorization_code", PKCE));
        }

        @Test
        final void testNullClientID(){
            assertThrows(NullPointerException.class, () -> new Authorization(null, null, null, null));
        }

        @Test
        final void testNullAuthorizationCode(){
            assertThrows(NullPointerException.class, () -> new Authorization("client_id", null, null, null));
        }

        @Test
        final void testNullPKCE(){
            assertThrows(NullPointerException.class, () -> new Authorization("client_id", null, "authorization_code", null));
        }

        @Test
        final void testShortPKCE(){
            assertThrows(IllegalArgumentException.class, () -> new Authorization("client_id", null, "authorization_code", "42xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        }

        @Test
        final void testLongPKCE(){
            assertThrows(IllegalArgumentException.class, () -> new Authorization("client_id", null, "authorization_code", "129xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        }

        @Test
        final void testInvalidPKCE(){
            assertThrows(IllegalArgumentException.class, () -> new Authorization("client_id", null, "authorization_code", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx%7E"));
        }

        @Test
        final void testValidPKCE(){
            assertDoesNotThrow(() -> new Authorization("client_id", null, "authorization_code", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY0123456789-_.~"));
        }

    }

    @Nested
    final class TestClientID {

        @Test
        final void testClientID(){
            assertEquals("token", new Authorization("token", null, "authorization_code", PKCE).getClientID());
        }

    }

    @Nested
    final class TestClientSecret {

        @Test
        final void testClientSecret(){
            assertEquals("client_secret", new Authorization("token", "client_secret", "authorization_code", PKCE).getClientSecret());
        }

        @Test
        final void testNullClientSecret(){
            assertNull(new Authorization("token", null, "authorization_code", PKCE).getClientSecret());
        }

    }

    @Nested
    final class TestAuthorizationCode {

        @Test
        final void testAuthorizationCode(){
            assertEquals("authorization_code", new Authorization("token", null, "authorization_code", PKCE).getAuthorizationCode());
        }

    }

    @Nested
    final class TestPKCE {

        @Test
        final void testPKCE(){
            assertEquals(PKCE, new Authorization("token", null, "authorization_code", PKCE).getPKCE());
        }

    }

    @Nested
    final class TestRedirectURI {

        @Test
        final void testRedirectURI(){
            assertEquals("redirect_uri", new Authorization("token", "client_secret", "authorization_code", PKCE, "redirect_uri").getRedirectURI());
        }

        @Test
        final void testNullRedirectURI(){
            assertNull(new Authorization("token", null, "authorization_code", PKCE).getRedirectURI());
        }

    }

}
