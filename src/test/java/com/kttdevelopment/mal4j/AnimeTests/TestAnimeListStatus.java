package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import com.kttdevelopment.mal4j.anime.property.AnimeStatus;
import com.kttdevelopment.mal4j.anime.property.RewatchValue;
import com.kttdevelopment.mal4j.property.Genre;
import com.kttdevelopment.mal4j.property.Priority;
import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAnimeListStatus {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll() throws IOException{
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();

        final String file = "anime-list-" + System.currentTimeMillis() + ".txt";
        System.out.println("Running Anime list tests, saving backup of current list to '" + file + '\'');
        Files.write(new File(file).toPath(), mal.getAnime(TestProvider.AnimeID).getListStatus().toString().getBytes(StandardCharsets.UTF_8));
    }

    @SuppressWarnings("SpellCheckingInspection")
    @AfterAll
    public static void afterAll(){
        if(mal == null) return;
        TestProvider.requireToken();

        mal.deleteAnimeListing(TestProvider.AnimeID);

        if(mal.getAuthenticatedUser().getID() != 8316239) return;

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

        Assertions.assertEquals(AnimeStatus.Completed, status.getStatus(),
                                Workflow.errorSupplier("Expected status to match"));
        Assertions.assertEquals(10, status.getScore(),
                                Workflow.errorSupplier("Expected score to match"));
        Assertions.assertEquals(24, status.getWatchedEpisodes(),
                                Workflow.errorSupplier("Expected episodes to match"));
        Assertions.assertFalse(status.isRewatching(),
                               Workflow.errorSupplier("Expected rewatching to be false"));
        Assertions.assertEquals(Priority.Low, status.getPriority(),
                                Workflow.errorSupplier("Expected priority to match"));
        Assertions.assertEquals(0, status.getTimesRewatched(),
                                Workflow.errorSupplier("Expected times rewatched to match"));
        Assertions.assertEquals(RewatchValue.None, status.getRewatchValue(),
                                Workflow.errorSupplier("Expected rewatch value to match"));
        Assertions.assertEquals(0, status.getTags().length,
                                Workflow.errorSupplier("Expected tags to match"));
        Assertions.assertEquals("", status.getComments(),
                                Workflow.errorSupplier("Expected comments to match"));
    }

    @Test @Order(0)
    public void testMinimalUpdate(){
        Assertions.assertDoesNotThrow(() -> mal.updateAnimeListing(TestProvider.AnimeID).score(10).update(),
                                      Workflow.errorSupplier("Updating a listing with only a score should not throw an exception"));
    }

    @Test @Order(1)
    public void testDelete(){
        mal.deleteAnimeListing(TestProvider.AnimeID);
        Assertions.assertDoesNotThrow(() -> mal.deleteAnimeListing(TestProvider.AnimeID),
                                      Workflow.errorSupplier("Deleting a deleted listing should not throw an exception"));
        Assertions.assertNull(mal.getAnime(TestProvider.AnimeID, Fields.Anime.my_list_status).getListStatus().getUpdatedAtEpochMillis(),
                              Workflow.errorSupplier("Expected a deleted listing to be null"));
    }

    private static boolean passedUpdate = false;
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
            .timesRewatched(1)
            .rewatchValue(RewatchValue.VeryHigh)
            .tags(TestProvider.testTags())
            .comments(TestProvider.testComment)
            .update();

        testStatus(status);
        passedUpdate = true;
    }

    @Test @Order(3)
    public void testGet(){
        Assertions.assertTrue(passedUpdate,
                              Workflow.errorSupplier("Failed to start test (test requires update test to pass)"));

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
        Assertions.fail(Workflow.errorSupplier("Anime list status not found"));
    }

    @Test @Order(3)
    public void testGetUsername(){
        Assertions.assertTrue(passedUpdate,
                              Workflow.errorSupplier("Failed to start test (test requires update test to pass)"));

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
        Assertions.fail(Workflow.errorSupplier("User Anime list status not found"));
    }

    @Test @Order(3)
    public void testGetFromAnime(){
        Assertions.assertTrue(passedUpdate,
                              Workflow.errorSupplier("Failed to start test (test requires update test to pass)"));

        final AnimeListStatus status = mal
            .getAnime(TestProvider.AnimeID, Fields.Anime.my_list_status)
            .getListStatus();
        testStatus(status);
    }

    @SuppressWarnings("SpellCheckingInspection")
    private void testStatus(final AnimeListStatus status){
        Assertions.assertEquals(AnimeStatus.Completed, status.getStatus(),
                                Workflow.errorSupplier("Expected status to match"));
        Assertions.assertEquals(10, status.getScore(),
                                Workflow.errorSupplier("Expected score to match"));
        Assertions.assertEquals(24, status.getWatchedEpisodes(),
                                Workflow.errorSupplier(("Expected episodes watched to match")));
        Assertions.assertTrue(status.isRewatching(),
                              Workflow.errorSupplier("Expected rewatching to be true"));
        Assertions.assertNotNull(status.getStartDate(),
                                 Workflow.errorSupplier("Expected start date to not be null"));
        Assertions.assertNotNull(status.getFinishDate(),
                                 Workflow.errorSupplier("Expected finish date to not be null"));
        Assertions.assertEquals(Priority.High, status.getPriority(),
                                Workflow.errorSupplier("Expected priority to match"));
        Assertions.assertEquals(1, status.getTimesRewatched(),
                                Workflow.errorSupplier("Expected times rewatched to match"));
        Assertions.assertEquals(RewatchValue.VeryHigh, status.getRewatchValue(),
                                Workflow.errorSupplier("Expected rewatch value to match"));
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[0]),
                              Workflow.errorSupplier("Expected tags to match"));
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[1]),
                              Workflow.errorSupplier("Expected tags to match"));
        Assertions.assertEquals(TestProvider.testComment, status.getComments(),
                                Workflow.errorSupplier("Expected comment to match"));
        Assertions.assertNotNull(status.getUpdatedAt(),
                                 Workflow.errorSupplier("Expected updated at to not be null"));
        Assertions.assertNotNull(status.getUpdatedAtEpochMillis(),
                                 Workflow.errorSupplier("Expected updated at millis to not be null"));
    }

    @Test @Order(4)
    public void testConsecutiveUpdates(){
        testDelete();
        testUpdate();
        testUpdate();
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test @Order(5)
    public void testEcchiNSFW(){
        Assertions.assertTrue(passedUpdate,
                              Workflow.errorSupplier("Failed to start test (test requires update test to pass)"));

        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withLimit(1000)
                .withFields(Fields.Anime.list_status)
                .withFields(Fields.Anime.genres)
                .search();

        for(final AnimeListStatus status : list)
            for(final Genre genre : status.getAnimePreview().getGenres())
                if(genre.getName().equalsIgnoreCase("ecchi"))
                    return;

        Assertions.fail(Workflow.errorSupplier("Failed to find Anime with Ecchi genre"));
    }

}
