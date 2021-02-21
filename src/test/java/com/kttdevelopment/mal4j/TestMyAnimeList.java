package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.*;

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

    @Test
    public void testNonAtMeUser(){
        Assertions.assertThrows(UnsupportedOperationException.class, () -> mal.getUser("null"));
    }

}
