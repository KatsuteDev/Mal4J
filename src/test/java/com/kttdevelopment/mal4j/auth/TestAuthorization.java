package com.kttdevelopment.mal4j.auth;

import com.kttdevelopment.mal4j.Authorization;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestAuthorization {

    @SuppressWarnings("SpellCheckingInspection")
    private static final String PKCE = "43xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    @SuppressWarnings("SpellCheckingInspection")
    @Nested
    final class TestConstructor {

        @Test
        public void testValidAuthorization(){
            assertDoesNotThrow(
                () -> new Authorization("client_id", "client_secret", "authorization_code", PKCE),
                errorSupplier("Expected valid authorization to not throw an exception")
            );
        }

        @Test
        public void testValidAuthorizationRedirectURI(){
            assertDoesNotThrow(
                () -> new Authorization("client_id", "client_secret", "authorization_code", PKCE, "redirect_uri"),
                errorSupplier("Expected valid authorization to not throw an exception")
            );
        }

        @Test
        public void testValidAuthorizationNullClientSecret(){
            assertDoesNotThrow(
                () -> new Authorization("client_id", null, "authorization_code", PKCE),
                errorSupplier("Expected valid authorization to not throw an exception")
            );
        }

        @Test
        public void testNullClientID(){
            assertThrows(
                NullPointerException.class,
                () -> new Authorization(null, null, null, null),
                errorSupplier("Expected null client id to throw an exception")
            );
        }

        @Test
        public void testNullAuthorizationCode(){
            assertThrows(
                NullPointerException.class,
                () -> new Authorization("client_id", null, null, null),
                errorSupplier("Expected null authorization code to throw an exception")
            );
        }

        @Test
        public void testNullPKCE(){
            assertThrows(
                NullPointerException.class,
                () -> new Authorization("client_id", null, "authorization_code", null),
                errorSupplier("Expected null authorization code to throw an exception")
            );
        }

        @Test
        public void testShortPKCE(){
            assertThrows(
                IllegalArgumentException.class,
                () -> new Authorization("client_id", null, "authorization_code", "42xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"),
                errorSupplier("Expected bad PKCE to throw an exception")
            );
        }

        @Test
        public void testLongPKCE(){
            assertThrows(
                IllegalArgumentException.class,
                () -> new Authorization("client_id", null, "authorization_code", "129xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"),
                errorSupplier("Expected bad PKCE to throw an exception")
            );
        }

        @Test
        public void testInvalidPKCE(){
            assertThrows(
                IllegalArgumentException.class,
                () -> new Authorization("client_id", null, "authorization_code", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx%7E"),
                errorSupplier("Expected bad PKCE to throw an exception")
            );
        }

        @Test
        public void testValidPKCE(){
            assertDoesNotThrow(
                () -> new Authorization("client_id", null, "authorization_code", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY0123456789-_.~"),
                errorSupplier("Expected valid PKCE to not throw an exception")
            );
        }

    }

    @Nested
    final class TestClientID {

        @Test
        public void testClientID(){
            assertEquals(
                "token", new Authorization("token", null, "authorization_code", PKCE).getClientID(),
                errorSupplier("Expected client id to match")
            );
        }

    }

    @Nested
    final class TestClientSecret {

        @Test
        public void testClientSecret(){
            assertEquals(
                "client_secret", new Authorization("token", "client_secret", "authorization_code", PKCE).getClientSecret(),
                errorSupplier("Expected client secret to match")
            );
        }

        @Test
        public void testNullClientSecret(){
            assertNull(
                new Authorization("token", null, "authorization_code", PKCE).getClientSecret(),
                errorSupplier("Expected client secret to be null")
            );
        }

    }

    @Nested
    final class TestAuthorizationCode {

        @Test
        public void testAuthorizationCode(){
            assertEquals(
                "authorization_code", new Authorization("token", null, "authorization_code", PKCE).getAuthorizationCode(),
                errorSupplier("Expected authorization code to match")
            );
        }

    }

    @Nested
    final class TestPKCE {

        @SuppressWarnings("SpellCheckingInspection")
        @Test
        public void testPKCE(){
            assertEquals(
                PKCE, new Authorization("token", null, "authorization_code", PKCE).getPKCE(),
                errorSupplier("Expected PKCE to match")
            );
        }

    }

    @Nested
    final class TestRedirectURI {

        @Test
        public void testRedirectURI(){
            assertEquals(
                "redirect_uri", new Authorization("token", "client_secret", "authorization_code", PKCE, "redirect_uri").getRedirectURI(),
                errorSupplier("Expected redirect uri to match")
            );
        }

        @Test
        public void testNullRedirectURI(){
            assertNull(
                new Authorization("token", null, "authorization_code", PKCE).getRedirectURI(),
                errorSupplier("Expected redirect uri to be null")
            );
        }

    }

}
