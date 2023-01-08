package dev.katsute.mal4j.ForumTests;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.forum.ForumTopicDetail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

final class TestForumTopicDetail {

    private static MyAnimeList mal;
    private static ForumTopicDetail topic;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        topic = mal.getForumTopicDetail(481);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("forumTopicProvider")
    void testForumTopic(@SuppressWarnings("unused") final String method, final Function<ForumTopicDetail,Object> function){
        assertNotNull(function.apply(topic), "Expected ForumTopicDetail#" + method + " to not be null");
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> forumTopicProvider(){
        return new TestProvider.MethodStream<ForumTopicDetail>()
            .add("Title", ForumTopicDetail::getTitle)
            .add("Posts", ForumTopicDetail::getPosts)
            .add("Posts[0]", topic -> topic.getPosts()[0])
            .add("Posts#ID", topic -> topic.getPosts()[0].getID())
            .add("Posts#Number", topic -> topic.getPosts()[0].getNumber())
            .add("Posts#CreatedAt", topic -> topic.getPosts()[0].getCreatedAt())
            .add("Posts#Body", topic -> topic.getPosts()[0].getBody())
            .add("Posts#Signature", topic -> topic.getPosts()[0].getSignature())
            .add("Poll", ForumTopicDetail::getPoll)
            .add("Poll#ID", topic -> topic.getPoll().getID())
            .add("Poll#Question", topic -> topic.getPoll().getQuestion())
            .add("Poll#Closed", topic -> topic.getPoll().isClosed())
            .add("Poll#PollOptions", topic -> topic.getPoll().getOptions())
            .add("Poll#PollOptions[0]", topic -> topic.getPoll().getOptions()[0])
            .add("Poll#PollOptions#ID", topic -> topic.getPoll().getOptions()[0].getID())
            .add("Poll#PollOptions#Text", topic -> topic.getPoll().getOptions()[0].getText())
            .add("Poll#PollOptions#Votes", topic -> topic.getPoll().getOptions()[0].getVotes())
            .stream();
    }

    @Test
    final void testPostsReference(){
        assertSame(topic, topic.getPosts()[0].getForumTopicDetail());
    }

    @Test
    final void testPollReference(){
        assertSame(topic.getPoll(), topic.getPoll().getOptions()[0].getPoll());
        assertSame(topic, topic.getPoll().getForumTopicDetail());
    }

    @Test
    final void testPostLimitOffset(){
        assertEquals(5, mal.getForumTopicDetail(481, 5).getPosts().length);
        assertEquals(6, mal.getForumTopicDetail(481, 5, 5).getPosts()[0].getNumber());
    }

}