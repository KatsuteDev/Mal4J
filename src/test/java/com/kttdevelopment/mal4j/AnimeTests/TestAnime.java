package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.manga.RelatedManga;
import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

public class TestAnime {

    private static MyAnimeList mal;
    private static Anime anime;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        anime = mal.getAnime(TestProvider.AltAnimeID, Fields.anime);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("animeProvider")
    public void testAnime(@SuppressWarnings("unused") final String method, final Function<Anime,Object> function){
        Assertions.assertNotNull(function.apply(anime),
                                 Workflow.errorSupplier("Expected Anime#" + method + " to not be null"));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> animeProvider(){
        return new TestProvider.MethodStream<Anime>()
            .add("Title", Anime::getTitle)
            .add("MainPicture", Anime::getMainPicture)
            .add("MainPicture#MediumURL", anime -> anime.getMainPicture().getMediumURL())
            .add("MainPicture#LargeURL", anime -> anime.getMainPicture().getLargeURL())
            .add("AlternativeTitles", Anime::getAlternativeTitles)
            .add("AlternativeTitles#English", anime -> anime.getAlternativeTitles().getEnglish())
            .add("AlternativeTitles#Japanese", anime -> anime.getAlternativeTitles().getJapanese())
            .add("AlternativeTitles#Synonyms", anime -> anime.getAlternativeTitles().getSynonyms())
            .add("StartDate", Anime::getStartDate)
            .add("EndDate", Anime::getEndDate)
            .add("Synopsis", Anime::getSynopsis)
            .add("MeanRating", Anime::getMeanRating)
            .add("Rank", Anime::getRank)
            .add("Popularity", Anime::getPopularity)
            .add("UserListingCount", Anime::getUserListingCount)
            .add("UserScoringCount", Anime::getUserScoringCount)
            .add("NSFW", Anime::getNSFW)
            .add("Genres", Anime::getGenres)
            .add("Genres[0]", anime -> anime.getGenres()[0])
            .add("Genres[0]#ID", anime -> anime.getGenres()[0].getID())
            .add("Genres[0]#Name", anime -> anime.getGenres()[0].getName())
            .add("Genres[0]#isAnimeGenre", anime -> anime.getGenres()[0].isAnimeGenre())
            .add("Genres[0]#isMangaGenre", anime -> anime.getGenres()[0].isMangaGenre())
            .add("CreatedAt", Anime::getCreatedAt)
            .add("CreatedAtEpoch", Anime::getCreatedAtEpochMillis)
            .add("UpdatedAt", Anime::getUpdatedAt)
            .add("UpdatedAtEpoch", Anime::getUpdatedAtEpochMillis)
            .add("Type", Anime::getType)
            .add("Status", Anime::getStatus)
            .add("Episodes", Anime::getEpisodes)
            .add("StartSeason", Anime::getStartSeason)
            .add("StartSeason#Season", anime -> anime.getStartSeason().getSeason())
            .add("StartSeason#Year", anime -> anime.getStartSeason().getYear())
            .add("Broadcast", Anime::getBroadcast)
            .add("Broadcast#DayOfWeek", anime -> anime.getBroadcast().getDayOfWeek())
            .add("Broadcast#StartTime", anime -> anime.getBroadcast().getStartTime())
            .add("Source", Anime::getSource)
            .add("AverageEpisodeLen", Anime::getAverageEpisodeLength)
            .add("Rating", Anime::getRating)
            .add("Studios", Anime::getStudios)
            .add("Studios[0]", anime -> anime.getStudios()[0])
            .add("Studios#ID", anime -> anime.getStudios()[0].getID())
            .add("Studios#Name", anime -> anime.getStudios()[0].getName())
            .add("Pictures", Anime::getPictures)
            .add("Pictures[0]", anime -> anime.getPictures()[0])
            .add("Pictures#MediumURL", anime -> anime.getPictures()[0].getMediumURL())
            .add("Pictures#LargeURL", anime -> anime.getPictures()[0].getLargeURL())
            .add("Background", Anime::getBackground)
            .add("OpeningThemes", Anime::getOpeningThemes)
            .add("OpeningThemes[0]", anime -> anime.getOpeningThemes()[0])
            .add("OpeningThemes#ID", anime -> anime.getOpeningThemes()[0].getID())
            .add("OpeningThemes#Text", anime -> anime.getOpeningThemes()[0].getText())
            .add("EndingThemes", Anime::getEndingThemes)
            .add("EndingThemes[0]", anime -> anime.getEndingThemes()[0])
            .add("EndingThemes#ID", anime -> anime.getEndingThemes()[0].getID())
            .add("EndingThemes#Text", anime -> anime.getEndingThemes()[0].getText())
            .add("RelatedAnime", Anime::getRelatedAnime)
            .add("RelatedAnime[0]", anime -> anime.getRelatedAnime()[0])
            .add("RelatedAnime#AnimePreview", anime -> anime.getRelatedAnime()[0].getAnimePreview().getID())
            .add("RelatedAnime#RelationType", anime -> anime.getRelatedAnime()[0].getRelationType())
            .add("RelatedAnime#RelationTypeFormat", anime -> anime.getRelatedAnime()[0].getRelationTypeFormat())
            .add("Recommendations", Anime::getRecommendations)
            .add("Recommendations[0]", anime -> anime.getRecommendations()[0])
            .add("Recommendations#AnimePreview", anime -> anime.getRecommendations()[0].getAnimePreview().getID())
            .add("Recommendations#Recommendations", anime -> anime.getRecommendations()[0].getRecommendations())
            .add("Statistics", Anime::getStatistics)
            .add("Statistics#Completed", anime -> anime.getStatistics().getCompleted())
            .add("Statistics#Dropped", anime -> anime.getStatistics().getDropped())
            .add("Statistics#OnHold", anime -> anime.getStatistics().getOnHold())
            .add("Statistics#PTW", anime -> anime.getStatistics().getPlanToWatch())
            .add("Statistics#Watching", anime -> anime.getStatistics().getWatching())
            .add("Statistics#UserCount", anime -> anime.getStatistics().getUserCount())
            .stream();
    }

    @Test
    public void testAnime(){
        Assertions.assertEquals(anime, anime.getAnime(),
                                Workflow.errorSupplier("Expected Anime#getAnime() to return self reference"));
        Assertions.assertEquals(TestProvider.AltAnimeID, anime.getID(),
                                Workflow.errorSupplier("Expected Anime#getID() to match test ID"));
    }

    @Test
    public void testOpEdReference(){
        Assertions.assertSame(anime.getOpeningThemes()[0].getAnime(), anime,
                              Workflow.errorSupplier("Expected Anime#getOpeningThemes#getAnime() to return self reference"));
        Assertions.assertSame(anime.getEndingThemes()[0].getAnime(), anime,
                              Workflow.errorSupplier("Expected Anime#getOpeningThemes#getAnime() to return self reference"));
    }

    @Test
    public void testJapaneseEncoding(){
        Assertions.assertFalse(anime.getAlternativeTitles().getJapanese().startsWith("\\u"),
                               Workflow.errorSupplier("Japanese characters should not be returned as a literal \\u unicode string"));
    }

    @Test
    public void testFields(){
        final Anime anime = mal.getAnime(TestProvider.AnimeID, Fields.Anime.episodes);
        Assertions.assertNotNull(anime.getEpisodes(),
                                 Workflow.errorSupplier("Expected field to not be null"));
        Assertions.assertNull(anime.getRating(),
                              Workflow.errorSupplier("Expected field to be null"));
    }

    @Test
    public void testInvertedFields(){
        final Anime anime = mal.getAnime(TestProvider.AnimeID, Fields.Anime.episodes, Fields.INVERTED);
        Assertions.assertNull(anime.getEpisodes(),
                              Workflow.errorSupplier("Expected field to be null"));
        Assertions.assertNotNull(anime.getRating(),
                                 Workflow.errorSupplier("Expected field to not be null"));
    }

    @Test
    public void testInvertedFieldsOnly(){
        final Anime manga = mal.getAnime(TestProvider.AnimeID, Fields.INVERTED);
        Assertions.assertNotNull(manga.getEpisodes(),
                                 Workflow.errorSupplier("Expected field to not be null"));
    }

    @Test @DisplayName("Anime may not have related Manga") @Disabled
    public void testRelatedManga(){
        final RelatedManga relatedManga = anime.getRelatedManga()[0];
        Assertions.assertNotNull(relatedManga.getMangaPreview().getID(),
                                 Workflow.errorSupplier("Expected Anime#getRelatedManga#getMangaPreview#getID to not be null"));
        Assertions.assertNotNull(relatedManga.getRelationType(),
                                 Workflow.errorSupplier("Expected Anime#getRelatedManga#getRelationType to not be null"));
        Assertions.assertNotNull(relatedManga.getRelationTypeFormat(),
                                 Workflow.errorSupplier("Expected Anime#getRelatedManga#getRelationTypeFormat to not be null"));
    }

}