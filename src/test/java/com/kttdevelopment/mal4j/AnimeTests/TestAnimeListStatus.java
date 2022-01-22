package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import com.kttdevelopment.mal4j.anime.property.AnimeStatus;
import com.kttdevelopment.mal4j.anime.property.RewatchValue;
import com.kttdevelopment.mal4j.property.Genre;
import com.kttdevelopment.mal4j.property.Priority;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
final class TestAnimeListStatus {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll() throws IOException{
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();

        final String file = "anime-list-" + System.currentTimeMillis() + ".txt";
        System.out.println("Running Anime list tests, saving backup of current list to '" + file + '\'');
        Files.write(new File(file).toPath(), mal.getAnime(TestProvider.AnimeID).getListStatus().toString().getBytes(StandardCharsets.UTF_8));
    }

    @AfterAll
    static void afterAll(){
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

        annotateTest(() -> assertEquals(AnimeStatus.Completed, status.getStatus()));
        annotateTest(() -> assertEquals(10, status.getScore()));
        annotateTest(() -> assertEquals(24, status.getWatchedEpisodes()));
        annotateTest(() -> assertFalse(status.isRewatching()));
        annotateTest(() -> assertEquals(Priority.Low, status.getPriority()));
        annotateTest(() -> assertEquals(0, status.getTimesRewatched()));
        annotateTest(() -> assertEquals(RewatchValue.None, status.getRewatchValue()));
        annotateTest(() -> assertEquals(0, status.getTags().length));
        annotateTest(() -> assertEquals("", status.getComments()));
    }

    @Test @Order(0)
    final void testMinimalUpdate(){
        annotateTest(() -> assertDoesNotThrow(() -> mal.updateAnimeListing(TestProvider.AnimeID).score(10).update()));
    }

    @Test @Order(1)
    final void testDelete(){
        mal.deleteAnimeListing(TestProvider.AnimeID);
        annotateTest(() -> assertDoesNotThrow(() -> mal.deleteAnimeListing(TestProvider.AnimeID)));
        annotateTest(() -> assertNull(mal.getAnime(TestProvider.AnimeID, Fields.Anime.my_list_status).getListStatus().getUpdatedAtEpochMillis()));
    }

    private static boolean passedUpdate = false;
    @Test @Order(2)
    final void testUpdate(){
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
    final void testGet(){
        annotateTest(() -> assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)"));

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

        annotateTest(() -> fail("Anime list status not found"));
    }

    @Test @Order(3)
    final void testGetUsername(){
        annotateTest(() -> assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)"));

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
        annotateTest(() -> fail("User Anime list status not found"));
    }

    @Test @Order(3)
    final void testGetFromAnime(){
        annotateTest(() -> assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)"));

        final AnimeListStatus status = mal
            .getAnime(TestProvider.AnimeID, Fields.Anime.my_list_status)
            .getListStatus();
        testStatus(status);
    }

    private void testStatus(final AnimeListStatus status){
        annotateTest(() -> assertEquals(AnimeStatus.Completed, status.getStatus()));
        annotateTest(() -> assertEquals(10, status.getScore()));
        annotateTest(() -> assertEquals(24, status.getWatchedEpisodes()));
        annotateTest(() -> assertTrue(status.isRewatching()));
        annotateTest(() -> assertNotNull(status.getStartDate()));
        annotateTest(() -> assertNotNull(status.getFinishDate()));
        annotateTest(() -> assertEquals(Priority.High, status.getPriority()));
        annotateTest(() -> assertEquals(1, status.getTimesRewatched()));
        annotateTest(() -> assertEquals(RewatchValue.VeryHigh, status.getRewatchValue()));
        annotateTest(() -> assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[0])));
        annotateTest(() -> assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[1])));
        annotateTest(() -> assertEquals(TestProvider.testComment, status.getComments()));
        annotateTest(() -> assertNotNull(status.getUpdatedAt()));
        annotateTest(() -> assertNotNull(status.getUpdatedAtEpochMillis()));
    }

    @Test @Order(4)
    final void testConsecutiveUpdates(){
        testDelete();
        testUpdate();
        testUpdate();
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test @Order(5)
    final void testEcchiNSFW(){
        annotateTest(() -> assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)"));

        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withLimit(1000)
                .withFields(Fields.Anime.list_status)
                .withFields(Fields.Anime.genres)
                .search();

        for(final AnimeListStatus status : list)
            if(status.getAnimePreview().getGenres() != null)
                for(final Genre genre : status.getAnimePreview().getGenres())
                    if(genre.getName().equalsIgnoreCase("ecchi"))
                        return;

        annotateTest(() -> fail("Failed to find Anime with Ecchi genre"));
    }

}
