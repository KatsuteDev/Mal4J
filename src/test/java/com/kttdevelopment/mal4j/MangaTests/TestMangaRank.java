package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.manga.MangaRanking;
import com.kttdevelopment.mal4j.manga.property.MangaRankingType;
import com.kttdevelopment.mal4j.manga.property.MangaType;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestMangaRank {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testRanking(){
        final List<MangaRanking> ranking =
            mal.getMangaRanking(MangaRankingType.Manga)
                .withLimit(1)
                .search();
        final MangaRanking first = ranking.get(0);
        Assertions.assertEquals(1,first.getRanking());
        Assertions.assertTrue(first.getPreviousRank() < 1);
        Assertions.assertEquals(MangaType.Manga, first.getMangaPreview().getType());
    }

    @Test @DisplayName("#5 - Ranking") @Disabled
    public void testNSFW(){
        // difficult to test since NSFW is unlikely to be in top ranking
    }

}
