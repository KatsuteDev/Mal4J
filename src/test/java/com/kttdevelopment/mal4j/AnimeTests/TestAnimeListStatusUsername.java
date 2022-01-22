package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestAnimeListStatusUsername {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void test(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing("KatsuteDev")
                .withLimit(1)
                .withFields(Fields.Anime.list_status)
                .includeNSFW()
                .search();

        annotateTest(() -> assertNotEquals(0, list.size(), "User Anime list status not found"));
    }

}
