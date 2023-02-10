package dev.katsute.mal4j.UserTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.property.ExperimentalFeature;
import dev.katsute.mal4j.user.User;
import dev.katsute.mal4j.user.property.AnimeAffinity;
import dev.katsute.mal4j.user.property.MangaAffinity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

final class TestUser {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static User user;

    @SuppressWarnings({"ConstantConditions", "RedundantSuppression"})
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();

        mal.enableExperimentalFeature(ExperimentalFeature.ALL);

        user = mal.getAuthenticatedUser(Fields.user);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("myselfProvider")
    final void testMyself(@SuppressWarnings("unused") final String method, final Function<User,Object> function){
        assertNotNull(function.apply(user), "Expected User#" + method + " to not be null");
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
    final void testBirthday(){
        assumeTrue(user.getBirthday() != null);
    }

    @Test
    final void testAnimeListing(){
        assertDoesNotThrow(() -> user.getUserAnimeListing().withNoFields().withLimit(1).search());
    }

    @Test
    final void testMangaListing(){
        assertDoesNotThrow(() -> user.getUserMangaListing().withNoFields().withLimit(1).search());
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    final void testAnimeAffinity(){
        final AnimeAffinity affinity = user.getAnimeAffinity("Xinil");
        assertEquals(affinity.getShared().length, affinity.getSharedCount());
        assertDoesNotThrow((ThrowingSupplier<Float>) affinity::getAffinity);
    }

    @SuppressWarnings("BusyWait")
    @Test
    final void testAnimeAffinityCallback(){
        final AtomicBoolean passed = new AtomicBoolean(false);
        user.getAnimeAffinity((affinity) -> {
            assertNotNull(affinity);
            passed.set(true);
        });

        assertTimeout(Duration.ofMinutes(1), () -> {
            while(!passed.get())
                Thread.sleep(5000);
        });
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    final void testMangaAffinity(){
        final MangaAffinity affinity = user.getMangaAffinity("Xinil");
        assertEquals(affinity.getShared().length, affinity.getSharedCount());
        assertDoesNotThrow((ThrowingSupplier<Float>) affinity::getAffinity);
    }

    @SuppressWarnings("BusyWait")
    @Test
    final void testMangaAffinityCallback(){
        final AtomicBoolean passed = new AtomicBoolean(false);
        user.getMangaAffinity((affinity) -> {
            assertNotNull(affinity);
            passed.set(true);
        });

        assertTimeout(Duration.ofMinutes(1), () -> {
            while(!passed.get())
                Thread.sleep(5000);
        });
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("nullAffinityProvider")
    final void testNullAffinity(final String method, final Function<User,Object> function){
        assertThrows(NullPointerException.class, () -> function.apply(user),"Expected " + method + " with null to throw NullPointerException");
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> nullAffinityProvider(){
        return new TestProvider.MethodStream<User>()
            .add("User#getAnimeAffinity(String)", user -> user.getAnimeAffinity((String) null))
            .add("User#getAnimeAffinity(User)", user -> user.getAnimeAffinity((User) null))
            .add("User#getMangaAffinity(String)", user -> user.getMangaAffinity((String) null))
            .add("User#getMangaAffinity(User)", user -> user.getMangaAffinity((User) null))
            .stream();
    }

}