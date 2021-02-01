package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
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
    public static void cleanup(){
        TestProvider.testRequireClientID();

        mal.deleteAnimeListing(TestProvider.AnimeID);
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
        Assertions.assertNull(mal.getAnime(TestProvider.AnimeID).getListStatus().getUpdatedAtEpochMillis());
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
            .tags("ignore", "tags")
            .comments("ignore comments")
            .update();

        testStatus(status);
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test @Order(3) @DisplayName("testGet(), #25 - Rewatching status")
    public void testGet(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withStatus(AnimeStatus.Watching)
                .withLimit(1000)
                .includeNSFW(true)
                .search();

        AnimeListStatus status = null;
        for(final AnimeListStatus userAnimeListStatus : list)
            if((status = userAnimeListStatus).getAnimePreview().getID() == TestProvider.AnimeID)
                break;
            else
                status = null;
        Assertions.assertNotNull(status);

        testStatus(status);
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test @Order(3) @DisplayName("testGetUsername(), #25 - Rewatching status")
    public void testGetUsername(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing("KatsuteDev")
                .withStatus(AnimeStatus.Watching)
                .withLimit(1000)
                .search();

        AnimeListStatus status = null;
        for(final AnimeListStatus userAnimeListStatus : list)
            if((status = userAnimeListStatus).getAnimePreview().getID() == TestProvider.AnimeID)
                break;
            else
                status = null;
        Assertions.assertNotNull(status);

        testStatus(status);
    }

    @Test @Order(3)
    public void testGetFromAnime(){
        final AnimeListStatus status = mal
            .getAnime(TestProvider.AnimeID)
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
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains("ignore"));
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains("tags"));
        Assertions.assertEquals("ignore comments", status.getComments());
        Assertions.assertNotNull(status.getUpdatedAt());
        Assertions.assertNotNull(status.getUpdatedAtEpochMillis());
    }

    @Test @DisplayName("#30 Consecutive Updates") @Disabled
    public void testConsecutiveUpdates(){
        testDelete();
        testUpdate();
        testUpdate();
    }

}
