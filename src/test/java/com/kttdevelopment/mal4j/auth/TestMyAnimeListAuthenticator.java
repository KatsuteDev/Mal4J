package com.kttdevelopment.mal4j.auth;

import com.kttdevelopment.mal4j.*;
import org.junit.jupiter.api.*;

import static com.kttdevelopment.mal4j.MyAnimeListAuthenticator.*;
import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestMyAnimeListAuthenticator {

    @Nested
    final class TestConstructor {

        @SuppressWarnings("SpellCheckingInspection")
        @Test
        public final void testValidAuthenticator(){
            assertDoesNotThrow(
                () -> new MyAnimeListAuthenticator(new Authorization("client_id", "client_secret", "authorization_code", "PKCExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"), new AccessToken("token")),
                errorSupplier("Expected valid authentication to not throw an exception")
            );
        }

        @Test
        public final void testNullAuthorization(){
            assertThrows(
                NullPointerException.class,
                () -> new MyAnimeListAuthenticator(null),
                errorSupplier("Expected null authorization to throw an exception")
            );
        }

    }

    @Nested
    final class TestAuthorization {

        @SuppressWarnings("SpellCheckingInspection")
        @Test
        public final void testAuthorization(){
            final Authorization authorization = new Authorization("client_id", "client_secret", "authorization_code", "PKCExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            assertSame(
                authorization, new MyAnimeListAuthenticator(authorization, new AccessToken("token")).getAuthorization(),
                errorSupplier("Expected authorization to match")
            );
        }

    }

    @Nested
    final class TestAccessToken {

        @SuppressWarnings("SpellCheckingInspection")
        @Test
        public final void testAccessToken(){
            final Authorization authorization = new Authorization("client_id", "client_secret", "authorization_code", "PKCExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            final AccessToken token = new AccessToken("token");

            assertSame(
                token, new MyAnimeListAuthenticator(authorization, token).getAccessToken(),
                errorSupplier("Expected access token to match")
            );
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    @Nested
    final class TestAuthorizationURL {

        @Nested
        final class TestBare {

            final String URL = getAuthorizationURL("client", "PKCE");

            @Test
            public final void testClientID(){
                assertTrue(
                    URL.contains("&client_id=client"),
                    errorSupplier("Expected authorization URL to contain client id")
                );
            }

            @Test
            public final void testCodeChallenge(){
                assertTrue(
                    URL.contains("&code_challenge=PKCE"),
                    errorSupplier("Expected authorization URL to contain code challenge")
                );
            }

            @Test
            public final void testRedirectURI(){
                assertFalse(
                    URL.contains("&redirect_uri="),
                    errorSupplier("Expected authorization URL to not contain redirect uri")
                );
            }

            @Test
            public final void testState(){
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
            public final void testClientID(){
                assertTrue(
                    URL.contains("&client_id=client"),
                    errorSupplier("Expected authorization URL to contain client id")
                );
            }

            @Test
            public final void testCodeChallenge(){
                assertTrue(
                    URL.contains("&code_challenge=PKCE"),
                    errorSupplier("Expected authorization URL to contain code challenge")
                );
            }

            @Test
            public final void testRedirectURI(){
                assertTrue(
                    URL.contains("&redirect_uri=redirect"),
                    errorSupplier("Expected authorization URL to contain redirect uri")
                );
            }

            @Test
            public final void testState(){
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
            public final void testClientID(){
                assertTrue(
                    URL.contains("&client_id=client"),
                    errorSupplier("Expected authorization URL to contain client id")
                );
            }

            @Test
            public final void testCodeChallenge(){
                assertTrue(
                    URL.contains("&code_challenge=PKCE"),
                    errorSupplier("Expected authorization URL to contain code challenge")
                );
            }

            @Test
            public final void testRedirectURI(){
                assertFalse(
                    URL.contains("&redirect_uri="),
                    errorSupplier("Expected authorization URL to not contain redirect uri")
                );
            }

            @Test
            public final void testState(){
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
            public final void testClientID(){
                assertTrue(
                    URL.contains("&client_id=client"),
                    errorSupplier("Expected authorization URL to contain client id")
                );
            }

            @Test
            public final void testCodeChallenge(){
                assertTrue(
                    URL.contains("&code_challenge=PKCE"),
                    errorSupplier("Expected authorization URL to contain code challenge")
                );
            }

            @Test
            public final void testRedirectURI(){
                assertTrue(
                    URL.contains("&redirect_uri=redirect"),
                    errorSupplier("Expected authorization URL to contain redirect uri")
                );
            }

            @Test
            public final void testState(){
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
        public final void test0Length(){
            assertEquals(
                0, generatePKCE(0).length(),
                errorSupplier("Expected PKCE length to match")
            );
        }

        @Test
        public final void test1Length(){
            assertEquals(
                1, generatePKCE(1).length(),
                errorSupplier("Expected PKCE length to match")
            );
        }

        @Test
        public final void test100Length(){
            assertEquals(
                1, generatePKCE(1).length(),
                errorSupplier("Expected PKCE length to match")
            );
        }

        @RepeatedTest(10)
        public final void testValidPKCE(){
            final String PKCE = generatePKCE(128);
            assertDoesNotThrow(
                () -> new Authorization("client_id", null, "authorization_code", PKCE),
                errorSupplier(String.format("Expected randomly generated PKCE to be valid '%s'", PKCE))
            );
        }

    }

}
