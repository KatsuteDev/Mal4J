package dev.katsute.mal4j;

import dev.katsute.mal4j.anime.property.AnimeRankingType;
import dev.katsute.mal4j.exception.InvalidTokenException;
import dev.katsute.mal4j.manga.property.MangaRankingType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertThrows(NullPointerException.class, () -> MyAnimeList.withClientID(null));
    }

    @Test
    final void testNullToken(){
        assertThrows(NullPointerException.class, () -> MyAnimeList.withToken(null));
    }

    @Test
    final void testNoBearerToken(){
        assertThrows(InvalidTokenException.class, () -> MyAnimeList.withToken("x"));
    }

    @Test
    final void testInvalidToken(){
        assertThrows(InvalidTokenException.class, () -> MyAnimeList.withToken("Bearer invalid").getAnime(TestProvider.AnimeID));
    }

    @Test
    final void testNullAuthenticator(){
        assertThrows(NullPointerException.class, () -> MyAnimeList.withOAuth2(null));
    }

    // null parameter tests

    @Test
    final void testNullAnimeRanking(){
        assertThrows(NullPointerException.class, () -> mal.getAnimeRanking((AnimeRankingType) null));
        assertThrows(NullPointerException.class, () -> mal.getAnimeRanking((String) null));
    }

    @Test
    final void testNullAnimeSeason(){
        assertThrows(NullPointerException.class, () -> mal.getAnimeSeason(2020, null));
    }

    @Test
    final void testNullUserAnimeList(){
        assertThrows(NullPointerException.class, () -> mal.getUserAnimeListing(null));
    }

    @Test
    final void testNullMangaRanking(){
        assertThrows(NullPointerException.class, () -> mal.getMangaRanking((MangaRankingType) null));
        assertThrows(NullPointerException.class, () -> mal.getMangaRanking((String) null));
    }

    @Test
    final void testNullUserMangaList(){
        assertThrows(NullPointerException.class, () -> mal.getUserMangaListing(null));
    }

    @Test
    final void testNullUser(){
        assertThrows(NullPointerException.class, () -> mal.getUser(null));
    }

    // inverted field test

    private static final String inverted = "^%s$|^%s(?=,)|(?<=\\w)\\{%s}|(?:^|,)%s\\{.*?}|,%s|(?<=\\{)%s,";

    @ParameterizedTest
    @ValueSource(strings={"%s", "%s,%s", "a,%s", "a{%s}", "%s{a}", "a{%s}", "a{a,%s}", "a{%s,a}"})
    final void testInvertedRegex(final String raw){
        final String inv = raw.replaceAll(inverted, "");
        assertFalse(inv.contains("%s"));
        assertFalse(inv.contains("{}"));
        assertFalse(inv.contains("{,"));
        assertFalse(inv.contains(",}"));
        assertFalse(inv.startsWith(","));
        assertFalse(inv.endsWith(","));
    }

}
