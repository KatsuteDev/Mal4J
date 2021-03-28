package com.kttdevelopment.mal4j.UserTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.user.User;
import com.kttdevelopment.mal4j.user.UserAnimeStatistics;
import org.junit.jupiter.api.*;

public class TestUser {

    private static MyAnimeList mal;
    private static User user;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        user = mal.getMyself(Fields.user);
    }

    @Test
    public void testMyself(){
        Assertions.assertNotNull(user.getID());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getPictureURL());
        Assertions.assertNotNull(user.getGender());
        Assertions.assertNotNull(user.getLocation());
        Assertions.assertNotNull(user.getJoinedAt());
        Assertions.assertNotNull(user.getJoinedAtEpochMillis());
        Assertions.assertNotNull(user.getTimeZone());
        Assertions.assertFalse(user.isSupporter());
    }

    @Test
    public void testStatistics(){
        final UserAnimeStatistics statistics = user.getAnimeStatistics();
        Assertions.assertNotNull(statistics.getItemsWatching());
        Assertions.assertNotNull(statistics.getItemsCompleted());
        Assertions.assertNotNull(statistics.getDaysOnHold());
        Assertions.assertNotNull(statistics.getItemsPlanToWatch());
        Assertions.assertNotNull(statistics.getItemsDropped());
        Assertions.assertNotNull(statistics.getItemsOnHold());
        Assertions.assertNotNull(statistics.getItems());
        Assertions.assertNotNull(statistics.getDaysWatched());
        Assertions.assertNotNull(statistics.getDaysWatching());
        Assertions.assertNotNull(statistics.getDaysCompleted());
        Assertions.assertNotNull(statistics.getDaysOnHold());
        Assertions.assertNotNull(statistics.getDaysDropped());
        Assertions.assertNotNull(statistics.getDays());
        Assertions.assertNotNull(statistics.getEpisodes());
        Assertions.assertNotNull(statistics.getTimesRewatched());
        Assertions.assertNotNull(statistics.getMeanScore());
    }

    @Test // test does actually pass
    public void testBirthday(){
        Assumptions.assumeTrue(user.getBirthday() != null, "User might not specify a birthday");
    }

    @Test
    public void testAnimeMangaListing(){
        Assertions.assertNotEquals(0, user.getUserAnimeListing().withNoFields().withLimit(1).search().size());
        Assertions.assertNotEquals(0, user.getUserMangaListing().withNoFields().withLimit(1).search().size());
    }

    @Test
    public void testToString(){
        Assertions.assertNotEquals("{}", user.toString());
    }

}
