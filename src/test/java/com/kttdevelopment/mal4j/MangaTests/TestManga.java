package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.RelatedAnime;
import com.kttdevelopment.mal4j.manga.Manga;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

public class TestManga {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static Manga manga;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        manga = mal.getManga(TestProvider.MangaID, Fields.manga);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("mangaProvider")
    public void testManga(@SuppressWarnings("unused") final String method, final Function<Manga,Object> function){
        Assertions.assertNotNull(function.apply(manga));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> mangaProvider(){
        return new TestProvider.MethodStream<Manga>()
            .add("Title", Manga::getTitle)
            .add("MainPicture", Manga::getMainPicture)
            .add("MainPicture#MediumURL", manga -> manga.getMainPicture().getMediumURL())
            .add("MainPicture#LargeURL", manga -> manga.getMainPicture().getLargeURL())
            .add("AlternativeTitles", Manga::getAlternativeTitles)
            .add("AlternativeTitles#English", manga -> manga.getAlternativeTitles().getEnglish())
            .add("AlternativeTitles#Japanese", manga -> manga.getAlternativeTitles().getJapanese())
            .add("AlternativeTitles#Synonyms", manga -> manga.getAlternativeTitles().getSynonyms())
            .add("StartDate", Manga::getStartDate)
            .add("EndDate", Manga::getEndDate)
            .add("Synopsis", Manga::getSynopsis)
            .add("MeanRating", Manga::getMeanRating)
            .add("Rank", Manga::getRank)
            .add("Popularity", Manga::getPopularity)
            .add("UserListingCount", Manga::getUserListingCount)
            .add("UserScoringCount", Manga::getUserScoringCount)
            .add("NSFW", Manga::getNSFW)
            .add("Genres", Manga::getGenres)
            .add("Genres[0]", manga -> manga.getGenres()[0])
            .add("CreatedAt", Manga::getCreatedAt)
            .add("CreatedAtEpoch", Manga::getCreatedAtEpochMillis)
            .add("UpdatedAt", Manga::getUpdatedAt)
            .add("UpdatedAtEpoch", Manga::getUpdatedAtEpochMillis)
            .add("Type", Manga::getType)
            .add("Status", Manga::getStatus)
            .add("Volumes", Manga::getVolumes)
            .add("Chapters", Manga::getChapters)
            .add("Authors", Manga::getAuthors)
            .add("Authors[0]", manga -> manga.getAuthors()[0])
            .add("Authors#ID", manga -> manga.getAuthors()[0].getID())
            .add("Authors#FirstName", manga -> manga.getAuthors()[0].getFirstName())
            .add("Authors#LastName", manga -> manga.getAuthors()[0].getLastName())
            .add("Authors#Role", manga -> manga.getAuthors()[0].getRole())
            .add("Pictures", Manga::getPictures)
            .add("Pictures[0]", manga -> manga.getPictures()[0])
            .add("Pictures#MediumURL", manga -> manga.getPictures()[0].getMediumURL())
            .add("Pictures#LargeURL", manga -> manga.getPictures()[0].getLargeURL())
            .add("Background", Manga::getBackground)
            .add("Serialization", Manga::getSerialization)
            .add("Serialization[0]", manga -> manga.getSerialization()[0])
            .add("Serialization#ID", manga -> manga.getSerialization()[0].getID())
            .add("Serialization#Name", manga -> manga.getSerialization()[0].getName())
            .add("Serialization#Role", manga -> manga.getSerialization()[0].getRole())
            .add("RelatedManga", Manga::getRelatedManga)
            .add("RelatedManga[0]", manga -> manga.getRelatedManga()[0])
            .add("RelatedManga#MangaPreview", manga -> manga.getRelatedManga()[0].getMangaPreview().getID())
            .add("RelatedManga#RelationType", manga -> manga.getRelatedManga()[0].getRelationType())
            .add("RelatedManga#RelationTypeFormat", manga -> manga.getRelatedManga()[0].getRelationTypeFormat())
            .add("Recommendations", Manga::getRecommendations)
            .add("Recommendations[0]", manga -> manga.getRecommendations()[0])
            .add("Recommendations#MangaPreview", manga -> manga.getRecommendations()[0].getMangaPreview().getID())
            .add("Recommendations#Recommendations", manga -> manga.getRecommendations()[0].getRecommendations())
            .stream();
    }

    @Test
    public void testManga(){
        Assertions.assertEquals(manga, manga.getManga());
        Assertions.assertEquals(TestProvider.MangaID, manga.getID());
    }

    @Test
    public void testJapaneseEncoding(){
        Assertions.assertFalse(manga.getAlternativeTitles().getJapanese().startsWith("\\u"), "Japanese characters should not be returned as a literal \\u unicode string");
    }

    @Test @DisplayName("Manga may not have related Anime") @Disabled
    public void testRelatedAnime(){
        final RelatedAnime relatedAnime = manga.getRelatedAnime()[0];
        Assertions.assertNotNull(relatedAnime.getAnimePreview().getID());
        Assertions.assertNotNull(relatedAnime.getRelationType());
        Assertions.assertNotNull(relatedAnime.getRelationTypeFormat());
    }

}