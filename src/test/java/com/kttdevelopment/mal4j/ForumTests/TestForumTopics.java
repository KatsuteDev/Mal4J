package com.kttdevelopment.mal4j.ForumTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.forum.*;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestForumTopics {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testSearch(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withQuery("MyAnimeList API")
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);
    }

    @Test
    public void testLimitOffset(){
        final List<ForumTopic> topics =
            mal.getForumTopics()
                .withLimit(1)
                .withOffset(1)
                .withQuery("MyAnimeList API")
                .search();
        Assertions.assertEquals(1, topics.size());
    }

    @Test @DisplayName("Documentation is unclear what this filters") @Disabled
    public void testTopicUsername(){
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
    public void testUsername(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withUsername("Xinil")
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);
    }

    @Test
    public void testBoardID(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withBoardId(5)
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);

        Assertions.assertEquals(5, topic.getBoardID());
    }

    @Test
    public void testSubBoardID(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withSubboardId(2)
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);

        Assertions.assertEquals(2, topic.getSubBoardID());
    }

    private void testForumTopic(final ForumTopic topic){
        Assertions.assertNotNull(topic.getID());
        Assertions.assertNotNull(topic.getTitle());
        Assertions.assertNotNull(topic.getCreatedAt());
        Assertions.assertNotNull(topic.getCreatedAtEpochMillis());
        Assertions.assertNotNull(topic.getCreatedBy().getID());
        // Assertions.assertEquals(topic.getCreatedBy().getID(), topic.getCreatedBy().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(topic.getCreatedBy().getName());
        Assertions.assertNotNull(topic.getPostsCount());
        Assertions.assertNotNull(topic.getLastPostCreatedAt());
        Assertions.assertNotNull(topic.getLastPostCreatedAtEpochMillis());
        Assertions.assertNotNull(topic.getLastPostCreatedBy().getID());
        // Assertions.assertEquals(topic.getLastPostCreatedBy().getID(), topic.getLastPostCreatedBy().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(topic.getLastPostCreatedBy().getName());
        Assertions.assertNotNull(topic.isLocked());
    }

}
