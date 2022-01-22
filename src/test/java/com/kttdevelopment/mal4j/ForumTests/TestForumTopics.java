package com.kttdevelopment.mal4j.ForumTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.forum.ForumTopic;
import org.junit.jupiter.api.*;

import java.util.List;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestForumTopics {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    final void testSearch(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withQuery("MyAnimeList API")
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);
    }

    @Test
    final void testLimitOffset(){
        final List<ForumTopic> topics =
            mal.getForumTopics()
                .withLimit(1)
                .withOffset(1)
                .withQuery("MyAnimeList API")
                .search();
        annotateTest(() -> assertEquals(1, topics.size()));
    }

    @Test @DisplayName("Documentation is unclear what this filters") @Disabled
    final void testTopicUsername(){
        @SuppressWarnings("SpellCheckingInspection")
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withQuery("MyAnimeList API")
            .withTopicUsername("Myanimelist Redesign") // fixme
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    final void testUsername(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withUsername("Xinil")
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);
    }

    @Test
    final void testBoardID(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withBoardId(5)
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);

        annotateTest(() -> assertEquals(5, topic.getBoardID()));
    }

    @Test
    final void testSubBoardID(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withSubboardId(2)
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);
        annotateTest(() -> assertEquals(2, topic.getSubBoardID()));
    }

    private void testForumTopic(final ForumTopic topic){
        annotateTest(() -> assertNotNull(topic.getID()));
        annotateTest(() -> assertNotNull(topic.getTitle()));
        annotateTest(() -> assertNotNull(topic.getCreatedAt()));
        annotateTest(() -> assertNotNull(topic.getCreatedAtEpochMillis()));
        annotateTest(() -> assertNotNull(topic.getCreatedBy().getID()));
        // annotateTest(() -> assertEquals(topic.getCreatedBy().getID(), topic.getCreatedBy().getUser().getID())); // not yet implemented
        annotateTest(() -> assertNotNull(topic.getCreatedBy().getName()));
        annotateTest(() -> assertNotNull(topic.getPostsCount()));
        annotateTest(() -> assertNotNull(topic.getLastPostCreatedAt()));
        annotateTest(() -> assertNotNull(topic.getLastPostCreatedAtEpochMillis()));
        annotateTest(() -> assertNotNull(topic.getLastPostCreatedBy().getID()));
        // annotateTest(() -> assertEquals(topic.getLastPostCreatedBy().getID(), topic.getLastPostCreatedBy().getUser().getID())); // not yet implemented
        annotateTest(() -> assertNotNull(topic.getLastPostCreatedBy().getName()));
        annotateTest(() -> assertNotNull(topic.isLocked()));
    }

}
