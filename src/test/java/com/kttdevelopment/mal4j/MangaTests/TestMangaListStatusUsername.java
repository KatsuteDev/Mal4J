package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.manga.MangaListStatus;
import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestMangaListStatusUsername {
    
    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll() throws Throwable{
        mal = TestProvider.getMyAnimeList();
    }
    
    @Test
    public void test(){
        final List<MangaListStatus> list =
            mal.getUserMangaListing("KatsuteDev")
                .withLimit(1)
                .withFields(Fields.Manga.list_status)
                .includeNSFW()
                .search();

        Assertions.assertNotEquals(0, list.size(), Workflow.errorSupplier("User Manga list status not found"));
    }
    
}
