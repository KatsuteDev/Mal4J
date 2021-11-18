package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import com.kttdevelopment.mal4j.anime.property.AnimeSort;
import com.kttdevelopment.mal4j.anime.property.AnimeStatus;
import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestUserAnimeListing {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();
    }

    @Test
    public void testStatus(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withStatus(AnimeStatus.Dropped)
                .withFields(Fields.Anime.list_status)
                .withLimit(1)
                .search();
        Assertions.assertEquals(AnimeStatus.Dropped, list.get(0).getStatus(),
                                Workflow.errorSupplier("Expected listing status to match"));
    }

    @Test
    public void testSort(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .sortBy(AnimeSort.UpdatedAt)
                .withFields(Fields.Anime.list_status)
                .withLimit(2)
                .search();
        Assertions.assertTrue(list.get(0).getUpdatedAt().getTime() > list.get(1).getUpdatedAt().getTime(),
                              Workflow.errorSupplier("Expected listing status to be sorted"));
    }

}
