package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.*;
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
                .withFields(Fields.Manga.rank, Fields.Manga.media_type)
                .search();
        final MangaRanking first = ranking.get(0);
        Assertions.assertEquals(1,first.getRanking());
        Assertions.assertEquals(MangaType.Manga, first.getMangaPreview().getType());
        Assertions.assertNotNull(first.getPreviousRank(), "Failed to get previous rank for Anime (this is an external issue, ignore this)");
    }

    @SuppressWarnings("EmptyMethod")
    @Test @DisplayName("#5 - Ranking") @Disabled
    public void testNSFW(){
        // difficult to test since NSFW is unlikely to be in top ranking
    }

}
