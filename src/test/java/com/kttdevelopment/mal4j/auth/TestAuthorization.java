package com.kttdevelopment.mal4j.auth;

import com.kttdevelopment.mal4j.Authorization;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestAuthorization {

    @SuppressWarnings("SpellCheckingInspection")
    private static final String PKCE = "PKCExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    @SuppressWarnings("SpellCheckingInspection")
    @Nested
    final class TestConstructor {

        @Test
        final void testValidAuthorization(){
            annotateTest(() -> assertDoesNotThrow(() -> new Authorization("client_id", "client_secret", "authorization_code", PKCE)));
        }

        @Test
        final void testValidAuthorizationRedirectURI(){
            annotateTest(() -> assertDoesNotThrow(() -> new Authorization("client_id", "client_secret", "authorization_code", PKCE, "redirect_uri")));
        }

        @Test
        final void testValidAuthorizationNullClientSecret(){
            annotateTest(() -> assertDoesNotThrow(() -> new Authorization("client_id", null, "authorization_code", PKCE)));
        }

        @Test
        final void testNullClientID(){
            annotateTest(() -> assertThrows(NullPointerException.class, () -> new Authorization(null, null, null, null)));
        }

        @Test
        final void testNullAuthorizationCode(){
            annotateTest(() -> assertThrows(NullPointerException.class, () -> new Authorization("client_id", null, null, null)));
        }

        @Test
        final void testNullPKCE(){
            annotateTest(() -> assertThrows(NullPointerException.class, () -> new Authorization("client_id", null, "authorization_code", null)));
        }

        @Test
        final void testShortPKCE(){
            annotateTest(() -> assertThrows(IllegalArgumentException.class, () -> new Authorization("client_id", null, "authorization_code", "42xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")));
        }

        @Test
        final void testLongPKCE(){
            annotateTest(() -> assertThrows(IllegalArgumentException.class, () -> new Authorization("client_id", null, "authorization_code", "129xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")));
        }

        @Test
        final void testInvalidPKCE(){
            annotateTest(() -> assertThrows(IllegalArgumentException.class, () -> new Authorization("client_id", null, "authorization_code", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx%7E")));
        }

        @Test
        final void testValidPKCE(){
            annotateTest(() -> assertDoesNotThrow(() -> new Authorization("client_id", null, "authorization_code", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY0123456789-_.~")));
        }

    }

    @Nested
    final class TestClientID {

        @Test
        final void testClientID(){
            annotateTest(() -> assertEquals("token", new Authorization("token", null, "authorization_code", PKCE).getClientID()));
        }

    }

    @Nested
    final class TestClientSecret {

        @Test
        final void testClientSecret(){
            annotateTest(() -> assertEquals("client_secret", new Authorization("token", "client_secret", "authorization_code", PKCE).getClientSecret()));
        }

        @Test
        final void testNullClientSecret(){
            annotateTest(() -> assertNull(new Authorization("token", null, "authorization_code", PKCE).getClientSecret()));
        }

    }

    @Nested
    final class TestAuthorizationCode {

        @Test
        final void testAuthorizationCode(){
            annotateTest(() -> assertEquals("authorization_code", new Authorization("token", null, "authorization_code", PKCE).getAuthorizationCode()));
        }

    }

    @Nested
    final class TestPKCE {

        @Test
        final void testPKCE(){
            annotateTest(() -> assertEquals(PKCE, new Authorization("token", null, "authorization_code", PKCE).getPKCE()));
        }

    }

    @Nested
    final class TestRedirectURI {

        @Test
        final void testRedirectURI(){
            annotateTest(() -> assertEquals("redirect_uri", new Authorization("token", "client_secret", "authorization_code", PKCE, "redirect_uri").getRedirectURI()));
        }

        @Test
        final void testNullRedirectURI(){
            annotateTest(() -> assertNull(new Authorization("token", null, "authorization_code", PKCE).getRedirectURI()));
        }

    }

}
