package dev.katsute.mal4j.AnimeTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.anime.AnimeListStatus;
import dev.katsute.mal4j.anime.property.AnimeStatus;
import dev.katsute.mal4j.anime.property.RewatchValue;
import dev.katsute.mal4j.property.Genre;
import dev.katsute.mal4j.property.Priority;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

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

        assertEquals(AnimeStatus.Completed, status.getStatus());
        assertEquals(10, status.getScore());
        assertEquals(24, status.getWatchedEpisodes());
        assertFalse(status.isRewatching());
        assertEquals(Priority.Low, status.getPriority());
        assertEquals(0, status.getTimesRewatched());
        assertEquals(RewatchValue.None, status.getRewatchValue());
        assertEquals(0, status.getTags().length);
        assertEquals("", status.getComments());
    }

    @Test @Order(0)
    final void testMinimalUpdate(){
        assertDoesNotThrow(() -> mal.updateAnimeListing(TestProvider.AnimeID).score(10).update());
    }

    @Test @Order(1)
    final void testDelete(){
        mal.deleteAnimeListing(TestProvider.AnimeID);
        assertDoesNotThrow(() -> mal.deleteAnimeListing(TestProvider.AnimeID));
        assertNull(mal.getAnime(TestProvider.AnimeID, Fields.Anime.my_list_status).getListStatus().getUpdatedAtEpochMillis());
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
        assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withStatus(AnimeStatus.Watching)
                .withLimit(1000)
                .withFields(Fields.Anime.list_status)
                .includeNSFW()
                .search();

        for(final AnimeListStatus listStatus : list)
            if(listStatus.getAnime().getID() == TestProvider.AnimeID){
                testStatus(listStatus);
                return;
            }

        fail("Anime list status not found");
    }

    @Test @Order(3)
    final void testGetUsername(){
        assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final List<AnimeListStatus> list =
            mal.getUserAnimeListing("KatsuteDev")
                .withStatus(AnimeStatus.Watching)
                .withLimit(1000)
                .withFields(Fields.Anime.list_status)
                .includeNSFW()
                .search();

        for(final AnimeListStatus listStatus : list)
            if(listStatus.getAnime().getID() == TestProvider.AnimeID){
                testStatus(listStatus);
                return;
            }
        fail("User Anime list status not found");
    }

    @Test @Order(3)
    final void testGetFromAnime(){
        assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final AnimeListStatus status = mal
            .getAnime(TestProvider.AnimeID, Fields.Anime.my_list_status)
            .getListStatus();
        testStatus(status);
    }

    private void testStatus(final AnimeListStatus status){
        assertEquals(AnimeStatus.Completed, status.getStatus());
        assertEquals(AnimeStatus.Completed.field(), status.getRawStatus());
        assertEquals(10, status.getScore());
        assertEquals(24, status.getWatchedEpisodes());
        assertTrue(status.isRewatching());
        assertNotNull(status.getStartDate());
        assertNotNull(status.getFinishDate());
        assertEquals(Priority.High, status.getPriority());
        assertEquals(Priority.High.value(), status.getRawPriority());
        assertEquals(1, status.getTimesRewatched());
        assertEquals(RewatchValue.VeryHigh, status.getRewatchValue());
        assertEquals(RewatchValue.VeryHigh.value(), status.getRawRewatchValue());
        assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[0]));
        assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[1]));
        assertEquals(TestProvider.testComment, status.getComments());
        assertNotNull(status.getUpdatedAt());
        assertNotNull(status.getUpdatedAtEpochMillis());
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
        assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withLimit(1000)
                .withFields(Fields.Anime.list_status)
                .withFields(Fields.Anime.genres)
                .search();

        for(final AnimeListStatus status : list)
            if(status.getAnime().getGenres() != null)
                for(final Genre genre : status.getAnime().getGenres())
                    if(genre.getName().equalsIgnoreCase("ecchi"))
                        return;

        fail("Failed to find Anime with Ecchi genre");
    }

}
