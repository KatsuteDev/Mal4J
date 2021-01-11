package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.anime.AnimePreview;
import com.kttdevelopment.mal4j.anime.property.AnimeSeasonSort;
import com.kttdevelopment.mal4j.anime.property.time.Season;
import com.kttdevelopment.mal4j.property.NSFW;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestAnimeSeason {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testSeason(){
        final List<Anime> season =
            mal.getAnimeSeason(2020, Season.Summer)
                .withLimit(1)
                .withField("start_season")
                .search();
        final AnimePreview anime = season.get(0);
        Assertions.assertEquals(2020, anime.getStartSeason().getYear());
        Assertions.assertEquals(Season.Summer, anime.getStartSeason().getSeason());
    }

    @Test
    public void testSort(){
        final List<Anime> season =
            mal.getAnimeSeason(2020, Season.Winter)
                .withLimit(2)
                .sortBy(AnimeSeasonSort.Users)
                .withField(MyAnimeList.ALL_ANIME_FIELDS)
                .search();
        final AnimePreview first = season.get(0);
        final AnimePreview second = season.get(1);
        Assertions.assertTrue(first.getUserScoringCount() > second.getUserScoringCount());
    }

    @Test @DisplayName("#5 - Seasonal") @Disabled
    public void testNSFW(){
        final List<Anime> season =
            mal.getAnimeSeason(2014, Season.Winter)
                .search();
        boolean hasNSFW = false;
        for(final Anime animePreview : season)
            if(animePreview.getNSFW() != NSFW.White)
                hasNSFW = true;
        Assertions.assertTrue(hasNSFW);
    }

}
