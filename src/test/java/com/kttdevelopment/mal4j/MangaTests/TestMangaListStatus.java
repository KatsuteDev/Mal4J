package com.kttdevelopment.mal4j.MangaTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.manga.MangaListStatus;
import com.kttdevelopment.mal4j.manga.property.MangaStatus;
import com.kttdevelopment.mal4j.property.Priority;
import com.kttdevelopment.mal4j.manga.property.RereadValue;
import org.junit.jupiter.api.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMangaListStatus {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @AfterAll
    public static void afterAll(){
        if(mal == null) return;

        mal.deleteMangaListing(TestProvider.MangaID);

        if(mal.getMyself().getID() != 8316239) return;

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

        Assertions.assertEquals(MangaStatus.PlanToRead, status.getStatus());
        Assertions.assertEquals(0, status.getScore());
        Assertions.assertEquals(0, status.getVolumesRead());
        Assertions.assertEquals(0, status.getChaptersRead());
        Assertions.assertFalse(status.isRereading());
        Assertions.assertEquals(Priority.Low, status.getPriority());
        Assertions.assertEquals(0, status.getTimesReread());
        Assertions.assertEquals(RereadValue.None, status.getRereadValue());
        Assertions.assertEquals(0, status.getTags().length);
        Assertions.assertEquals("", status.getComments());
    }

    @Test @Order(1)
    public void testDelete(){
        mal.deleteMangaListing(TestProvider.MangaID);
        Assertions.assertDoesNotThrow(() -> mal.deleteMangaListing(TestProvider.MangaID));
        Assertions.assertNull(mal.getManga(TestProvider.MangaID, Fields.Manga.my_list_status).getListStatus().getUpdatedAtEpochMillis());
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
            .timesReread(0)
            .rereadValue(RereadValue.VeryHigh)
            .tags(TestProvider.testTags())
            .comments(TestProvider.testComment)
            .update();

        testStatus(status);
        passedUpdate = true;
    }

    @Test @Order(3)
    public void testGet(){
        Assertions.assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

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
        Assertions.fail("Manga list status not found");
    }

    @Test @Order(3)
    public void testGetUsername(){
        Assertions.assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

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
        Assertions.fail("User Manga list status not found");
    }

    @Test @Order(3)
    public void testGetFromManga(){
        Assertions.assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final MangaListStatus status = mal.getManga(TestProvider.MangaID, Fields.Manga.my_list_status).getListStatus();
        testStatus(status);
    }

    private void testStatus(final MangaListStatus status){
        Assertions.assertEquals(MangaStatus.Completed, status.getStatus());
        Assertions.assertEquals(10, status.getScore());
        Assertions.assertEquals(8, status.getVolumesRead());
        Assertions.assertEquals(49, status.getChaptersRead());
        Assertions.assertTrue(status.isRereading());
        Assertions.assertNotNull(status.getStartDate());
        Assertions.assertNotNull(status.getFinishDate());
        Assertions.assertEquals(Priority.High, status.getPriority());
        Assertions.assertEquals(0, status.getTimesReread());
        Assertions.assertEquals(RereadValue.VeryHigh, status.getRereadValue());
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
        Assertions.assertTrue(passedUpdate, "Failed to start test (test requires update test to pass)");

        final List<MangaListStatus> list =
            mal.getUserMangaListing()
                .withLimit(1000)
                .withFields(Fields.Manga.list_status)
                .search();

        for(final MangaListStatus listStatus : list)
            if(listStatus.getMangaPreview().getID() == TestProvider.MangaID)
                return;
        Assertions.fail("Failed to find Manga with Ecchi genre (external issue, disregard fail)");
    }

}
