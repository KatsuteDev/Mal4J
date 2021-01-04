package com.kttdevelopment.myanimelist.MangaTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.manga.*;
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
        Assertions.assertNotEquals(-1, manga.getStartDate());
        Assertions.assertNotEquals(-1, manga.getEndDate());
        Assertions.assertNotNull(manga.getSynopsis());
        Assertions.assertNotEquals(-1, manga.getMeanRating());
        Assertions.assertNotEquals(-1, manga.getRank());
        Assertions.assertNotEquals(-1, manga.getPopularity());
        Assertions.assertNotEquals(-1, manga.getUserListingCount());
        Assertions.assertNotEquals(-1, manga.getUserScoringCount());
        Assertions.assertNotNull(manga.getNSFW());
        Assertions.assertNotEquals(-1, manga.getGenres()[0].getId());
        Assertions.assertNotNull(manga.getGenres()[0].getName());
        Assertions.assertNotEquals(-1, manga.getCreatedAt());
        Assertions.assertNotEquals(-1, manga.getUpdatedAt());
        Assertions.assertNotNull(manga.getType());
        Assertions.assertNotNull(manga.getStatus());
        Assertions.assertNotEquals(-1, manga.getVolumes());
        Assertions.assertNotEquals(-1, manga.getChapters());
        Assertions.assertNotEquals(-1, manga.getAuthors()[0].getID());
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
        Assertions.assertNotEquals(-1, relatedAnime.getAnimePreview().getID());
        Assertions.assertNotNull(relatedAnime.getRelationType());
        Assertions.assertNotNull(relatedAnime.getRelationTypeFormat());
    }

    @Test
    public void testRelatedManga(){
        final RelatedManga relatedManga = manga.getRelatedManga()[0];
        Assertions.assertNotEquals(-1, relatedManga.getMangaPreview().getID());
        Assertions.assertNotNull(relatedManga.getRelationType());
        Assertions.assertNotNull(relatedManga.getRelationTypeFormat());
    }

    @Test
    public void testRecommendations(){
        final MangaRecommendation recommendation = manga.getRecommendations()[0];
        Assertions.assertNotEquals(-1, recommendation.getMangaPreview().getID());
        Assertions.assertNotEquals(-1, recommendation.getRecommendations());
    }

    @Test
    public void testStatistics(){
        Assertions.assertNotEquals(-1, manga.getSerialization()[0].getID());
        Assertions.assertNotNull(manga.getSerialization()[0].getName());
    }

    @Test
    public void testSerialization(){
        Assertions.assertNotEquals(-1, manga.getSerialization()[0].getID());
        Assertions.assertNotNull(manga.getSerialization()[0].getName());
    }
    
    @Test @DisplayName("#4 - Serialization") @Disabled
    public void testSerializationRole(){
        Assertions.assertNotNull(manga.getSerialization()[0].getRole());
    }

}
