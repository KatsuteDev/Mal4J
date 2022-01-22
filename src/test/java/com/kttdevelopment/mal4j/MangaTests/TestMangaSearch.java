package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.manga.MangaPreview;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestMangaSearch {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void testSearch(){
        final List<MangaPreview> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .withNoFields()
                .search();
        annotateTest(() -> assertEquals(TestProvider.AltMangaID, search.get(0).getID()));
        annotateTest(() -> assertNotEquals(1, search.size()));
    }

    @Test
    final void testOffsetLimit(){
        final List<MangaPreview> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .withLimit(1)
                .withOffset(1)
                .withNoFields()
                .search();
        annotateTest(() -> assertNotEquals(TestProvider.AltMangaID, search.get(0).getID()));
        annotateTest(() -> assertEquals(1, search.size()));
    }

    @Test
    final void testFields(){
        final List<MangaPreview> search =
            mal.getManga()
                .withQuery(TestProvider.MangaQuery)
                .withLimit(1)
                .withNoFields()
                .search();
        annotateTest(() -> assertNull(search.get(0).getType()));
    }

    @Test
    final void testNSFW(){
        {
            final List<MangaPreview> search =
                mal.getManga()
                    .withQuery(TestProvider.NSFW_MangaQuery)
                    .withLimit(1)
                    .withNoFields()
                    .search();
            annotateTest(() -> assertEquals(0, search.size()));
        }
        {
            final List<MangaPreview> search =
                mal.getManga()
                    .withQuery(TestProvider.NSFW_MangaQuery)
                    .withLimit(1)
                    .withNoFields()
                    .includeNSFW()
                    .search();
            annotateTest(() -> assertEquals(TestProvider.NSFW_MangaID, search.get(0).getID()));
        }
    }

}
