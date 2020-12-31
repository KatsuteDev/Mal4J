package com.kttdevelopment.myanimelist.AnimeTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.anime.AnimeListStatus;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAnimeListStatus {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @AfterAll
    public static void cleanup(){
        final AnimeListStatus status = mal.updateAnimeListing(TestProvider.AnimeID)
            .status(AnimeStatus.Completed)
            .score(10)
            .episodesWatched(24)
            .rewatching(false)
            .priority(0)
            .timesRewatched(0)
            .rewatchValue(0)
            .tags("")
            .comments("")
            .update();

        Assertions.assertEquals(AnimeStatus.Completed, status.getStatus());
        Assertions.assertEquals(10, status.getScore());
        Assertions.assertEquals(24, status.getWatchedEpisodes());
        Assertions.assertFalse(status.isRewatching());
        Assertions.assertEquals(0, status.getPriority());
        Assertions.assertEquals(0, status.getTimesRewatched());
        Assertions.assertEquals(0, status.getRewatchValue());
        Assertions.assertEquals(0, status.getTags().length);
        Assertions.assertEquals("", status.getComments());
    }

    @Test @Order(1)
    public void testDelete(){
        mal.deleteAnimeListing(TestProvider.AnimeID);
        Assertions.assertNull(mal.getAnime(TestProvider.AnimeID).getListStatus());
    }

    @Test @Order(2)
    public void testUpdate(){
        final AnimeListStatus status = mal.updateAnimeListing(TestProvider.AnimeID)
            .status(AnimeStatus.Completed)
            .score(10)
            .episodesWatched(24)
            .rewatching(true)
            .priority(2)
            .timesRewatched(0)
            .rewatchValue(5)
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
                .search();

        AnimeListStatus status = null;
        for(final AnimeListStatus userAnimeListStatus : list)
            if((status = userAnimeListStatus).getAnimePreview().getID() == TestProvider.AnimeID)
                break;
            else
                status = null;
        if(status == null)
            Assertions.fail();

        testStatus(status);
    }

    @Test @Order(3)
    public void testGetFromAnime(){
        final AnimeListStatus status = mal.getAnime(TestProvider.AnimeID).getListStatus();
        testStatus(status);
    }

    @SuppressWarnings("CommentedOutCode")
    private void testStatus(final AnimeListStatus status){
        Assertions.assertEquals(AnimeStatus.Completed, status.getStatus());
        Assertions.assertEquals(10, status.getScore());
        Assertions.assertEquals(24, status.getWatchedEpisodes());
        Assertions.assertTrue(status.isRewatching());
        // Assertions.assertNotEquals(-1, status.getStartDate()); // will fail (unable to set start date)
        // Assertions.assertNotEquals(-1, status.getFinishDate()); // will fail (unable to set finish date)
        Assertions.assertEquals(2, status.getPriority());
        Assertions.assertEquals(0, status.getTimesRewatched());
        Assertions.assertEquals(5, status.getRewatchValue());
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains("ignore"));
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains("tags"));
        Assertions.assertEquals("ignore comments", status.getComments());
        Assertions.assertNotEquals(-1, status.getUpdatedAt());
    }

}
