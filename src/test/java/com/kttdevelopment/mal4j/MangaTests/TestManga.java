package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.anime.*;
import com.kttdevelopment.mal4j.manga.*;
import org.junit.jupiter.api.*;

public class TestManga {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static Manga manga;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        manga = mal.getManga(TestProvider.MangaID);
    }

    @Test
    public void testManga(){
        Assertions.assertEquals(manga, manga.getManga());

        Assertions.assertEquals(TestProvider.MangaID, manga.getID());
        Assertions.assertNotNull(manga.getTitle());
        Assertions.assertNotNull(manga.getMainPicture().getMediumURL());
        Assertions.assertNotNull(manga.getMainPicture().getLargeURL());
        Assertions.assertNotNull(manga.getAlternativeTitles().getEnglish());
        Assertions.assertNotNull(manga.getAlternativeTitles().getJapanese());
        Assertions.assertNotNull(manga.getAlternativeTitles().getSynonyms());
        Assertions.assertNotNull(manga.getStartDate());
        Assertions.assertNotNull(manga.getEndDate());
        Assertions.assertNotNull(manga.getSynopsis());
        Assertions.assertNotNull(manga.getMeanRating());
        Assertions.assertNotNull(manga.getRank());
        Assertions.assertNotNull(manga.getPopularity());
        Assertions.assertNotNull(manga.getUserListingCount());
        Assertions.assertNotNull(manga.getUserScoringCount());
        Assertions.assertNotNull(manga.getNSFW());
        Assertions.assertNotNull(manga.getGenres()[0]);
        Assertions.assertNotNull(manga.getGenres()[0].getName());
        Assertions.assertNotNull(manga.getCreatedAt());
        Assertions.assertNotNull(manga.getCreatedAtEpochMillis());
        Assertions.assertNotNull(manga.getUpdatedAt());
        Assertions.assertNotNull(manga.getUpdatedAtEpochMillis());
        Assertions.assertNotNull(manga.getType());
        Assertions.assertNotNull(manga.getStatus());
        Assertions.assertNotNull(manga.getVolumes());
        Assertions.assertNotNull(manga.getChapters());
        Assertions.assertNotNull(manga.getAuthors()[0].getID());
        Assertions.assertNotNull(manga.getAuthors()[0].getFirstName());
        Assertions.assertNotNull(manga.getAuthors()[0].getLastName());
        Assertions.assertNotNull(manga.getAuthors()[0].getRole());
        Assertions.assertNotNull(manga.getPictures()[0].getMediumURL());
        Assertions.assertNotNull(manga.getPictures()[0].getLargeURL());
        Assertions.assertNotNull(manga.getBackground());
    }

    @Test @DisplayName("Manga may not have related Anime") @Disabled
    public void testRelatedAnime(){
        final RelatedAnime relatedAnime = manga.getRelatedAnime()[0];
        Assertions.assertNotNull(relatedAnime.getAnimePreview().getID());
        Assertions.assertNotNull(relatedAnime.getRelationType());
        Assertions.assertNotNull(relatedAnime.getRelationTypeFormat());
    }

    @Test
    public void testRelatedManga(){
        final RelatedManga relatedManga = manga.getRelatedManga()[0];
        Assertions.assertNotNull(relatedManga.getMangaPreview().getID());
        Assertions.assertNotNull(relatedManga.getRelationType());
        Assertions.assertNotNull(relatedManga.getRelationTypeFormat());
    }

    @Test
    public void testRecommendations(){
        final MangaRecommendation recommendation = manga.getRecommendations()[0];
        Assertions.assertNotNull(recommendation.getMangaPreview().getID());
        Assertions.assertNotNull(recommendation.getRecommendations());
    }

    @Test
    public void testStatistics(){
        Assertions.assertNotNull(manga.getSerialization()[0].getID());
        Assertions.assertNotNull(manga.getSerialization()[0].getName());
    }

    @Test
    public void testSerialization(){
        Assertions.assertNotNull(manga.getSerialization()[0].getID());
        Assertions.assertNotNull(manga.getSerialization()[0].getName());
    }
    
    @Test @DisplayName("#4 - Serialization") @Disabled
    public void testSerializationRole(){
        Assertions.assertNotNull(manga.getSerialization()[0].getRole());
    }

}
