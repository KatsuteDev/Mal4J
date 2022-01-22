package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.manga.MangaListStatus;
import com.kttdevelopment.mal4j.manga.property.MangaSort;
import com.kttdevelopment.mal4j.manga.property.MangaStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestUserMangaListing {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();
    }

    @Test
    public void testStatus(){
        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .withStatus(MangaStatus.PlanToRead)
                .withLimit(1)
                .withFields(Fields.Manga.list_status)
                .search();
        annotateTest(() -> assertEquals(MangaStatus.PlanToRead, list.get(0).getStatus()));
    }

    @Test
    public void testSort(){
        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .sortBy(MangaSort.UpdatedAt)
                .withLimit(2)
                .withFields(Fields.Manga.list_status)
                .search();
        annotateTest(() -> assertTrue(list.get(0).getUpdatedAt().getTime() > list.get(1).getUpdatedAt().getTime()));
    }

}
