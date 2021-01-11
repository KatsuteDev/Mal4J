package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.anime.Anime;
import org.junit.jupiter.api.*;

public class TestIterator {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testIterator(){
        final PaginatedIterator<Anime> iterator = mal
            .getAnime()
            .withQuery(TestProvider.AnimeQuery)
            .withFields(MyAnimeList.NO_FIELDS)
            .withLimit(100)
            .searchAll();

        final Anime first = iterator.next();
        Assertions.assertEquals(TestProvider.AnimeID, first.getID());
        iterator.forEachRemaining(animePreview -> Assertions.assertNotEquals(TestProvider.AnimeID, animePreview.getID()));
    }

}
