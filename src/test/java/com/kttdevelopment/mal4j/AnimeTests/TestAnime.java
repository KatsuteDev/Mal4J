package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.anime.*;
import com.kttdevelopment.mal4j.anime.property.AnimeStatistics;
import com.kttdevelopment.mal4j.manga.RelatedManga;
import org.junit.jupiter.api.*;

public class TestAnime {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static Anime anime;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        anime = mal.getAnime(TestProvider.AltAnimeID);
    }

    @Test
    public void testAnime(){
        Assertions.assertEquals(anime, anime.getAnime());

        Assertions.assertEquals(TestProvider.AltAnimeID, anime.getID());
        Assertions.assertNotNull(anime.getTitle());
        Assertions.assertNotNull(anime.getMainPicture().getMediumURL());
        Assertions.assertNotNull(anime.getMainPicture().getLargeURL());
        Assertions.assertNotNull(anime.getAlternativeTitles().getEnglish());
        Assertions.assertNotNull(anime.getAlternativeTitles().getJapanese());
        Assertions.assertNotNull(anime.getAlternativeTitles().getSynonyms());
        Assertions.assertDoesNotThrow(() -> anime.getStartDate().getTime());
        Assertions.assertDoesNotThrow(() -> anime.getStartDateEpochMillis());
        Assertions.assertDoesNotThrow(() -> anime.getEndDate().getTime());
        Assertions.assertDoesNotThrow(() -> anime.getEndDateEpochMillis());
        Assertions.assertNotNull(anime.getSynopsis());
        Assertions.assertDoesNotThrow(() -> anime.getMeanRating());
        Assertions.assertDoesNotThrow(() -> anime.getRank());
        Assertions.assertDoesNotThrow(() -> anime.getPopularity());
        Assertions.assertDoesNotThrow(() -> anime.getUserListingCount());
        Assertions.assertDoesNotThrow(() -> anime.getUserScoringCount());
        Assertions.assertNotNull(anime.getNSFW());
        Assertions.assertDoesNotThrow(() -> anime.getGenres()[0].getId());
        Assertions.assertNotNull(anime.getGenres()[0].getName());
        Assertions.assertDoesNotThrow(() -> anime.getCreatedAt().getTime());
        Assertions.assertDoesNotThrow(() -> anime.getCreatedAtEpochMillis());
        Assertions.assertDoesNotThrow(() -> anime.getUpdatedAt().getTime());
        Assertions.assertDoesNotThrow(() -> anime.getUpdatedAtEpochMillis());
        Assertions.assertNotNull(anime.getType());
        Assertions.assertNotNull(anime.getStatus());
        Assertions.assertDoesNotThrow(() -> anime.getEpisodes());
        Assertions.assertNotNull(anime.getStartSeason().getSeason());
        Assertions.assertDoesNotThrow(() -> anime.getStartSeason().getYear());
        Assertions.assertNotNull(anime.getBroadcast().getDayOfWeek());
        Assertions.assertNotNull(anime.getBroadcast().getStartTime());
        Assertions.assertNotNull(anime.getSource());
        Assertions.assertDoesNotThrow(() -> anime.getAverageEpisodeLength());
        Assertions.assertNotNull(anime.getRating());
        Assertions.assertDoesNotThrow(() -> anime.getStudios()[0].getID());
        Assertions.assertNotNull(anime.getStudios()[0].getName());
        Assertions.assertNotNull(anime.getPictures()[0].getMediumURL());
        Assertions.assertNotNull(anime.getPictures()[0].getLargeURL());
        Assertions.assertNotNull(anime.getBackground());
    }

    @Test
    public void testRelatedAnime(){
        final RelatedAnime relatedAnime = anime.getRelatedAnime()[0];
        Assertions.assertDoesNotThrow(() -> relatedAnime.getAnimePreview().getID());
        Assertions.assertNotNull(relatedAnime.getRelationType());
        Assertions.assertNotNull(relatedAnime.getRelationTypeFormat());
    }

    @Test @DisplayName("Anime may not have related Manga") @Disabled
    public void testRelatedManga(){
        final RelatedManga relatedManga = anime.getRelatedManga()[0];
        Assertions.assertDoesNotThrow(() -> relatedManga.getMangaPreview().getID());
        Assertions.assertNotNull(relatedManga.getRelationType());
        Assertions.assertNotNull(relatedManga.getRelationTypeFormat());
    }

    @Test
    public void testRecommendations(){
        final AnimeRecommendation recommendation = anime.getRecommendations()[0];
        Assertions.assertDoesNotThrow(() -> recommendation.getAnimePreview().getID());
        Assertions.assertDoesNotThrow(recommendation::getRecommendations);
    }

    @Test
    public void testStatistics(){
        final AnimeStatistics statistics = anime.getStatistics();
        Assertions.assertDoesNotThrow(statistics::getCompleted);
        Assertions.assertDoesNotThrow(statistics::getDropped);
        Assertions.assertDoesNotThrow(statistics::getOnHold);
        Assertions.assertDoesNotThrow(statistics::getPlanToWatch);
        Assertions.assertDoesNotThrow(statistics::getWatching);
        Assertions.assertDoesNotThrow(statistics::getUserCount);
    }

}
