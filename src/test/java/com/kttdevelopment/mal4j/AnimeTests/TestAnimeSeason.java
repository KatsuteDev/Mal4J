package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimePreview;
import com.kttdevelopment.mal4j.anime.property.AnimeSeasonSort;
import com.kttdevelopment.mal4j.anime.property.time.Season;
import com.kttdevelopment.mal4j.property.NSFW;
import dev.katsute.jcore.Workflow;
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
        final int year = 2019;
        final List<AnimePreview> season =
            mal.getAnimeSeason(year, Season.Summer)
                .withField(Fields.Anime.start_season)
                .search();

        int thisYear = 0;
        int otherYear = 0;

        AnimePreview anime = null;
        for(final AnimePreview iterator : season)
            if(iterator.getStartSeason().getYear() == year){
                thisYear++;
                anime = iterator;
            }else
                otherYear++;

        Assertions.assertTrue(thisYear > otherYear, Workflow.errorSupplier("Expected seasonal search to return mostly from selected year (search contained mostly results from other years)"));

        Assertions.assertNotNull(anime, Workflow.errorSupplier("Expected seasonal search to return an Anime from selected year"));
        Assertions.assertTrue(
            anime.getStartSeason().getSeason() == Season.Summer || anime.getStartSeason().getSeason() == Season.Spring,
            Workflow.errorSupplier("Anime start season was supposed to be either Summer or Spring but was " + anime.getStartSeason().getSeason().name())
        );
    }

    @Test
    public void testSort(){
        final List<AnimePreview> season =
            mal.getAnimeSeason(2020, Season.Winter)
                .withLimit(2)
                .sortBy(AnimeSeasonSort.Users)
                .withFields(Fields.Anime.scoring_users)
                .search();
        final AnimePreview first = season.get(0);
        final AnimePreview second = season.get(1);
        Assertions.assertTrue(first.getUserScoringCount() > second.getUserScoringCount(),
                              Workflow.errorSupplier("Expected season to be sorted"));
    }

    @Test
    public void testNSFW(){
        final List<AnimePreview> season =
            mal.getAnimeSeason(2014, Season.Winter)
                .includeNSFW(true)
                .withFields(Fields.Anime.nsfw)
                .withLimit(100)
                .search();
        boolean hasNSFW = false;
        for(final AnimePreview animePreview : season){
            if(animePreview.getNSFW() != NSFW.White){
                hasNSFW = true;
                break;
            }
        }

        Assertions.assertTrue(hasNSFW,
                              Workflow.errorSupplier("Failed to find NSFW seasonal Anime"));
    }

}
