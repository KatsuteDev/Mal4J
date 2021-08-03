package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestMyAnimeList {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = MyAnimeList.withOAuthToken("Bearer null");
    }

    @Test
    public void testNullToken(){
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeList.withOAuthToken(null),
                                Workflow.errorSupplier("Expected MyAnimeList#withOAuthToken with null token to throw a NullPointerException"));
    }

    @Test
    public void testNoBearerToken(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> MyAnimeList.withOAuthToken("x"),
                                Workflow.errorSupplier("Expected MyAnimeList#withOAuthToken with invalid token to throw an IllegalArgumentException"));
    }

    @Test
    public void testNullAuthenticator(){
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeList.withAuthorization(null),
                                Workflow.errorSupplier("Expected MyAnimeList#withAuthorizaton with null authenticator to throw a NullPointerException"));
    }

    @Test
    public void testNullAnimeRanking(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getAnimeRanking(null),
                                Workflow.errorSupplier("Expected MyAnimeList#getAnimeRanking of null type to throw a NullPointerException"));
    }

    @Test
    public void testNullAnimeSeason(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getAnimeSeason(2020, null),
                                Workflow.errorSupplier("Expected MyAnimeList#getAnimeSeason of null season to throw a NullPointerException"));
    }

    @Test
    public void testNullUserAnimeList(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getUserAnimeListing(null),
                                Workflow.errorSupplier("Expected MyAnimeList#getUserAnimeListing of null user to throw a NullPointerException"));
    }

    @Test
    public void testNullMangaRanking(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getMangaRanking(null),
                                Workflow.errorSupplier("Expected MyAnimeList#getMangaRanking of null type to throw a NullPointerException"));
    }

    @Test
    public void testNullUserMangaList(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getUserMangaListing(null),
                                Workflow.errorSupplier("Expected MyAnimeList#getUserMangaListing of null user to throw a NullPointerException"));
    }

    @Test
    public void testNullUser(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getUser(null),
                                Workflow.errorSupplier("Expected MyAnimeList#getUser of null user to throw a NullPointerException"));
    }

    private static final String inverted = "^%s$|^%s(?=,)|(?<=\\w)\\{%s}|(?:^|,)%s\\{.*?}|,%s|(?<=\\{)%s,";

    @ParameterizedTest
    @ValueSource(strings={"%s", "%s,%s", "a,%s", "a{%s}", "%s{a}", "a{%s}", "a{a,%s}", "a{%s,a}"})
    public void testInvertedRegex(final String raw){
        final String sf = "[%s]: '%s' should not have contained '%s' after inversion";
        final String inv = raw.replaceAll(inverted, "");
        Assertions.assertFalse(inv.contains("%s"),
                               Workflow.errorSupplier(String.format(sf, raw, inv, "%s")));
        Assertions.assertFalse(inv.contains("{}"),
                               Workflow.errorSupplier(String.format(sf, raw, inv, "{}")));
        Assertions.assertFalse(inv.contains("{,"),
                               Workflow.errorSupplier(String.format(sf, raw, inv, "{,")));
        Assertions.assertFalse(inv.contains(",}"),
                               Workflow.errorSupplier(String.format(sf, raw, inv, ",}")));
        Assertions.assertFalse(inv.startsWith(","),
                               Workflow.errorSupplier(String.format(sf, raw, inv, ",$")));
        Assertions.assertFalse(inv.endsWith(","),
                               Workflow.errorSupplier(String.format(sf, raw, inv, "^,")));
    }

}
