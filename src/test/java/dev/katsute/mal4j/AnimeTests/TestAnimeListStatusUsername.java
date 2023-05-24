package dev.katsute.mal4j.AnimeTests;

import dev.katsute.mal4j.Fields;
import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.anime.AnimeListStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        assertNotEquals(0, list.size(), "User Anime list status not found");
    }

}