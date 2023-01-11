package dev.katsute.mal4j.MangaTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.manga.MangaListStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestMangaListStatusUsername {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void test(){
        final List<MangaListStatus> list =
            mal.getUserMangaListing("KatsuteDev")
                .withLimit(1)
                .withFields(Fields.Manga.list_status)
                .includeNSFW()
                .search();

        assertNotEquals(0, list.size(), "User Manga list status not found");
    }

}
