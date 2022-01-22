package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.anime.AnimePreview;
import com.kttdevelopment.mal4j.forum.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestIterator {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void testIterator(){
        final PaginatedIterator<AnimePreview> iterator = mal
            .getAnime()
            .withQuery(TestProvider.AnimeQuery)
            .withNoFields()
            .withLimit(100)
            .searchAll();
        annotateTest(() -> assertNotEquals(0, iterator.toList().size()));
        annotateTest(() -> assertEquals(TestProvider.AnimeID, iterator.toList().get(0).getID()));

        final AnimePreview first = iterator.next();
        annotateTest(() -> assertEquals(TestProvider.AnimeID, first.getID()));
        iterator.forEachRemaining(animePreview -> annotateTest(() -> assertNotEquals(TestProvider.AnimeID, animePreview.getID())));
    }

    @Test
    final void testPostIterator(){
        final PaginatedIterator<Post> iterator = mal
            .getForumTopicDetailPostQuery(481)
            .searchAll();
        annotateTest(() -> assertNotEquals(0, iterator.toList().size()));
        annotateTest(() -> assertEquals(481, iterator.toList().get(0).getForumTopicDetail().getID()));

        iterator.forEachRemaining(post -> annotateTest(() -> assertEquals(481, post.getForumTopicDetail().getID())));
    }

}
