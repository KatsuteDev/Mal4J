package com.kttdevelopment.myanimelist.AnimeTests;

import com.kttdevelopment.myanimelist.*;
import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.AnimePreview;
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
                .search();
        Assertions.assertEquals(TestProvider.AnimeID, search.get(0).getID());
        Assertions.assertNotEquals(1, search.size());
    }

    @Test
    public void testOffsetLimit(){
        final List<Anime> search =
            mal.getAnime()
                .withQuery(TestProvider.AnimeQuery)
                .withLimit(1)
                .withOffset(1)
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
                    .search();
            Assertions.assertEquals(0, search.size());
        }
        {
            final List<Anime> search =
                mal.getAnime()
                   .withQuery(TestProvider.NSFW_AnimeQuery)
                   .withLimit(1)
                   .includeNSFW(true)
                   .search();
            Assertions.assertEquals(TestProvider.NSFW_AnimeID, search.get(0).getID());
        }
    }

}
