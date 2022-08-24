package dev.katsute.mal4j.auth;

import dev.katsute.mal4j.*;
import org.junit.jupiter.api.*;

import static dev.katsute.mal4j.MyAnimeListAuthenticator.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestMyAnimeListAuthenticator {

    @Nested
    final class TestConstructor {

        @SuppressWarnings("SpellCheckingInspection")
        @Test
        final void testValidAuthenticator(){
            assertDoesNotThrow(() -> new MyAnimeListAuthenticator(new Authorization("client_id", "client_secret", "authorization_code", "PKCExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"), new AccessToken("token")));
        }

        @Test
        final void testNullAuthorization(){
            assertThrows(NullPointerException.class, () -> new MyAnimeListAuthenticator(null));
        }

    }

    @Nested
    final class TestAuthorization {

        @SuppressWarnings("SpellCheckingInspection")
        @Test
        final void testAuthorization(){
            final Authorization authorization = new Authorization("client_id", "client_secret", "authorization_code", "PKCExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            assertSame(authorization, new MyAnimeListAuthenticator(authorization, new AccessToken("token")).getAuthorization());
        }

    }

    @Nested
    final class TestAccessToken {

        @SuppressWarnings("SpellCheckingInspection")
        @Test
        final void testAccessToken(){
            final Authorization authorization = new Authorization("client_id", "client_secret", "authorization_code", "PKCExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            final AccessToken token = new AccessToken("token");

            assertSame(token, new MyAnimeListAuthenticator(authorization, token).getAccessToken());
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    @Nested
    final class TestAuthorizationURL {

        @Nested
        final class TestBare {

            final String URL = getAuthorizationURL("client", "PKCE");

            @Test
            final void testClientID(){
                assertTrue(URL.contains("&client_id=client"));
            }

            @Test
            final void testCodeChallenge(){
                assertTrue(URL.contains("&code_challenge=PKCE"));
            }

            @Test
            final void testRedirectURI(){
                assertFalse(URL.contains("&redirect_uri="));
            }

            @Test
            final void testState(){
                assertFalse(URL.contains("&state="));
            }

        }

        @Nested
        final class TestRedirect {

            final String URL = getAuthorizationURL("client", "PKCE", "redirect");

            @Test
            final void testClientID(){
                assertTrue(URL.contains("&client_id=client"));
            }

            @Test
            final void testCodeChallenge(){
                assertTrue(URL.contains("&code_challenge=PKCE"));
            }

            @Test
            final void testRedirectURI(){
                assertTrue(URL.contains("&redirect_uri=redirect"));
            }

            @Test
            final void testState(){
                assertFalse(URL.contains("&state="));
            }

        }

        @Nested
        final class TestState {

            final String URL = getAuthorizationURL("client", "PKCE", null, "state");

            @Test
            final void testClientID(){
                assertTrue(URL.contains("&client_id=client"));
            }

            @Test
            final void testCodeChallenge(){
                assertTrue(URL.contains("&code_challenge=PKCE"));
            }

            @Test
            final void testRedirectURI(){
                assertFalse(URL.contains("&redirect_uri="));
            }

            @Test
            final void testState(){
                assertTrue(URL.contains("&state=state"));
            }

        }

        @Nested
        final class TestAll {

            final String URL = getAuthorizationURL("client", "PKCE", "redirect", "state");

            @Test
            final void testClientID(){
                assertTrue(URL.contains("&client_id=client"));
            }

            @Test
            final void testCodeChallenge(){
                assertTrue(URL.contains("&code_challenge=PKCE"));
            }

            @Test
            final void testRedirectURI(){
                assertTrue(URL.contains("&redirect_uri=redirect"));
            }

            @Test
            final void testState(){
                assertTrue(URL.contains("&state=state"));
            }

        }

        @Test
        final void testNullClient(){
            assertThrows(NullPointerException.class, () -> getAuthorizationURL(null, "PKCE"));
        }

        @Test
        final void testNullPKCE(){
            assertThrows(NullPointerException.class, () -> getAuthorizationURL("client", null));
        }

    }

    @Nested
    final class TestPKCE {

        @Test
        final void test0Length(){
            assertEquals(0, generatePKCE(0).length());
        }

        @Test
        final void test1Length(){
            assertEquals(1, generatePKCE(1).length());
        }

        @Test
        final void test100Length(){
            assertEquals(1, generatePKCE(1).length());
        }

        @RepeatedTest(10)
        final void testValidPKCE(){
            assertDoesNotThrow(() -> new Authorization("client_id", null, "authorization_code", generatePKCE(128)));
        }

    }

}
