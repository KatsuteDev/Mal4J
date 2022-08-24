package dev.katsute.mal4j.ForumTests;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.forum.ForumTopic;
import org.junit.jupiter.api.*;

import java.util.List;

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
        assertEquals(1, topics.size());
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

        assertEquals(5, topic.getBoardID());
    }

    @Test
    final void testSubBoardID(){
        final List<ForumTopic> topics = mal.getForumTopics()
            .withLimit(1)
            .withSubboardId(2)
            .search();
        final ForumTopic topic = topics.get(0);
        testForumTopic(topic);
        assertEquals(2, topic.getSubBoardID());
    }

    private void testForumTopic(final ForumTopic topic){
        assertNotNull(topic.getID());
        assertNotNull(topic.getTitle());
        assertNotNull(topic.getCreatedAt());
        assertNotNull(topic.getCreatedAtEpochMillis());
        assertNotNull(topic.getCreatedBy().getID());
        // assertEquals(topic.getCreatedBy().getID(), topic.getCreatedBy().getUser().getID()); // not yet implemented
        assertNotNull(topic.getCreatedBy().getName());
        assertNotNull(topic.getPostsCount());
        assertNotNull(topic.getLastPostCreatedAt());
        assertNotNull(topic.getLastPostCreatedAtEpochMillis());
        assertNotNull(topic.getLastPostCreatedBy().getID());
        // assertEquals(topic.getLastPostCreatedBy().getID(), topic.getLastPostCreatedBy().getUser().getID()); // not yet implemented
        assertNotNull(topic.getLastPostCreatedBy().getName());
        assertNotNull(topic.isLocked());
    }

}
