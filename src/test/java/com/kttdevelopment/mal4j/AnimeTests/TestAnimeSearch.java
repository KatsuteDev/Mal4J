package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.anime.AnimePreview;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestAnimeSearch {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void testSearch(){
        final List<AnimePreview> search =
            mal.getAnime()
                .withQuery(TestProvider.AnimeQuery)
                .withNoFields()
                .search();
        assertEquals(TestProvider.AnimeID, search.get(0).getID());
        assertNotEquals(1, search.size());
    }

    @Test
    final void testOffsetLimit(){
        final List<AnimePreview> search =
            mal.getAnime()
                .withQuery(TestProvider.AltAnimeQuery)
                .withLimit(1)
                .withOffset(1)
                .withNoFields()
                .search();
        assertNotEquals(TestProvider.AnimeID, search.get(0).getID());
        assertEquals(1, search.size());
    }

    @Test
    final void testFields(){
        final List<AnimePreview> search =
            mal.getAnime()
                .withQuery(TestProvider.AnimeQuery)
                .withLimit(1)
                .withNoFields()
                .search();
        assertNull(search.get(0).getType());
    }

    @Test
    final void testNSFW(){
        {
            final List<AnimePreview> search =
                mal.getAnime()
                    .withQuery(TestProvider.NSFW_AnimeQuery)
                    .withLimit(1)
                    .withNoFields()
                    .search();
            assertEquals(0, search.size());
        }
        {
            final List<AnimePreview> search =
                mal.getAnime()
                   .withQuery(TestProvider.NSFW_AnimeQuery)
                   .withLimit(1)
                   .withNoFields()
                   .includeNSFW()
                   .search();
            annotateTest(() -> assertTrue(
                search.get(0).getID() == TestProvider.NSFW_AnimeID || search.get(0).getID() == TestProvider.AltNSFW_AnimeID,
                "NSFW Anime ID was supposed to be either " + TestProvider.NSFW_AnimeID + " or " + TestProvider.AltNSFW_AnimeID + " but was " + search.get(0).getID()
            ));
        }
    }

}
