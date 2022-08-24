package dev.katsute.mal4j.AnimeTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.anime.AnimeListStatus;
import dev.katsute.mal4j.anime.property.AnimeSort;
import dev.katsute.mal4j.anime.property.AnimeStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestUserAnimeListing {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();
    }

    @Test
    final void testStatus(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withStatus(AnimeStatus.Dropped)
                .withFields(Fields.Anime.list_status)
                .withLimit(1)
                .search();
        assertEquals(AnimeStatus.Dropped, list.get(0).getStatus());
    }

    @Test
    final void testSort(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .sortBy(AnimeSort.UpdatedAt)
                .withFields(Fields.Anime.list_status)
                .withLimit(2)
                .search();
        assertTrue(list.get(0).getUpdatedAt().getTime() > list.get(1).getUpdatedAt().getTime());
    }

}
