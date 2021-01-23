package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.anime.AnimeRanking;
import com.kttdevelopment.mal4j.anime.property.AnimeRankingType;
import com.kttdevelopment.mal4j.anime.property.AnimeType;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestAnimeRank {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testRanking(){
        final List<AnimeRanking> ranking =
            mal.getAnimeRanking(AnimeRankingType.Movie)
                .withLimit(1)
                .search();
        final AnimeRanking first = ranking.get(0);
        Assertions.assertEquals(1, first.getRanking());
        // Assertions.assertTrue(first.getPreviousRank() < 1); // unable to test
        Assertions.assertEquals(AnimeType.Movie, first.getAnimePreview().getType());
    }

    @Test @DisplayName("#5 - Ranking") @Disabled
    public void testNSFW(){
        // difficult to test since NSFW is unlikely to be in top ranking
    }

}
