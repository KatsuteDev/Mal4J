package com.kttdevelopment.mal4j.ForumTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.forum.ForumTopic;
import com.kttdevelopment.mal4j.forum.Post;
import com.kttdevelopment.mal4j.forum.property.Poll;
import com.kttdevelopment.mal4j.forum.property.PollOption;
import org.junit.jupiter.api.*;

public class TestForumTopicDetail {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static ForumTopic topic;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        topic = mal.getForumTopicDetail(481);
    }

    @Test
    public void testForumTopic(){
        Assertions.assertNotNull(topic.getTitle());
    }

    @Test
    public void testPosts(){
        final Post post = topic.getPosts()[0];
        Assertions.assertNotEquals(-1, post.getID());
        Assertions.assertNotEquals(-1, post.getNumber());
        Assertions.assertNotEquals(-1, post.getCreatedAt());
        Assertions.assertNotNull(post.getBody());
        Assertions.assertNotNull(post.getSignature());
        Assertions.assertSame(topic, post.getForumTopic());
    }

    @Test
    public void testPoll(){
        final Poll poll = topic.getPoll();
        Assertions.assertNotEquals(-1, poll.getID());
        Assertions.assertNotNull(poll.getQuestion());
        Assertions.assertFalse(poll.isClosed()); // weak test
        // options
        {
            final PollOption option = poll.getOptions()[0];
            Assertions.assertNotEquals(-1, option.getID());
            Assertions.assertNotNull(option.getText());
            Assertions.assertNotEquals(-1, option.getVotes());
            Assertions.assertSame(poll, option.getPoll());
        }
        Assertions.assertSame(topic, poll.getForumTopic());
    }

    @Test
    public void testPostLimitOffset(){
        Assertions.assertEquals(5, mal.getForumTopicDetail(481, 5).getPosts().length);
        Assertions.assertEquals(6, mal.getForumTopicDetail(481, 5, 5).getPosts()[0].getNumber());
    }

}
