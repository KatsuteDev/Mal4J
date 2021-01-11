package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.manga.Manga;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestMangaSearch {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testSearch(){
        final List<Manga> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .search();
        Assertions.assertEquals(TestProvider.AltMangaID, search.get(0).getID());
        Assertions.assertNotEquals(1, search.size());
    }

    @Test
    public void testOffsetLimit(){
        final List<Manga> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .withLimit(1)
                .withOffset(1)
                .search();
        Assertions.assertNotEquals(TestProvider.AltMangaID, search.get(0).getID());
        Assertions.assertEquals(1, search.size());
    }

    @Test
    public void testFields(){
        final List<Manga> search =
            mal.getManga()
                .withQuery(TestProvider.MangaQuery)
                .withLimit(1)
                .withFields(new String[0])
                .search();
        Assertions.assertNull(search.get(0).getType());
    }

    @Test
    public void testNSFW(){
        {
            final List<Manga> search =
                mal.getManga()
                    .withQuery(TestProvider.NSFW_MangaQuery)
                    .withLimit(1)
                    .search();
            Assertions.assertEquals(0, search.size());
        }
        {
            final List<Manga> search =
                mal.getManga()
                   .withQuery(TestProvider.NSFW_MangaQuery)
                   .withLimit(1)
                   .includeNSFW(true)
                   .search();
            Assertions.assertEquals(TestProvider.NSFW_MangaID, search.get(0).getID());
        }
    }

}
