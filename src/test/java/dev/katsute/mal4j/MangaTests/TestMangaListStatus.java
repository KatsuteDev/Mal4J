package dev.katsute.mal4j.MangaTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.manga.MangaListStatus;
import dev.katsute.mal4j.manga.property.MangaStatus;
import dev.katsute.mal4j.manga.property.RereadValue;
import dev.katsute.mal4j.property.Genre;
import dev.katsute.mal4j.property.Priority;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
final class TestMangaListStatus {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll() throws IOException{
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();

        final String file = "manga-list-" + System.currentTimeMillis() + ".txt";
        System.out.println("Running Manga list tests, saving backup of current list to '" + file + '\'');
        Files.write(new File(file).toPath(), mal.getManga(TestProvider.MangaID).getListStatus().toString().getBytes(StandardCharsets.UTF_8));
    }

    @AfterAll
    static void afterAll(){
        if(mal == null) return;
        TestProvider.requireToken();

        mal.deleteMangaListing(TestProvider.MangaID);

        if(mal.getAuthenticatedUser().getID() != 8316239) return;

        final MangaListStatus status = mal.updateMangaListing(TestProvider.MangaID)
            .status(MangaStatus.OnHold)
            .score(0)
            .volumesRead(0)
            .chaptersRead(0)
            .rereading(false)
            .priority(Priority.Low)
            .timesReread(0)
            .rereadValue(RereadValue.None)
            .tags("")
            .comments("")
            .update();

        assertEquals(MangaStatus.OnHold, status.getStatus());
        assertEquals(0, status.getScore());
        assertEquals(0, status.getVolumesRead());
        assertEquals(0, status.getChaptersRead());
        assertFalse(status.isRereading());
        assertEquals(Priority.Low, status.getPriority());
        assertEquals(0, status.getTimesReread());
        assertEquals(RereadValue.None, status.getRereadValue());
        assertEquals(0, status.getTags().length);
        assertEquals("", status.getComments());
    }

    @Test @Order(0)
    final void testMinimalUpdate(){
        assertDoesNotThrow(() -> mal.updateMangaListing(TestProvider.MangaID).score(10).update());
    }

    @Test @Order(1)
    final void testDelete(){
        mal.deleteMangaListing(TestProvider.MangaID);
        assertDoesNotThrow(() -> mal.deleteMangaListing(TestProvider.MangaID));
        assertNull(mal.getManga(TestProvider.MangaID, Fields.Manga.my_list_status).getListStatus().getUpdatedAtEpochMillis());
    }

    private static boolean passedUpdate = false;
    @Test @Order(2)
    final void testUpdate(){
        final Date now = new Date();
        final MangaListStatus status = mal.updateMangaListing(TestProvider.MangaID)
            .status(MangaStatus.Completed)
            .score(10)
            .startDate(now)
            .finishDate(now)
            .volumesRead(8)
            .chaptersRead(49)
            .rereading(true)
            .priority(Priority.High)
            .timesReread(1)
            .rereadValue(RereadValue.VeryHigh)
            .tags(TestProvider.testTags())
            .comments(TestProvider.testComment)
            .update();

        testStatus(status);
        passedUpdate = true;
    }

    @Test @Order(3)
    final void testGet(){
        assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .withStatus(MangaStatus.Reading)
                .withLimit(1000)
                .withFields(Fields.Manga.list_status)
                .includeNSFW()
                .search();

        for(final MangaListStatus listStatus : list)
            if(listStatus.getManga().getID() == TestProvider.MangaID){
                testStatus(listStatus);
                return;
            }
        fail("Manga list status not found");
    }

    @Test @Order(3)
    final void testGetUsername(){
        assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final List<MangaListStatus> list =
            mal.getUserMangaListing("KatsuteDev")
                .withStatus(MangaStatus.Reading)
                .withLimit(1000)
                .withFields(Fields.Manga.list_status)
                .includeNSFW()
                .search();

        for(final MangaListStatus listStatus : list)
            if(listStatus.getManga().getID() == TestProvider.MangaID){
                testStatus(listStatus);
                return;
            }
        fail("User Manga list status not found");
    }

    @Test @Order(3)
    final void testGetFromManga(){
        assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final MangaListStatus status = mal.getManga(TestProvider.MangaID, Fields.Manga.my_list_status).getListStatus();
        testStatus(status);
    }

    private void testStatus(final MangaListStatus status){
        assertEquals(MangaStatus.Completed, status.getStatus());
        assertEquals(MangaStatus.Completed.field(), status.getRawStatus());
        assertEquals(10, status.getScore());
        assertEquals(8, status.getVolumesRead());
        assertEquals(49, status.getChaptersRead());
        assertTrue(status.isRereading());
        assertNotNull(status.getStartDate());
        assertNotNull(status.getFinishDate());
        assertEquals(Priority.High, status.getPriority());
        assertEquals(Priority.High.value(), status.getRawPriority());
        assertEquals(1, status.getTimesReread());
        assertEquals(RereadValue.VeryHigh, status.getRereadValue());
        assertEquals(RereadValue.VeryHigh.value(), status.getRawRereadValue());
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

    @SuppressWarnings({"SpellCheckingInspection", "DataFlowIssue"})
    @Test @Order(5)
    final void testEcchiNSFW(){
        assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .withLimit(1000)
                .withFields(Fields.Manga.list_status)
                .withFields(Fields.Manga.genres)
                .search();

        for(final MangaListStatus status : list)
            if(status.getManga().getGenres() != null)
                for(final Genre genre : status.getManga().getGenres())
                    if(genre.getName().equalsIgnoreCase("ecchi"))
                        return;

        assumeTrue(false, "Failed to find Manga with Ecchi genre (this is a data issue, disregard)");
    }

}
