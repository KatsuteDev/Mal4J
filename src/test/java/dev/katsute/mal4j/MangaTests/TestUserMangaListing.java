package dev.katsute.mal4j.MangaTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.manga.MangaListStatus;
import dev.katsute.mal4j.manga.property.MangaSort;
import dev.katsute.mal4j.manga.property.MangaStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestUserMangaListing {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();
    }

    @Test
    final void testStatus(){
        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .withStatus(MangaStatus.PlanToRead)
                .withLimit(1)
                .withFields(Fields.Manga.list_status)
                .search();
        assertEquals(MangaStatus.PlanToRead, list.get(0).getStatus());
    }

    @Test
    final void testSort(){
        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .sortBy(MangaSort.UpdatedAt)
                .withLimit(2)
                .withFields(Fields.Manga.list_status)
                .search();
        assertTrue(list.get(0).getUpdatedAt().getTime() > list.get(1).getUpdatedAt().getTime());
    }

}
