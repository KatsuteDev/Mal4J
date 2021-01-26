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
        Assertions.assertNotNull(anime.getStartDate());
        Assertions.assertNotNull(anime.getEndDate());
        Assertions.assertNotNull(anime.getSynopsis());
        Assertions.assertNotNull(anime.getMeanRating());
        Assertions.assertNotNull(anime.getRank());
        Assertions.assertNotNull(anime.getPopularity());
        Assertions.assertNotNull(anime.getUserListingCount());
        Assertions.assertNotNull(anime.getUserScoringCount());
        Assertions.assertNotNull(anime.getNSFW());
        Assertions.assertNotNull(anime.getGenres()[0]);
        Assertions.assertNotNull(anime.getGenres()[0].getName());
        Assertions.assertNotNull(anime.getCreatedAt());
        Assertions.assertNotNull(anime.getCreatedAtEpochMillis());
        Assertions.assertNotNull(anime.getUpdatedAt());
        Assertions.assertNotNull(anime.getUpdatedAtEpochMillis());
        Assertions.assertNotNull(anime.getType());
        Assertions.assertNotNull(anime.getStatus());
        Assertions.assertNotNull(anime.getEpisodes());
        Assertions.assertNotNull(anime.getStartSeason().getSeason());
        Assertions.assertNotNull(anime.getStartSeason().getYear());
        Assertions.assertNotNull(anime.getBroadcast().getDayOfWeek());
        Assertions.assertNotNull(anime.getBroadcast().getStartTime());
        Assertions.assertNotNull(anime.getSource());
        Assertions.assertNotNull(anime.getAverageEpisodeLength());
        Assertions.assertNotNull(anime.getRating());
        Assertions.assertNotNull(anime.getStudios()[0].getID());
        Assertions.assertNotNull(anime.getStudios()[0].getName());
        Assertions.assertNotNull(anime.getPictures()[0].getMediumURL());
        Assertions.assertNotNull(anime.getPictures()[0].getLargeURL());
        Assertions.assertNotNull(anime.getBackground());
    }

    @Test
    public void testRelatedAnime(){
        final RelatedAnime relatedAnime = anime.getRelatedAnime()[0];
        Assertions.assertNotNull(relatedAnime.getAnimePreview().getID());
        Assertions.assertNotNull(relatedAnime.getRelationType());
        Assertions.assertNotNull(relatedAnime.getRelationTypeFormat());
    }

    @Test @DisplayName("Anime may not have related Manga") @Disabled
    public void testRelatedManga(){
        final RelatedManga relatedManga = anime.getRelatedManga()[0];
        Assertions.assertNotNull(relatedManga.getMangaPreview().getID());
        Assertions.assertNotNull(relatedManga.getRelationType());
        Assertions.assertNotNull(relatedManga.getRelationTypeFormat());
    }

    @Test
    public void testRecommendations(){
        final AnimeRecommendation recommendation = anime.getRecommendations()[0];
        Assertions.assertNotNull(recommendation.getAnimePreview().getID());
        Assertions.assertNotNull(recommendation.getRecommendations());
    }

    @Test
    public void testStatistics(){
        final AnimeStatistics statistics = anime.getStatistics();
        Assertions.assertNotNull(statistics.getCompleted());
        Assertions.assertNotNull(statistics.getDropped());
        Assertions.assertNotNull(statistics.getOnHold());
        Assertions.assertNotNull(statistics.getPlanToWatch());
        Assertions.assertNotNull(statistics.getWatching());
        Assertions.assertNotNull(statistics.getUserCount());
    }

}
