package com.kttdevelopment.myanimelist.ForumTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.forum.*;
import com.kttdevelopment.myanimelist.forum.property.Poll;
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
        Assertions.assertNotEquals(-1, post.getID());
        Assertions.assertNotEquals(-1, post.getNumber());
        Assertions.assertNotEquals(-1, post.getCreatedAt());
        Assertions.assertNotEquals(-1, post.getAuthor().getID());
        Assertions.assertNotNull(post.getAuthor().getName());
        Assertions.assertNotNull(post.getAuthor().getForumAvatarURL());
        // Assertions.assertEquals(post.getAuthor().getID(), post.getAuthor().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(post.getBody());
        Assertions.assertNotNull(post.getSignature());
        Assertions.assertEquals(topic, post.getForumTopic());
        final Poll poll = topic.getPoll();
        Assertions.assertNotEquals(-1, poll.getID());
        Assertions.assertNotNull(poll.getQuestion());
        Assertions.assertFalse(poll.isClosed());  // weak test
        Assertions.assertNotEquals(-1, poll.getOptions()[0].getID());
        Assertions.assertNotNull(poll.getOptions()[1].getText());
        Assertions.assertNotEquals(-1, poll.getOptions()[0].getVotes());
        Assertions.assertEquals(topic, poll.getForumTopic());
        Assertions.assertEquals(poll, poll.getOptions()[0].getPoll());
    }

    private void testForumTopicDetail(final ForumTopicDetail topic){
        Assertions.assertNotEquals(-1, topic.getID());
        Assertions.assertNotNull(topic.getTitle());
        Assertions.assertNotEquals(-1, topic.getCreatedAt());
        Assertions.assertNotEquals(-1, topic.getCreatedBy().getID());
        // Assertions.assertEquals(topic.getCreatedBy().getID(), topic.getCreatedBy().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(topic.getCreatedBy().getName());
        Assertions.assertNotEquals(-1, topic.getPostsCount());
        Assertions.assertNotEquals(-1, topic.getLastPostCreatedAt());
        Assertions.assertNotEquals(-1, topic.getLastPostCreatedBy().getID());
        // Assertions.assertEquals(topic.getLastPostCreatedBy().getID(), topic.getLastPostCreatedBy().getUser().getID()); // not yet implemented
        Assertions.assertNotNull(topic.getLastPostCreatedBy().getName());
        Assertions.assertFalse(topic.isLocked());  // weak test
    }

}
