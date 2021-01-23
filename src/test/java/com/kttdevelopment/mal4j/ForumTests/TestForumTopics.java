package com.kttdevelopment.mal4j.ForumTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.forum.*;
import com.kttdevelopment.mal4j.forum.property.Poll;
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
        final List<ForumTopicDetail> topics = mal.getForumTopics()
            .withQuery("MyAnimeList API")
            .search();
        final ForumTopicDetail topic = topics.get(0);
        testForumTopicDetail(topic);
    }

    @Test
    public void testLimitOffset(){
        final List<ForumTopicDetail> topics =
            mal.getForumTopics()
                .withLimit(1)
                .withOffset(1)
                .withQuery("MyAnimeList API")
                .search();
        Assertions.assertEquals(1, topics.size());
    }

    @Test @DisplayName("Documentation is unclear what this filters") @Disabled
    public void testTopicUsername(){
        final List<ForumTopicDetail> topics = mal.getForumTopics()
            .withLimit(1)
            .withQuery("MyAnimeList API")
            .withTopicUsername("Myanimelist Redesign") // fixme
            .search();
        final ForumTopicDetail topic = topics.get(0);
        testForumTopicDetail(topic);
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testUsername(){
        final List<ForumTopicDetail> topics = mal.getForumTopics()
            .withLimit(1)
            .withUsername("Xinil")
            .search();
        final ForumTopicDetail topic = topics.get(0);
        testForumTopicDetail(topic);
    }

    @Test
    public void testBoardID(){
        final List<ForumTopicDetail> topics = mal.getForumTopics()
            .withLimit(1)
            .withBoardId(5)
            .search();
        final ForumTopicDetail topic = topics.get(0);
        testForumTopicDetail(topic);
    }

    @Test
    public void testSubBoardID(){
         final List<ForumTopicDetail> topics = mal.getForumTopics()
            .withLimit(1)
            .withSubboardId(2)
            .search();
        final ForumTopicDetail topic = topics.get(0);
        testForumTopicDetail(topic);
    }

    @Test
    public void testPost(){
        final ForumTopic topic = mal.getForumTopicDetail(481);
        Assertions.assertNotNull(topic.getTitle());
        final Post post = topic.getPosts()[0];
        Assertions.assertDoesNotThrow(post::getID);
        Assertions.assertDoesNotThrow(post::getNumber);
        Assertions.assertDoesNotThrow(post::getCreatedAt);
        Assertions.assertDoesNotThrow(() -> post.getAuthor().getID());
        Assertions.assertNotNull(post.getAuthor().getName());
        Assertions.assertNotNull(post.getAuthor().getForumAvatarURL());
        // Assertions.assertEquals(post.getAuthor().getID(), post.getAuthor().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(post.getBody());
        Assertions.assertNotNull(post.getSignature());
        Assertions.assertEquals(topic, post.getForumTopic());
        final Poll poll = topic.getPoll();
        Assertions.assertDoesNotThrow(poll::getID);
        Assertions.assertNotNull(poll.getQuestion());
        Assertions.assertFalse(poll.isClosed());
        Assertions.assertDoesNotThrow(() -> poll.getOptions()[0].getID());
        Assertions.assertNotNull(poll.getOptions()[1].getText());
        Assertions.assertDoesNotThrow(() -> poll.getOptions()[0].getVotes());
        Assertions.assertEquals(topic, poll.getForumTopic());
        Assertions.assertEquals(poll, poll.getOptions()[0].getPoll());
    }

    private void testForumTopicDetail(final ForumTopicDetail topic){
        Assertions.assertDoesNotThrow(topic::getID);
        Assertions.assertNotNull(topic.getTitle());
        Assertions.assertDoesNotThrow(topic::getCreatedAt);
        Assertions.assertDoesNotThrow(() -> topic.getCreatedBy().getID());
        // Assertions.assertEquals(topic.getCreatedBy().getID(), topic.getCreatedBy().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(topic.getCreatedBy().getName());
        Assertions.assertDoesNotThrow(topic::getPostsCount);
        Assertions.assertDoesNotThrow(topic::getLastPostCreatedAt);
        Assertions.assertDoesNotThrow(() -> topic.getLastPostCreatedBy().getID());
        // Assertions.assertEquals(topic.getLastPostCreatedBy().getID(), topic.getLastPostCreatedBy().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(topic.getLastPostCreatedBy().getName());
        Assertions.assertFalse(topic.isLocked());
    }

}
