package dev.katsute.mal4j.MangaTests;

import dev.katsute.mal4j.Fields;
import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.manga.Manga;
import dev.katsute.mal4j.manga.property.MangaPublishStatus;
import dev.katsute.mal4j.manga.property.MangaType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

final class TestManga {

    private static MyAnimeList mal;
    private static Manga manga;

    @SuppressWarnings({"ConstantConditions", "RedundantSuppression"})
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        manga = mal.getManga(TestProvider.MangaID, Fields.manga);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("mangaProvider")
    final void testManga(@SuppressWarnings("unused") final String method, final Function<Manga,Object> function){
        if(!method.equals("Serialization#Role"))
            assertNotNull(function.apply(manga), "Expected Manga#" + method + " to not be null");
        else
            assumeTrue(function.apply(manga) != null, "Expected Manga#" + method + " to not be null (external issue)");
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
            .add("StartDate#Year", manga -> manga.getStartDate().getYear())
            .add("StartDate#Month", manga -> manga.getStartDate().getMonth())
            .add("StartDate#Day", manga -> manga.getStartDate().getDay())
            .add("StartDate#Millis", manga -> manga.getStartDate().getMillis())
            .add("StartDate#Date", manga -> manga.getStartDate().getDate())
            .add("EndDate", Manga::getEndDate)
            .add("EndDate#Year", manga -> manga.getEndDate().getYear())
            .add("EndDate#Month", manga -> manga.getEndDate().getMonth())
            .add("EndDate#Day", manga -> manga.getEndDate().getDay())
            .add("EndDate#Millis", manga -> manga.getEndDate().getMillis())
            .add("EndDate#Date", manga -> manga.getEndDate().getDate())
            .add("Synopsis", Manga::getSynopsis)
            .add("MeanRating", Manga::getMeanRating)
            .add("Rank", Manga::getRank)
            .add("Popularity", Manga::getPopularity)
            .add("UserListingCount", Manga::getUserListingCount)
            .add("UserScoringCount", Manga::getUserScoringCount)
            .add("NSFW", Manga::getNSFW)
            .add("RawNSFW", Manga::getRawNSFW)
            .add("Genres", Manga::getGenres)
            .add("Genres[0]", manga -> manga.getGenres()[0])
            .add("Genres[0]#ID", manga -> manga.getGenres()[0].getID())
            .add("Genres[0]#Name", manga -> manga.getGenres()[0].getName())
            .add("Genres[0]#isAnimeGenre", manga -> manga.getGenres()[0].isAnimeGenre())
            .add("Genres[0]#isMangaGenre", manga -> manga.getGenres()[0].isMangaGenre())
            .add("CreatedAt", Manga::getCreatedAt)
            .add("CreatedAtEpoch", Manga::getCreatedAtEpochMillis)
            .add("UpdatedAt", Manga::getUpdatedAt)
            .add("UpdatedAtEpoch", Manga::getUpdatedAtEpochMillis)
            .add("Type", Manga::getType)
            .add("RawType", Manga::getRawType)
            .add("Status", Manga::getStatus)
            .add("RawStatus", Manga::getRawStatus)
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
            .add("RelatedManga#MangaPreview", manga -> manga.getRelatedManga()[0].getManga().getID())
            .add("RelatedManga#RelationType", manga -> manga.getRelatedManga()[0].getRelationType())
            .add("RelatedManga#RawRelationType", manga -> manga.getRelatedManga()[0].getRawRelationType())
            .add("RelatedManga#RelationTypeFormat", manga -> manga.getRelatedManga()[0].getRelationTypeFormat())
            .add("Recommendations", Manga::getRecommendations)
            .add("Recommendations[0]", manga -> manga.getRecommendations()[0])
            .add("Recommendations#MangaPreview", manga -> manga.getRecommendations()[0].getManga().getID())
            .add("Recommendations#Recommendations", manga -> manga.getRecommendations()[0].getRecommendations())
            .stream();
    }

    @Test
    final void testManga(){
        assertEquals(TestProvider.MangaID, manga.getID());
    }

    @Test
    final void testJapaneseEncoding(){
        assertFalse(manga.getAlternativeTitles().getJapanese().startsWith("\\u"));
    }

    @Test
    final void testFields(){
        final Manga manga = mal.getManga(TestProvider.MangaID, Fields.Manga.volumes);
        assertFalse(manga.toString().contains(", volumes=null,"));
        assertTrue(manga.toString().contains(", chapters=null,"));
    }

    @Test
    final void testInvertedFields(){
        final Manga manga = mal.getManga(TestProvider.MangaID, Fields.Manga.volumes, Fields.Manga.list_status, Fields.Manga.related_manga, Fields.Manga.recommendations, Fields.INVERTED);
        assertTrue(manga.toString().contains(", volumes=null,"));
        assertFalse(manga.toString().contains(", chapters=null,"));
    }

    @Test
    final void testEnum(){
        assertEquals(MangaPublishStatus.Unknown, MangaPublishStatus.asEnum("?"));
        assertEquals(MangaType.Unknown, MangaType.asEnum("?"));

        assumeTrue(MangaPublishStatus.OnHiatus == mal.getManga(2).getStatus(), "Test will fail when status is not hiatus");
    }

    @Test
    final void testPartialRecommendation(){
        final Manga recommendation = manga.getRecommendations()[0].getManga();
        final int was = recommendation.toString().length();
        recommendation.getBackground();
        assertTrue(recommendation.toString().length() > was);
    }

    @Test
    final void testPartialRelated(){
        final Manga related = manga.getRelatedManga()[0].getManga();
        final int was = related.toString().length();
        related.getBackground();
        assertTrue(related.toString().length() > was);
    }

}