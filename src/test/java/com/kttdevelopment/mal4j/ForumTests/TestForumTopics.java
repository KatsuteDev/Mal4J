package com.kttdevelopment.mal4j.ForumTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.forum.ForumTopic;
import dev.katsute.jcore.Workflow;
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
        Assertions.assertEquals(1, topics.size(), Workflow.errorSupplier("Expected topic count to match"));
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

        Assertions.assertEquals(5, topic.getBoardID(), Workflow.errorSupplier("Expected board ID to match"));
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testSubBoardID(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withSubboardId(2)
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);

        Assertions.assertEquals(2, topic.getSubBoardID(), Workflow.errorSupplier("Expected subboard ID to match"));
    }

    private void testForumTopic(final ForumTopic topic){
        Assertions.assertNotNull(topic.getID(),
                                 Workflow.errorSupplier("Expected ForumTopic#getID to not be null"));
        Assertions.assertNotNull(topic.getTitle(),
                                 Workflow.errorSupplier("Expected ForumTopic#getTitle to not be null"));
        Assertions.assertNotNull(topic.getCreatedAt(),
                                 Workflow.errorSupplier("Expected ForumTopic#getCreatedAt to not be null"));
        Assertions.assertNotNull(topic.getCreatedAtEpochMillis(),
                                 Workflow.errorSupplier("Expected ForumTopic#getCreatedAtEpochMillis to not be null"));
        Assertions.assertNotNull(topic.getCreatedBy().getID(),
                                 Workflow.errorSupplier("Expected ForumTopic#getCreatedBy#getID to not be null"));
        // Assertions.assertEquals(topic.getCreatedBy().getID(), topic.getCreatedBy().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(topic.getCreatedBy().getName(),
                                 Workflow.errorSupplier("Expected ForumTopic#getCreatedBy#getName to not be null"));
        Assertions.assertNotNull(topic.getPostsCount(),
                                 Workflow.errorSupplier("Expected ForumTopic#getPostsCount to not be null"));
        Assertions.assertNotNull(topic.getLastPostCreatedAt(),
                                 Workflow.errorSupplier("Expected ForumTopic#getLastPostCreatedAt to not be null"));
        Assertions.assertNotNull(topic.getLastPostCreatedAtEpochMillis(),
                                 Workflow.errorSupplier("Expected ForumTopic#getLastPostCreatedAtEpochMillis to not be null"));
        Assertions.assertNotNull(topic.getLastPostCreatedBy().getID(),
                                 Workflow.errorSupplier("Expected ForumTopic#getLastPostCreatedBy#getID to not be null"));
        // Assertions.assertEquals(topic.getLastPostCreatedBy().getID(), topic.getLastPostCreatedBy().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(topic.getLastPostCreatedBy().getName(),
                                 Workflow.errorSupplier("Expected ForumTopic#getLastPostCreatedBy#getName to not be null"));
        Assertions.assertNotNull(topic.isLocked(), Workflow.errorSupplier("Expected ForumTopic#isLocked to not be null"));
    }

}
