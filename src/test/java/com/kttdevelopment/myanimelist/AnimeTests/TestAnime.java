package com.kttdevelopment.myanimelist.AnimeTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatistics;
import com.kttdevelopment.myanimelist.manga.RelatedManga;
import org.junit.jupiter.api.*;

public class TestAnime {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static Anime anime;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        anime = mal.getAnime(TestProvider.AltAnimeID, MyAnimeList.ALL_ANIME_FIELDS);
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
        Assertions.assertNotEquals(-1, anime.getStartDate());
        Assertions.assertNotEquals(-1, anime.getEndDate());
        Assertions.assertNotNull(anime.getSynopsis());
        Assertions.assertNotEquals(-1, anime.getMeanRating());
        Assertions.assertNotEquals(-1, anime.getRank());
        Assertions.assertNotEquals(-1, anime.getPopularity());
        Assertions.assertNotEquals(-1, anime.getUserListingCount());
        Assertions.assertNotEquals(-1, anime.getUserScoringCount());
        Assertions.assertNotNull(anime.getNSFW());
        Assertions.assertNotEquals(-1, anime.getGenres()[0].getId());
        Assertions.assertNotNull(anime.getGenres()[0].getName());
        Assertions.assertNotEquals(-1, anime.getCreatedAt());
        Assertions.assertNotEquals(-1, anime.getUpdatedAt());
        Assertions.assertNotNull(anime.getType());
        Assertions.assertNotNull(anime.getStatus());
        Assertions.assertNotEquals(-1, anime.getEpisodes());
        Assertions.assertNotNull(anime.getStartSeason().getSeason());
        Assertions.assertNotEquals(-1, anime.getStartSeason().getYear());
        Assertions.assertNotNull(anime.getBroadcast().getDayOfWeek());
        Assertions.assertNotNull(anime.getBroadcast().getStartTime());
        Assertions.assertNotNull(anime.getSource());
        Assertions.assertNotEquals(-1, anime.getAverageEpisodeLength());
        Assertions.assertNotNull(anime.getRating());
        Assertions.assertNotEquals(-1, anime.getStudios()[0].getID());
        Assertions.assertNotNull(anime.getStudios()[0].getName());
        Assertions.assertNotNull(anime.getPictures()[0].getMediumURL());
        Assertions.assertNotNull(anime.getPictures()[0].getLargeURL());
        Assertions.assertNotNull(anime.getBackground());
    }

    @Test
    public void testRelatedAnime(){
        final RelatedAnime relatedAnime = anime.getRelatedAnime()[0];
        Assertions.assertNotEquals(-1, relatedAnime.getAnimePreview().getID());
        Assertions.assertNotNull(relatedAnime.getRelationType());
        Assertions.assertNotNull(relatedAnime.getRelationTypeFormat());
    }

    @Test @DisplayName("Anime may not have related Manga") @Disabled
    public void testRelatedManga(){
        final RelatedManga relatedManga = anime.getRelatedManga()[0];
        Assertions.assertNotEquals(-1, relatedManga.getMangaPreview().getID());
        Assertions.assertNotNull(relatedManga.getRelationType());
        Assertions.assertNotNull(relatedManga.getRelationTypeFormat());
    }

    @Test
    public void testRecommendations(){
        final AnimeRecommendation recommendation = anime.getRecommendations()[0];
        Assertions.assertNotEquals(-1, recommendation.getAnimePreview().getID());
        Assertions.assertNotEquals(-1, recommendation.getRecommendations());
    }

    @Test
    public void testStatistics(){
        final AnimeStatistics statistics = anime.getStatistics();
        Assertions.assertNotEquals(-1, statistics.getCompleted());
        Assertions.assertNotEquals(-1, statistics.getDropped());
        Assertions.assertNotEquals(-1, statistics.getOnHold());
        Assertions.assertNotEquals(-1, statistics.getPlanToWatch());
        Assertions.assertNotEquals(-1, statistics.getWatching());
        Assertions.assertNotEquals(-1, statistics.getUserCount());
    }

}
