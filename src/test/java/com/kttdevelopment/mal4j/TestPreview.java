package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.anime.property.AnimeRankingType;
import com.kttdevelopment.mal4j.manga.Manga;
import com.kttdevelopment.mal4j.manga.property.MangaRankingType;
import org.junit.jupiter.api.*;

final class TestPreview {

    private static MyAnimeList mal;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();

        ((MyAnimeListImpl) mal).clearExperimentalFeatures();
    }

    @Test
    final void testAnimePreview(){
        final Anime preview = mal.getAnimeRanking(AnimeRankingType.All).search().get(0).getAnime();
        Assertions.assertTrue(preview.toString().contains("background='null'"));
        Assertions.assertNotNull(preview.getBackground());
        Assertions.assertTrue(preview.toString().contains("background='" + preview.getBackground() + "'"));
    }

    @Test
    final void testMangaPreview(){
        final Manga preview = mal.getMangaRanking(MangaRankingType.All).search().get(0).getManga();
        Assertions.assertTrue(preview.toString().contains("background='null'"));
        Assertions.assertNotNull(preview.getBackground());
        Assertions.assertTrue(preview.toString().contains("background='" + preview.getBackground() + "'"));
    }

}
