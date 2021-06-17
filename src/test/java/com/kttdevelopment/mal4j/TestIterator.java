package com.kttdevelopment.mal4j;

import com.kttdevelopment.jcore.Workflow;
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
        Assertions.assertNotEquals(0, iterator.toList().size(),
                                   Workflow.errorSupplier("Expected iterator size to not be 0"));
        Assertions.assertEquals(TestProvider.AnimeID, iterator.toList().get(0).getID(),
                                Workflow.errorSupplier("Expected first iterator to match test ID"));

        final Anime first = iterator.next();
        Assertions.assertEquals(TestProvider.AnimeID, first.getID());
        iterator.forEachRemaining(animePreview -> Assertions.assertNotEquals(TestProvider.AnimeID, animePreview.getID(),
                                                                             Workflow.errorSupplier("Expected subsequent iterator to not match test ID")));
    }

    @Test
    public void testPostIterator(){
        final PaginatedIterator<Post> iterator = mal
            .getForumTopicDetailPostQuery(481)
            .searchAll();
        Assertions.assertNotEquals(0, iterator.toList().size(),
                                   Workflow.errorSupplier("Expected iterator size to not be 0"));
        Assertions.assertEquals(481, iterator.toList().get(0).getForumTopicDetail().getID(),
                                Workflow.errorSupplier("Expected first iterator to match topic ID"));

        iterator.forEachRemaining(post -> Assertions.assertEquals(481, post.getForumTopicDetail().getID(),
                                                                  Workflow.errorSupplier("Expected subsequent iterator to match topic ID")));
    }

}
