package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.forum.*;
import com.kttdevelopment.myanimelist.forum.property.*;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.NSFW;
import com.kttdevelopment.myanimelist.user.User;
import com.kttdevelopment.myanimelist.user.UserAnimeStatistics;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"SpellCheckingInspection", "CommentedOutCode"})
public class TestMyAnimeList {

    private static MyAnimeList mal;

    private static final Path client = new File("src/test/java/com/kttdevelopment/myanimelist/client.txt").toPath();
    private static final Path oauth  = new File("src/test/java/com/kttdevelopment/myanimelist/oauth.txt").toPath();

    private static MyAnimeListAuthenticator authenticator;

    @BeforeAll
    public static void beforeAll() throws IOException{
        if(oauth.toFile().exists()){ // use existing OAuth
            mal = MyAnimeList.withOAuthToken(Files.readString(oauth));
            if(mal.getAnime().withQuery("さくら荘のペットな彼女").search() != null)
                return; // create new auth only if above null
        }

        Assumptions.assumeTrue(client.toFile().exists(), "Skipping tests (requires user authentication)");
        final String clientId = Files.readString(client);
        authenticator = new MyAnimeListAuthenticator(clientId, null, 5050);
        mal = MyAnimeList.withAuthorization(authenticator);

        // test refresh auth
        Assertions.assertNotNull(mal.getAnime().withQuery("さくら荘のペットな彼女").search());
        mal.refreshOAuthToken();
    }

    @AfterAll
    public static void afterAll() throws IOException{
        if(authenticator != null)
            Files.write(oauth, authenticator.getAccessToken().getToken().getBytes(StandardCharsets.UTF_8));
    }

    // Forum

    @Test // test may fail if forum has no subboards
    public void testForumCategories(){
        // test standard
        {
            final ForumCategory category =
                mal.getForumBoards().get(0);

            Assertions.assertNotNull(category.getTitle());
            // board
            {
                final ForumBoard board = category.getForumBoards()[2];
                Assertions.assertNotEquals(-1, board.getID());
                Assertions.assertNotNull(board.getTitle());
                Assertions.assertNotNull(board.getDescription());
                // subboard
                {
                    final ForumSubBoard subBoard = board.getSubBoards()[0];
                    Assertions.assertNotEquals(-1, subBoard.getID());
                    Assertions.assertNotNull(subBoard.getTitle());
                }
                Assertions.assertEquals(category, board.getCategory());
            }
        }
    }

    @Test
    public void testForumTopicDetail(){
        // test standard
        {
            final ForumTopic topic = mal.getForumTopicDetail(481);
            Assertions.assertNotNull(topic.getTitle());
            // posts
            {
                final Post post = topic.getPosts()[0];
                Assertions.assertNotEquals(-1, post.getID());
                Assertions.assertNotEquals(-1, post.getNumber());
                Assertions.assertNotEquals(-1, post.getCreatedAt());
                Assertions.assertNotNull(post.getBody());
                Assertions.assertNotNull(post.getSignature());
                Assertions.assertSame(topic, post.getForumTopic());
            }
            // poll
            {
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
        }
    }

    @Test
    public void testForumTopics(){
        // test standard
        {
            final List<ForumCategory> boards = mal.getForumBoards();
            final ForumCategory category = boards.get(0);
            Assertions.assertNotNull(category.getTitle());

            final ForumBoard board = category.getForumBoards()[2];
            Assertions.assertNotEquals(-1, board.getID());
            Assertions.assertNotNull(board.getTitle());
            Assertions.assertNotNull(board.getDescription());
            Assertions.assertNotEquals(-1, board.getSubBoards()[0].getBoard().getID());
            Assertions.assertNotNull(board.getSubBoards()[0].getBoard().getTitle());
        }
        final Consumer<ForumTopicDetail> test = topic -> {
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
        };
        // test search
        {
            final List<ForumTopicDetail> topics = mal.getForumTopics()
                .withQuery("MyAnimeList API")
                .search();
            final ForumTopicDetail topic = topics.get(0);
            test.accept(topic);
        }
        // test limit & offset
        {
            final List<ForumTopicDetail> topics =
                mal.getForumTopics()
                    .withLimit(1)
                    .withOffset(1)
                    .withQuery("MyAnimeList API")
                    .search();
            Assertions.assertEquals(1, topics.size());
        }
        // test topic name
        {
            final List<ForumTopicDetail> topics = mal.getForumTopics()
                .withLimit(1)
                .withQuery("MyAnimeList API")
                // .withTopicUsername("Myanimelist Redesign") // fixme
                .search();
            final ForumTopicDetail topic = topics.get(0);
            test.accept(topic);
        }
        // test username
        {
            final List<ForumTopicDetail> topics = mal.getForumTopics()
                .withLimit(1)
                .withUsername("Xinil")
                .search();
            final ForumTopicDetail topic = topics.get(0);
            test.accept(topic);
        }
        // id
        {
            // board
            {
                final List<ForumTopicDetail> topics = mal.getForumTopics()
                    .withLimit(1)
                    .withBoardId(5)
                    .search();
                final ForumTopicDetail topic = topics.get(0);
                test.accept(topic);
            }
            // sub board
            {
                final List<ForumTopicDetail> topics = mal.getForumTopics()
                    .withLimit(1)
                    .withSubboardId(2)
                    .search();
                final ForumTopicDetail topic = topics.get(0);
                test.accept(topic);
            }
            // id
            {
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
        }
    }

    // Manga

    @Test
    public void testUpdateAndDeleteMangaListing(){
        // test delete
        {
            mal.deleteMangaListing(28107);
            Assertions.assertNull(mal.getManga(28107).getListStatus());
        }
        final Consumer<MangaListStatus> test = status -> {
            Assertions.assertEquals(MangaStatus.PlanToRead, status.getStatus());
            Assertions.assertEquals(10, status.getScore());
            Assertions.assertEquals(0, status.getVolumesRead());
            Assertions.assertEquals(0, status.getChaptersRead());
            Assertions.assertFalse(status.isRereading()); // weak test
            // Assertions.assertNotEquals(-1, status.getStartDate()); // will fail
            // Assertions.assertNotEquals(-1, status.getFinishDate()); // will fail
            Assertions.assertEquals(2, status.getPriority());
            Assertions.assertEquals(0, status.getTimesReread());
            Assertions.assertEquals(5, status.getRereadValue());
            Assertions.assertTrue(Arrays.asList(status.getTags()).contains("ignore"));
            Assertions.assertTrue(Arrays.asList(status.getTags()).contains("tags"));
            Assertions.assertEquals("ignore comments", status.getComments());
            Assertions.assertNotEquals(-1, status.getUpdatedAt());
        };
        // test update
        {
            final MangaListStatus status = mal.updateMangaListing(28107)
                .status(MangaStatus.PlanToRead)
                .score(10)
                .volumesRead(0)
                .chaptersRead(0)
                .rereading(false)
                .priority(2)
                .timesReread(0)
                .rereadValue(5)
                .tags("ignore", "tags")
                .comments("ignore comments")
                .update();
            test.accept(status);
        }

        // test get
        {
            final List<MangaListStatus> list =
                mal.getUserMangaListing()
                    .withStatus(MangaStatus.PlanToRead)
                    .withLimit(1000)
                    .search();

            MangaListStatus status = null;
            for(final MangaListStatus userAnimeListStatus : list)
                if((status = userAnimeListStatus).getMangaPreview().getID() == 28107)
                    break;
            if(status == null)
                Assertions.fail();

            test.accept(status);
        }

        // list status
        {
            final MangaListStatus status = mal.getManga(28107).getListStatus();
            test.accept(status);
        }
    }

}
