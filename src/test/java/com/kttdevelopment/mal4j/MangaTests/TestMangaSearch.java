package com.kttdevelopment.mal4j.MangaTests;

import dev.katsute.jcore.Workflow;
import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.manga.MangaPreview;
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
        final List<MangaPreview> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .withNoFields()
                .search();
        Assertions.assertEquals(TestProvider.AltMangaID, search.get(0).getID(),
                                Workflow.errorSupplier("Expected first search ID to match"));
        Assertions.assertNotEquals(1, search.size(),
                                   Workflow.errorSupplier("Expected search to return more than 1"));
    }

    @Test
    public void testOffsetLimit(){
        final List<MangaPreview> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .withLimit(1)
                .withOffset(1)
                .withNoFields()
                .search();
        Assertions.assertNotEquals(TestProvider.AltMangaID, search.get(0).getID(),
                                   Workflow.errorSupplier("Expected first search ID to not match"));
        Assertions.assertEquals(1, search.size(),
                                Workflow.errorSupplier("Expected search to return only 1"));
    }

    @Test
    public void testFields(){
        final List<MangaPreview> search =
            mal.getManga()
                .withQuery(TestProvider.MangaQuery)
                .withLimit(1)
                .withNoFields()
                .search();
        Assertions.assertNull(search.get(0).getType(),
                              Workflow.errorSupplier("Expected type to be null"));
    }

    @Test
    public void testNSFW(){
        {
            final List<MangaPreview> search =
                mal.getManga()
                    .withQuery(TestProvider.NSFW_MangaQuery)
                    .withLimit(1)
                    .withNoFields()
                    .search();
            Assertions.assertEquals(0, search.size(),
                                    Workflow.errorSupplier("Expected search to return 0"));
        }
        {
            final List<MangaPreview> search =
                mal.getManga()
                    .withQuery(TestProvider.NSFW_MangaQuery)
                    .withLimit(1)
                    .withNoFields()
                    .includeNSFW()
                    .search();
            Assertions.assertEquals(TestProvider.NSFW_MangaID, search.get(0).getID(),
                                    Workflow.errorSupplier("Expected Manga ID to match test ID"));
        }
    }

}
