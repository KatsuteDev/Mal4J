package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.Anime;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestAnimeSearch {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testSearch(){
        final List<Anime> search =
            mal.getAnime()
                .withQuery(TestProvider.AnimeQuery)
                .withNoFields()
                .search();
        Assertions.assertEquals(TestProvider.AnimeID, search.get(0).getID());
        Assertions.assertNotEquals(1, search.size());
    }

    @Test
    public void testOffsetLimit(){
        final List<Anime> search =
            mal.getAnime()
                .withQuery(TestProvider.AltAnimeQuery)
                .withLimit(1)
                .withOffset(1)
                .withNoFields()
                .search();
        Assertions.assertNotEquals(TestProvider.AnimeID, search.get(0).getID());
        Assertions.assertEquals(1, search.size());
    }

    @Test
    public void testFields(){
        final List<Anime> search =
            mal.getAnime()
                .withQuery(TestProvider.AnimeQuery)
                .withLimit(1)
                .withNoFields()
                .search();
        Assertions.assertNull(search.get(0).getType());
    }

    @Test
    public void testNSFW(){
        {
            final List<Anime> search =
                mal.getAnime()
                    .withQuery(TestProvider.NSFW_AnimeQuery)
                    .withLimit(1)
                    .withNoFields()
                    .search();
            Assertions.assertEquals(0, search.size());
        }
        {
            final List<Anime> search =
                mal.getAnime()
                   .withQuery(TestProvider.NSFW_AnimeQuery)
                   .withLimit(1)
                   .withNoFields()
                   .includeNSFW()
                   .search();
            Assertions.assertTrue(
                search.get(0).getID() == TestProvider.NSFW_AnimeID || search.get(0).getID() == TestProvider.AltNSFW_AnimeID,
                "NSFW Anime ID was supposed to be either " + TestProvider.NSFW_AnimeID + " or " + TestProvider.AltNSFW_AnimeID + " but was " + search.get(0).getID()
            );
        }
    }

}
