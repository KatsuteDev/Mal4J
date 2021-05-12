package com.kttdevelopment.mal4j;

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
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeList.withOAuthToken(null));
    }

    @Test
    public void testNoBearerToken(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> MyAnimeList.withOAuthToken("x"));
    }

    @Test
    public void testNullAuthenticator(){
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeList.withAuthorization(null));
    }

    @Test
    public void testNullAnimeRanking(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getAnimeRanking(null));
    }

    @Test
    public void testNullAnimeSeason(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getAnimeSeason(2020, null));
    }

    @Test
    public void testNullUserAnimeList(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getUserAnimeListing(null));
    }

    @Test
    public void testNullMangaRanking(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getMangaRanking(null));
    }

    @Test
    public void testNullUserMangaList(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getUserMangaListing(null));
    }

    @Test
    public void testNullUser(){
        Assertions.assertThrows(NullPointerException.class, () -> mal.getUser(null));
    }

    private static final String inverted = "^%s$|^%s(?=,)|(?<=\\w)\\{%s}|(?:^|,)%s\\{.*?}|,%s|(?<=\\{)%s,";

    @ParameterizedTest
    @ValueSource(strings={"%s", "%s,%s", "a,%s", "a{%s}", "%s{a}", "a{%s}", "a{a,%s}", "a{%s,a}"})
    public void testInvertedRegex(final String raw){
        final String sf = "[%s]: '%s' should not have contained '%s'";
        final String inv = raw.replaceAll(inverted, "");
        Assertions.assertFalse(inv.contains("%s"), String.format(sf, raw, inv, "%s"));
        Assertions.assertFalse(inv.contains("{}"), String.format(sf, raw, inv, "{}"));
        Assertions.assertFalse(inv.contains("{,"), String.format(sf, raw, inv, "{,"));
        Assertions.assertFalse(inv.contains(",}"), String.format(sf, raw, inv, ",}"));
        Assertions.assertFalse(inv.startsWith(","), String.format(sf, raw, inv, ",$"));
        Assertions.assertFalse(inv.endsWith(","), String.format(sf, raw, inv, "^,"));
    }

}
