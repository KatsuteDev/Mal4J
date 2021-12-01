package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestAnimeListStatusUsername {
    
    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }
    
    @Test
    public void test(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing("KatsuteDev")
                .withLimit(1)
                .withFields(Fields.Anime.list_status)
                .includeNSFW()
                .search();

        Assertions.assertNotEquals(0, list.size(), Workflow.errorSupplier("User Anime list status not found"));
    }
    
}
