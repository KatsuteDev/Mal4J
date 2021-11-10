package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.manga.MangaListStatus;
import com.kttdevelopment.mal4j.manga.property.MangaStatus;
import com.kttdevelopment.mal4j.manga.property.RereadValue;
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
public class TestMangaListStatus {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll() throws IOException{
        mal = TestProvider.getMyAnimeList();

        final String file = "manga-list-" + System.currentTimeMillis() + ".txt";
        System.out.println("Running Manga list tests, saving backup of current list to '" + file + '\'');
        Files.write(new File(file).toPath(), mal.getManga(TestProvider.MangaID).getListStatus().toString().getBytes(StandardCharsets.UTF_8));
    }

    @AfterAll
    public static void afterAll(){
        if(mal == null) return;

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

        Assertions.assertEquals(MangaStatus.PlanToRead, status.getStatus(),
                                Workflow.errorSupplier("Expected status to match"));
        Assertions.assertEquals(0, status.getScore(),
                                Workflow.errorSupplier("Expected score to match"));
        Assertions.assertEquals(0, status.getVolumesRead(),
                                Workflow.errorSupplier("Expected volumes read to match"));
        Assertions.assertEquals(0, status.getChaptersRead(),
                                Workflow.errorSupplier("Expected chapters read to match"));
        Assertions.assertFalse(status.isRereading(),
                               Workflow.errorSupplier("Expected rereading to be false"));
        Assertions.assertEquals(Priority.Low, status.getPriority(),
                                Workflow.errorSupplier("Expected priority to match"));
        Assertions.assertEquals(0, status.getTimesReread(),
                                Workflow.errorSupplier("Expected times reread to match"));
        Assertions.assertEquals(RereadValue.None, status.getRereadValue(),
                                Workflow.errorSupplier("Expected reread value to match"));
        Assertions.assertEquals(0, status.getTags().length,
                                Workflow.errorSupplier("Expected tags to match"));
        Assertions.assertEquals("", status.getComments(),
                                Workflow.errorSupplier("Expected comments to match"));
    }

    @Test @Order(0)
    public void testMinimalUpdate(){
        Assertions.assertDoesNotThrow(() -> mal.updateMangaListing(TestProvider.MangaID).score(10).update(),
                                      Workflow.errorSupplier("Updating a listing with only a score should not throw an exception"));
    }

    @Test @Order(1)
    public void testDelete(){
        mal.deleteMangaListing(TestProvider.MangaID);
        Assertions.assertDoesNotThrow(() -> mal.deleteMangaListing(TestProvider.MangaID),
                                      Workflow.errorSupplier("Deleting a deleted listing should not throw an exception"));
        Assertions.assertNull(mal.getManga(TestProvider.MangaID, Fields.Manga.my_list_status).getListStatus().getUpdatedAtEpochMillis(),
                              Workflow.errorSupplier("Expected a deleted listing to be null"));
    }

    private static boolean passedUpdate = false;
    @Test @Order(2)
    public void testUpdate(){
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
    public void testGet(){
        Assertions.assertTrue(passedUpdate,
                              Workflow.errorSupplier("Failed to start test (test requires update test to pass)"));

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
        Assertions.fail(Workflow.errorSupplier("Manga list status not found"));
    }

    @Test @Order(3)
    public void testGetUsername(){
        Assertions.assertTrue(passedUpdate,
                              Workflow.errorSupplier("Failed to start test (test requires update test to pass)"));

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
        Assertions.fail(Workflow.errorSupplier("User Manga list status not found"));
    }

    @Test @Order(3)
    public void testGetFromManga(){
        Assertions.assertTrue(passedUpdate,
                              Workflow.errorSupplier("Failed to start test (test requires update test to pass)"));

        final MangaListStatus status = mal.getManga(TestProvider.MangaID, Fields.Manga.my_list_status).getListStatus();
        testStatus(status);
    }

    private void testStatus(final MangaListStatus status){
        Assertions.assertEquals(MangaStatus.Completed, status.getStatus(),
                                Workflow.errorSupplier("Expected status to match"));
        Assertions.assertEquals(10, status.getScore(),
                                Workflow.errorSupplier("Expected score to match"));
        Assertions.assertEquals(8, status.getVolumesRead(),
                                Workflow.errorSupplier("Expected volumes read to match"));
        Assertions.assertEquals(49, status.getChaptersRead(),
                                Workflow.errorSupplier("Expected chapters read to match"));
        Assertions.assertTrue(status.isRereading(),
                              Workflow.errorSupplier("Expected rereading to be true"));
        Assertions.assertNotNull(status.getStartDate(),
                                 Workflow.errorSupplier("Expected start date to not be null"));
        Assertions.assertNotNull(status.getFinishDate(),
                                 Workflow.errorSupplier("Expected finish data to not be null"));
        Assertions.assertEquals(Priority.High, status.getPriority(),
                                Workflow.errorSupplier("Expected priority to match"));
        Assertions.assertEquals(1, status.getTimesReread(),
                                Workflow.errorSupplier("Expected times reread to match"));
        Assertions.assertEquals(RereadValue.VeryHigh, status.getRereadValue(),
                                Workflow.errorSupplier("Expected reread value to match"));
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[0]),
                              Workflow.errorSupplier("Expected tags to match"));
        Assertions.assertTrue(Arrays.asList(status.getTags()).contains(TestProvider.testTags()[1]),
                              Workflow.errorSupplier("Expected tags to match"));
        Assertions.assertEquals(TestProvider.testComment, status.getComments(),
                                Workflow.errorSupplier("Expected comments to match"));
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
    @Test @Order(5) @DisplayName("testEcchiNSFW(), #90 - Ecchi as NSFW")
    public void testEcchiNSFW(){
        Assertions.assertTrue(passedUpdate,
                              Workflow.errorSupplier("Failed to start test (test requires update test to pass)"));

        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .withLimit(1000)
                .withFields(Fields.Manga.list_status)
                .withFields(Fields.Manga.genres)
                .search();

        for(final MangaListStatus status : list)
            for(final Genre genre : status.getMangaPreview().getGenres())
                if(genre.getName().equalsIgnoreCase("ecchi"))
                    return;

        //noinspection ConstantConditions
        Assumptions.assumeTrue(false, Workflow.warningSupplier("Failed to find Manga with Ecchi genre (this is a data issue, disregard)"));
    }

}
