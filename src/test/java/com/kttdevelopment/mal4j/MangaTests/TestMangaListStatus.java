package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.manga.MangaListStatus;
import com.kttdevelopment.mal4j.manga.property.MangaStatus;
import com.kttdevelopment.mal4j.manga.property.RereadValue;
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
            .status(MangaStatus.PlanToRead)
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

        annotateTest(() -> assertEquals(MangaStatus.PlanToRead, status.getStatus()));
        annotateTest(() -> assertEquals(0, status.getScore()));
        annotateTest(() -> assertEquals(0, status.getVolumesRead()));
        annotateTest(() -> assertEquals(0, status.getChaptersRead()));
        annotateTest(() -> assertFalse(status.isRereading()));
        annotateTest(() -> assertEquals(Priority.Low, status.getPriority()));
        annotateTest(() -> assertEquals(0, status.getTimesReread()));
        annotateTest(() -> assertEquals(RereadValue.None, status.getRereadValue()));
        annotateTest(() -> assertEquals(0, status.getTags().length));
        annotateTest(() -> assertEquals("", status.getComments()));
    }

    @Test @Order(0)
    final void testMinimalUpdate(){
        annotateTest(() -> assertDoesNotThrow(() -> mal.updateMangaListing(TestProvider.MangaID).score(10).update()));
    }

    @Test @Order(1)
    final void testDelete(){
        mal.deleteMangaListing(TestProvider.MangaID);
        annotateTest(() -> assertDoesNotThrow(() -> mal.deleteMangaListing(TestProvider.MangaID)));
        annotateTest(() -> assertNull(mal.getManga(TestProvider.MangaID, Fields.Manga.my_list_status).getListStatus().getUpdatedAtEpochMillis()));
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
        annotateTest(() -> assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)"));

        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .withStatus(MangaStatus.Reading)
                .withLimit(1000)
                .withFields(Fields.Manga.list_status)
                .includeNSFW()
                .search();

        for(final MangaListStatus listStatus : list)
            if(listStatus.getMangaPreview().getID() == TestProvider.MangaID){
                testStatus(listStatus);
                return;
            }
        annotateTest(() -> fail("Manga list status not found"));
    }

    @Test @Order(3)
    final void testGetUsername(){
        annotateTest(() -> assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)"));

        final List<MangaListStatus> list =
            mal.getUserMangaListing("KatsuteDev")
                .withStatus(MangaStatus.Reading)
                .withLimit(1000)
                .withFields(Fields.Manga.list_status)
                .includeNSFW()
                .search();

        for(final MangaListStatus listStatus : list)
            if(listStatus.getMangaPreview().getID() == TestProvider.MangaID){
                testStatus(listStatus);
                return;
            }
        annotateTest(() -> fail("User Manga list status not found"));
    }

    @Test @Order(3)
    final void testGetFromManga(){
        annotateTest(() -> assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)"));

        final MangaListStatus status = mal.getManga(TestProvider.MangaID, Fields.Manga.my_list_status).getListStatus();
        testStatus(status);
    }

    private void testStatus(final MangaListStatus status){
        annotateTest(() -> assertEquals(MangaStatus.Completed, status.getStatus()));
        annotateTest(() -> assertEquals(10, status.getScore()));
        annotateTest(() -> assertEquals(8, status.getVolumesRead()));
        annotateTest(() -> assertEquals(49, status.getChaptersRead()));
        annotateTest(() -> assertTrue(status.isRereading()));
        annotateTest(() -> assertNotNull(status.getStartDate()));
        annotateTest(() -> assertNotNull(status.getFinishDate()));
        annotateTest(() -> assertEquals(Priority.High, status.getPriority()));
        annotateTest(() -> assertEquals(1, status.getTimesReread()));
        annotateTest(() -> assertEquals(RereadValue.VeryHigh, status.getRereadValue()));
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

        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .withLimit(1000)
                .withFields(Fields.Manga.list_status)
                .withFields(Fields.Manga.genres)
                .search();

        for(final MangaListStatus status : list)
            if(status.getMangaPreview().getGenres() != null)
                for(final Genre genre : status.getMangaPreview().getGenres())
                    if(genre.getName().equalsIgnoreCase("ecchi"))
                        return;

        //noinspection ConstantConditions
        annotateTest(() -> assumeTrue(false, "Failed to find Manga with Ecchi genre (this is a data issue, disregard)"));
    }

}
