package com.kttdevelopment.mal4j.UserTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
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
        user = mal.getMyself();
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test @DisplayName("testUser() - not currently allowed by API")
    public void testUser(){
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
            Assertions.assertEquals(8316239, mal.getUser("KatsuteDev").getID())
        );
    }

    @Test
    public void testMyself(){
        Assertions.assertDoesNotThrow(user::getID);
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getPictureURL());
        Assertions.assertNotNull(user.getGender());
        Assertions.assertNotNull(user.getLocation());
        Assertions.assertDoesNotThrow(() -> user.getJoinedAt().getTime());
        Assertions.assertDoesNotThrow(() -> user.getJoinedAtEpochMillis());
        Assertions.assertNotNull(user.getTimeZone());
        Assertions.assertThrows(NullPointerException.class, user::isSupporter);
    }

    @Test
    public void testStatistics(){
        final UserAnimeStatistics statistics = user.getAnimeStatistics();
        Assertions.assertDoesNotThrow(statistics::getItemsWatching);
        Assertions.assertDoesNotThrow(statistics::getItemsCompleted);
        Assertions.assertDoesNotThrow(statistics::getDaysOnHold);
        Assertions.assertDoesNotThrow(statistics::getItemsPlanToWatch);
        Assertions.assertDoesNotThrow(statistics::getItemsDropped);
        Assertions.assertDoesNotThrow(statistics::getItemsOnHold);
        Assertions.assertDoesNotThrow(statistics::getItems);
        Assertions.assertDoesNotThrow(statistics::getDaysWatched);
        Assertions.assertDoesNotThrow(statistics::getDaysWatching);
        Assertions.assertDoesNotThrow(statistics::getDaysCompleted);
        Assertions.assertDoesNotThrow(statistics::getDaysOnHold);
        Assertions.assertDoesNotThrow(statistics::getDaysDropped);
        Assertions.assertDoesNotThrow(statistics::getDays);
        Assertions.assertDoesNotThrow(statistics::getEpisodes);
        Assertions.assertDoesNotThrow(statistics::getTimesRewatched);
        Assertions.assertDoesNotThrow(statistics::getMeanScore);
    }

    @Test // test does actually pass
    public void testBirthday(){
        Assumptions.assumeTrue(user.getBirthday().getTime() != -1, "User might not specify a birthday");
        Assumptions.assumeTrue(user.getBirthdayEpochMillis() != -1, "User might not specify a birthday");
    }

}
