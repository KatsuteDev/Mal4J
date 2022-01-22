package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.manga.MangaRanking;
import com.kttdevelopment.mal4j.manga.property.MangaRankingType;
import com.kttdevelopment.mal4j.manga.property.MangaType;
import org.junit.jupiter.api.*;

import java.util.List;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

final class TestMangaRank {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void testRanking(){
        final List<MangaRanking> ranking =
            mal.getMangaRanking(MangaRankingType.Manga)
                .withLimit(1)
                .withFields(Fields.Manga.rank, Fields.Manga.media_type)
                .search();
        final MangaRanking first = ranking.get(0);
        annotateTest(() -> assertEquals(1, first.getRanking()));
        annotateTest(() -> assertEquals(MangaType.Manga, first.getMangaPreview().getType()));
        // annotateTest(() -> assertNotNull(first.getPreviousRank()));
        annotateTest(() -> assumeTrue(first.getPreviousRank() != null, "Failed to get previous rank for Manga (this is an external issue, ignore this)"));
    }

    @SuppressWarnings("EmptyMethod")
    @Test @Disabled
    final void testNSFW(){
        // difficult to test since NSFW is unlikely to be in top ranking
    }

}
