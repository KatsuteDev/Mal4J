package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import com.kttdevelopment.mal4j.anime.property.AnimeStatus;
import com.kttdevelopment.mal4j.property.Priority;
import com.kttdevelopment.mal4j.anime.property.RewatchValue;
import org.junit.jupiter.api.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAnimeListStatus {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @AfterAll
    public static void afterAll(){
        if(mal == null) return;

        mal.deleteAnimeListing(TestProvider.AnimeID);

        if(mal.getMyself().getID() != 8316239) return;

        final AnimeListStatus status = mal.updateAnimeListing(TestProvider.AnimeID)
            .status(AnimeStatus.Completed)
            .score(10)
            .episodesWatched(24)
            .rewatching(false)
            .priority(Priority.Low)
            .timesRewatched(0)
            .rewatchValue(RewatchValue.None)
            .tags("")
            .comments("")
            .update();

        Assertions.assertEquals(AnimeStatus.Completed, status.getStatus());
        Assertions.assertEquals(10, status.getScore());
        Assertions.assertEquals(24, status.getWatchedEpisodes());
        Assertions.assertFalse(status.isRewatching());
        Assertions.assertEquals(Priority.Low, status.getPriority());
        Assertions.assertEquals(0, status.getTimesRewatched());
        Assertions.assertEquals(RewatchValue.None, status.getRewatchValue());
        Assertions.assertEquals(0, status.getTags().length);
        Assertions.assertEquals("", status.getComments());
    }

    @Test @Order(1)
    public void testDelete(){
        mal.deleteAnimeListing(TestProvider.AnimeID);
        Assertions.assertDoesNotThrow(() -> mal.deleteAnimeListing(TestProvider.AnimeID));
        Assertions.assertNull(mal.getAnime(TestProvider.AnimeID, Fields.Anime.my_list_status).getListStatus().getUpdatedAtEpochMillis());
    }

    @Test @Order(2)
    public void testUpdate(){
        final Date now = new Date();
        final AnimeListStatus status = mal.updateAnimeListing(TestProvider.AnimeID)
            .status(AnimeStatus.Completed)
            .score(10)
            .startDate(now)
            .finishDate(now)
            .episodesWatched(24)
            .rewatching(true)
            .priority(Priority.High)
            .timesRewatched(0)
            .rewatchValue(RewatchValue.VeryHigh)
            .tags(TestProvider.testTags())
            .comments(TestProvider.testComment)
            .update();

        testStatus(status);
    }

    @Test @Order(3)
    public void testGet(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withStatus(AnimeStatus.Watching)
                .withLimit(1000)
                .withFields(Fields.Anime.list_status)
                .includeNSFW()
                .search();

        for(final AnimeListStatus listStatus : list)
            if(listStatus.getAnimePreview().getID() == TestProvider.AnimeID){
                testStatus(listStatus);
                return;
            }
        Assertions.fail("Anime list status not found");
    }

    @Test @Order(3)
    public void testGetUsername(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing("KatsuteDev")
                .withStatus(AnimeStatus.Watching)
                .withLimit(1000)
                .withFields(Fields.Anime.list_status)
                .includeNSFW()
                .search();

        for(final AnimeListStatus listStatus : list)
            if(listStatus.getAnimePreview().getID() == TestProvider.AnimeID){
                testStatus(listStatus);
                return;
            }
        Assertions.fail("User Anime list status not found");
    }

    @Test @Order(3)
    public void testGetFromAnime(){
        final AnimeListStatus status = mal
            .getAnime(TestProvider.AnimeID, Fields.Anime.my_list_status)
            .getListStatus();
        testStatus(status);
    }

    private void testStatus(final AnimeListStatus status){
        Assertions.assertEquals(AnimeStatus.Completed, status.getStatus());
        Assertions.assertEquals(10, status.getScore());
        Assertions.assertEquals(24, status.getWatchedEpisodes());
        Assertions.assertTrue(status.isRewatching());
        Assertions.assertNotNull(status.getStartDate());
        Assertions.assertNotNull(status.getFinishDate());
        Assertions.assertEquals(Priority.High, status.getPriority());
        Assertions.assertEquals(0, status.getTimesRewatched());
        Assertions.assertEquals(RewatchValue.VeryHigh, status.getRewatchValue());
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[0]));
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[1]));
        Assertions.assertEquals(TestProvider.testComment, status.getComments());
        Assertions.assertNotNull(status.getUpdatedAt());
        Assertions.assertNotNull(status.getUpdatedAtEpochMillis());
    }

    @Test @Order(4)
    public void testConsecutiveUpdates(){
        testDelete();
        testUpdate();
        testUpdate();
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test @Order(5) @DisplayName("testEcchiNSFW(), #90 - Ecchi as NSFW")
    public void testEcchiNSFW(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withLimit(1000)
                .withFields(Fields.Anime.list_status)
                .search();

        for(final AnimeListStatus listStatus : list)
            if(listStatus.getAnimePreview().getID() == TestProvider.AnimeID)
                return;
        Assertions.fail("Failed to find Anime with Ecchi genre (external issue, disregard fail)");
    }

}
