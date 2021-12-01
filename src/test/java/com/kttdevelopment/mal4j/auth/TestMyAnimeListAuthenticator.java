package com.kttdevelopment.mal4j.auth;

import com.kttdevelopment.mal4j.Authorization;
import org.junit.jupiter.api.*;

import static com.kttdevelopment.mal4j.MyAnimeListAuthenticator.*;
import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestMyAnimeListAuthenticator {

    @Nested
    final class TestConstructor {

    }

    @Nested
    final class TestAuthorization {

    }

    @Nested
    final class TestAccessToken {

    }

    @SuppressWarnings("SpellCheckingInspection")
    @Nested
    final class TestAuthorizationURL {

        @Nested
        final class TestBare {

            final String URL = getAuthorizationURL("client", "PKCE");

             @Test
            public void testClientID(){
                assertTrue(
                    URL.contains("&client_id=client"),
                    errorSupplier("Expected authorization URL to contain client id")
                );
            }

            @Test
            public void testCodeChallenge(){
                assertTrue(
                    URL.contains("&code_challenge=PKCE"),
                    errorSupplier("Expected authorization URL to contain code challenge")
                );
            }

            @Test
            public void testRedirectURI(){
                assertFalse(
                    URL.contains("&redirect_uri="),
                    errorSupplier("Expected authorization URL to not contain redirect uri")
                );
            }

            @Test
            public void testState(){
                assertFalse(
                    URL.contains("&state="),
                    errorSupplier("Expected authorization URL to not contain state")
                );
            }

        }

        @Nested
        final class TestRedirect {

            final String URL = getAuthorizationURL("client", "PKCE", "redirect");

             @Test
            public void testClientID(){
                assertTrue(
                    URL.contains("&client_id=client"),
                    errorSupplier("Expected authorization URL to contain client id")
                );
            }

            @Test
            public void testCodeChallenge(){
                assertTrue(
                    URL.contains("&code_challenge=PKCE"),
                    errorSupplier("Expected authorization URL to contain code challenge")
                );
            }

            @Test
            public void testRedirectURI(){
                assertTrue(
                    URL.contains("&redirect_uri=redirect"),
                    errorSupplier("Expected authorization URL to contain redirect uri")
                );
            }

            @Test
            public void testState(){
                assertFalse(
                    URL.contains("&state="),
                    errorSupplier("Expected authorization URL to not contain state")
                );
            }

        }

        @Nested
        final class TestState {

            final String URL = getAuthorizationURL("client", "PKCE", null, "state");

             @Test
            public void testClientID(){
                assertTrue(
                    URL.contains("&client_id=client"),
                    errorSupplier("Expected authorization URL to contain client id")
                );
            }

            @Test
            public void testCodeChallenge(){
                assertTrue(
                    URL.contains("&code_challenge=PKCE"),
                    errorSupplier("Expected authorization URL to contain code challenge")
                );
            }

            @Test
            public void testRedirectURI(){
                assertFalse(
                    URL.contains("&redirect_uri="),
                    errorSupplier("Expected authorization URL to not contain redirect uri")
                );
            }

            @Test
            public void testState(){
                assertTrue(
                    URL.contains("&state=state"),
                    errorSupplier("Expected authorization URL to contain state")
                );
            }

        }

        @Nested
        final class TestAll {

            final String URL = getAuthorizationURL("client", "PKCE", "redirect", "state");

             @Test
            public void testClientID(){
                assertTrue(
                    URL.contains("&client_id=client"),
                    errorSupplier("Expected authorization URL to contain client id")
                );
            }

            @Test
            public void testCodeChallenge(){
                assertTrue(
                    URL.contains("&code_challenge=PKCE"),
                    errorSupplier("Expected authorization URL to contain code challenge")
                );
            }

            @Test
            public void testRedirectURI(){
                assertTrue(
                    URL.contains("&redirect_uri=redirect"),
                    errorSupplier("Expected authorization URL to contain redirect uri")
                );
            }

            @Test
            public void testState(){
                assertTrue(
                    URL.contains("&state=state"),
                    errorSupplier("Expected authorization URL to contain state")
                );
            }

        }

        @Test
        public final void testNullClient(){
            assertThrows(
                NullPointerException.class,
                () -> getAuthorizationURL(null, "PKCE"),
                errorSupplier("Expected null client id to throw an exception")
            );
        }

        @Test
        public final void testNullPKCE(){
            assertThrows(
                NullPointerException.class,
                () -> getAuthorizationURL("client", null),
                errorSupplier("Expected null PKCE to throw an exception")
            );
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    @Nested
    final class TestPKCE {

        @Test
        public void test0Length(){
            assertEquals(
                0, generatePKCE(0).length(),
                errorSupplier("Expected PKCE length to match")
            );
        }

        @Test
        public void test1Length(){
            assertEquals(
                1, generatePKCE(1).length(),
                errorSupplier("Expected PKCE length to match")
            );
        }

        @Test
        public void test100Length(){
            assertEquals(
                1, generatePKCE(1).length(),
                errorSupplier("Expected PKCE length to match")
            );
        }

        @RepeatedTest(10)
        public void testValidPKCE(){
            assertDoesNotThrow(
                () -> new Authorization("client_id", null, "authorization_code", generatePKCE(128)),
                errorSupplier("Expected randomly generated PKCE to be valid")
            );
        }

    }

}
