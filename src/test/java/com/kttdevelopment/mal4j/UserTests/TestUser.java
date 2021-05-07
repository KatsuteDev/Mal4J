package com.kttdevelopment.mal4j.UserTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.user.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

public class TestUser {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static User user;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        user = mal.getMyself(Fields.user);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("myselfProvider")
    public void testMyself(@SuppressWarnings("unused") final String method, final Function<User,Object> function){
        Assertions.assertNotNull(function.apply(user));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> myselfProvider(){
        return new TestProvider.MethodStream<User>()
            .add("ID", User::getID)
            .add("Name", User::getName)
            .add("PictureURL", User::getPictureURL)
            .add("Gender", User::getGender)
            .add("Location", User::getLocation)
            .add("JoinedAt", User::getJoinedAt)
            .add("JoinedAtEpoch", User::getJoinedAtEpochMillis)
            .add("TimeZone", User::getTimeZone)
            .add("Supporter", User::isSupporter)
            .add("Statistics", User::getAnimeStatistics)
            .add("Statistics#ItemsWatching", user -> user.getAnimeStatistics().getItemsWatching())
            .add("Statistics#ItemsCompleted", user -> user.getAnimeStatistics().getItemsCompleted())
            .add("Statistics#ItemsOnHold", user -> user.getAnimeStatistics().getItemsOnHold())
            .add("Statistics#ItemsPTW", user -> user.getAnimeStatistics().getItemsPlanToWatch())
            .add("Statistics#ItemsDropped", user -> user.getAnimeStatistics().getItemsDropped())
            .add("Statistics#Items", user -> user.getAnimeStatistics().getItems())
            .add("Statistics#DaysWatching", user -> user.getAnimeStatistics().getDaysWatching())
            .add("Statistics#DaysWatched", user -> user.getAnimeStatistics().getDaysWatched())
            .add("Statistics#DaysCompleted", user -> user.getAnimeStatistics().getDaysCompleted())
            .add("Statistics#DaysOnHold", user -> user.getAnimeStatistics().getDaysOnHold())
            .add("Statistics#DaysDropped", user -> user.getAnimeStatistics().getDaysDropped())
            .add("Statistics#Days", user -> user.getAnimeStatistics().getDays())
            .add("Statistics#Episodes", user -> user.getAnimeStatistics().getEpisodes())
            .add("Statistics#TimesRewatched", user -> user.getAnimeStatistics().getTimesRewatched())
            .add("Statistics#MeanScore", user -> user.getAnimeStatistics().getMeanScore())
            .stream();
    }

    @Test // test does actually pass
    public void testBirthday(){
        Assumptions.assumeTrue(user.getBirthday() != null, "User might not specify a birthday");
    }

    @Test
    public void testAnimeListing(){
        Assertions.assertDoesNotThrow(() -> user.getUserAnimeListing().withNoFields().withLimit(1).search());
    }

    @Test
    public void testMangaListing(){
        Assertions.assertDoesNotThrow(() -> user.getUserMangaListing().withNoFields().withLimit(1).search());
    }

}