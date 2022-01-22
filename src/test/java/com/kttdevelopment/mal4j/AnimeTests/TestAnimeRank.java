package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimeRanking;
import com.kttdevelopment.mal4j.anime.property.AnimeRankingType;
import com.kttdevelopment.mal4j.anime.property.AnimeType;
import org.junit.jupiter.api.*;

import java.util.List;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

final class TestAnimeRank {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void testRanking(){
        final List<AnimeRanking> ranking =
            mal.getAnimeRanking(AnimeRankingType.Movie)
                .withLimit(1)
                .withFields(Fields.Anime.rank, Fields.Anime.media_type)
                .search();
        final AnimeRanking first = ranking.get(0);
        annotateTest(() -> assertEquals(1, first.getRanking()));
        annotateTest(() -> assertEquals(AnimeType.Movie, first.getAnimePreview().getType()));
        // annotateTest(() -> assertNotNull(first.getPreviousRank()));
        annotateTest(() -> assumeTrue(first.getPreviousRank() != null, "Failed to get previous rank for Anime (this is an external issue, ignore this)"));
    }

    @SuppressWarnings("EmptyMethod")
    @Test @Disabled
    final void testNSFW(){
        // difficult to test since NSFW is unlikely to be in top ranking
    }

}
