package dev.katsute.mal4j.AnimeTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.anime.property.AnimeSeasonSort;
import dev.katsute.mal4j.anime.property.time.Season;
import dev.katsute.mal4j.property.NSFW;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestAnimeSeason {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void testSeason(){
        final int year = 2019;
        final List<Anime> season =
            mal.getAnimeSeason(year, Season.Summer)
                .withField(Fields.Anime.start_season)
                .search();

        int thisYear = 0;
        int otherYear = 0;

        Anime anime = null;
        for(final Anime iterator : season)
            if(iterator.getStartSeason().getYear() == year){
                thisYear++;
                anime = iterator;
            }else
                otherYear++;

        final int finalThisYear  = thisYear;
        final int finalOtherYear = otherYear;
        assertTrue(finalThisYear > finalOtherYear, "Expected seasonal search to return mostly from selected year (search contained mostly results from other years)");

        final Anime finalAnime = anime;
        assertNotNull(finalAnime, "Expected seasonal search to return an Anime from selected year");
        assertTrue(
            finalAnime.getStartSeason().getSeason() == Season.Summer || finalAnime.getStartSeason().getSeason() == Season.Spring,
            "Anime start season was supposed to be either Summer or Spring but was " + finalAnime.getStartSeason().getSeason().name()
        );
    }

    @Test
    final void testSort(){
        final List<Anime> season =
            mal.getAnimeSeason(2020, Season.Winter)
                .withLimit(2)
                .sortBy(AnimeSeasonSort.Users)
                .withFields(Fields.Anime.scoring_users)
                .search();
        final Anime first = season.get(0);
        final Anime second = season.get(1);
        assertTrue(first.getUserScoringCount() > second.getUserScoringCount(), "Expected season to be sorted");
    }

    @Test
    final void testNSFW(){
        final List<Anime> season =
            mal.getAnimeSeason(2014, Season.Winter)
                .includeNSFW(true)
                .withFields(Fields.Anime.nsfw)
                .withLimit(100)
                .search();
        boolean hasNSFW = false;
        for(final Anime animePreview : season){
            if(animePreview.getNSFW() != NSFW.White){
                hasNSFW = true;
                break;
            }
        }

        final boolean finalHasNSFW = hasNSFW;
        assertTrue(finalHasNSFW, "Failed to find NSFW seasonal Anime");
    }

}
