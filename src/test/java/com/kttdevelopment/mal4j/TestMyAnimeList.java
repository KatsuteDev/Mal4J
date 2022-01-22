package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestMyAnimeList {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = MyAnimeList.withToken("Bearer null");
    }

    // auth parameter tests

    @Test
    final void testNullClientID(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> MyAnimeList.withClientID(null)));
    }

    @Test
    final void testNullToken(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> MyAnimeList.withToken(null)));
    }

    @Test
    final void testNoBearerToken(){
        annotateTest(() -> assertThrows(InvalidTokenException.class, () -> MyAnimeList.withToken("x")));
    }

    @Test
    final void testInvalidToken(){
        annotateTest(() -> assertThrows(InvalidTokenException.class, () -> MyAnimeList.withToken("Bearer invalid").getAnime(TestProvider.AnimeID)));
    }

    @Test
    final void testNullAuthenticator(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> MyAnimeList.withOAuth2(null)));
    }

    // null parameter tests

    @Test
    final void testNullAnimeRanking(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> mal.getAnimeRanking(null)));
    }

    @Test
    final void testNullAnimeSeason(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> mal.getAnimeSeason(2020, null)));
    }

    @Test
    final void testNullUserAnimeList(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> mal.getUserAnimeListing(null)));
    }

    @Test
    final void testNullMangaRanking(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> mal.getMangaRanking(null)));
    }

    @Test
    final void testNullUserMangaList(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> mal.getUserMangaListing(null)));
    }

    @Test
    final void testNullUser(){
        annotateTest(() -> assertThrows(NullPointerException.class, () -> mal.getUser(null)));
    }

    // inverted field test

    private static final String inverted = "^%s$|^%s(?=,)|(?<=\\w)\\{%s}|(?:^|,)%s\\{.*?}|,%s|(?<=\\{)%s,";

    @ParameterizedTest
    @ValueSource(strings={"%s", "%s,%s", "a,%s", "a{%s}", "%s{a}", "a{%s}", "a{a,%s}", "a{%s,a}"})
    final void testInvertedRegex(final String raw){
        final String inv = raw.replaceAll(inverted, "");
        annotateTest(() -> assertFalse(inv.contains("%s")));
        annotateTest(() -> assertFalse(inv.contains("{}")));
        annotateTest(() -> assertFalse(inv.contains("{,")));
        annotateTest(() -> assertFalse(inv.contains(",}")));
        annotateTest(() -> assertFalse(inv.startsWith(",")));
        annotateTest(() -> assertFalse(inv.endsWith(",")));
    }

}
