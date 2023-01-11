package dev.katsute.mal4j.MangaTests;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.manga.Manga;
import dev.katsute.mal4j.manga.property.MangaType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestMangaSearch {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void testSearch(){
        final List<Manga> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .withNoFields()
                .search();
        assertEquals(TestProvider.AltMangaID, search.get(0).getID());
        assertNotEquals(1, search.size());
    }

    @Test
    final void testOffsetLimit(){
        final List<Manga> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .withLimit(1)
                .withOffset(1)
                .withNoFields()
                .search();
        assertNotEquals(TestProvider.AltMangaID, search.get(0).getID());
        assertEquals(1, search.size());
    }

    @Test
    final void testFields(){
        final List<Manga> search =
            mal.getManga()
                .withQuery(TestProvider.MangaQuery)
                .withLimit(1)
                .withNoFields()
                .search();
        assertEquals(MangaType.Unknown, search.get(0).getType());
    }

    @Test
    final void testNSFW(){
        {
            final List<Manga> search =
                mal.getManga()
                    .withQuery(TestProvider.NSFW_MangaQuery)
                    .withLimit(1)
                    .withNoFields()
                    .search();
            assertEquals(0, search.size());
        }
        {
            final List<Manga> search =
                mal.getManga()
                    .withQuery(TestProvider.NSFW_MangaQuery)
                    .withLimit(1)
                    .withNoFields()
                    .includeNSFW()
                    .search();
            assertEquals(TestProvider.NSFW_MangaID, search.get(0).getID());
        }
    }

}
