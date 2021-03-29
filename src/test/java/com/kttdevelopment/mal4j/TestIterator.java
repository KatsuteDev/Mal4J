package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.forum.Post;
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
            .withNoFields()
            .withLimit(100)
            .searchAll();
        Assertions.assertNotEquals(0, iterator.toList().size());
        Assertions.assertEquals(TestProvider.AnimeID, iterator.toList().get(0).getID());

        final Anime first = iterator.next();
        Assertions.assertEquals(TestProvider.AnimeID, first.getID());
        iterator.forEachRemaining(animePreview -> Assertions.assertNotEquals(TestProvider.AnimeID, animePreview.getID()));
    }

    @Test
    public void testPostIterator(){
        final PaginatedIterator<Post> iterator = mal
            .getForumTopicDetailPostQuery(481)
            .searchAll();
        Assertions.assertNotEquals(0, iterator.toList().size());
        Assertions.assertEquals(481, iterator.toList().get(0).getForumTopicDetail().getID());

        iterator.forEachRemaining(post -> {});
    }

}
