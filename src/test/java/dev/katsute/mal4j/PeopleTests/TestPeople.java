package dev.katsute.mal4j.PeopleTests;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.people.Person;
import dev.katsute.mal4j.property.ExperimentalFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

final class TestPeople {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static Person person;

    @SuppressWarnings({"ConstantConditions", "RedundantSuppression"})
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();

        mal.enableExperimentalFeature(ExperimentalFeature.PEOPLE);
        person = mal.getPerson(TestProvider.AltPersonID);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("personProvider")
    final void testPerson(final String method, final Function<Person,Object> function){
        assertNotNull(function.apply(person), "Expected Person#" + method + " to not be null");
    }

    private static Stream<Arguments> personProvider(){
        return new TestProvider.MethodStream<Person>()
            .add("FirstName", Person::getFirstName)
            .add("LastName", Person::getLastName)
            .add("Birthday", Person::getBirthday)
            .add("AlternativeNames", Person::getAlternativeNames)
            .add("AlternativeNames[0]", person -> person.getAlternativeNames()[0])
            .add("MainPicture", Person::getMainPicture)
            .add("MainPicture#MediumURL", person -> person.getMainPicture().getMediumURL())
            // .add("MainPicture#LargeURL", person -> person.getMainPicture().getLargeURL())
            .add("Favorites", Person::getFavorites)
            .add("More", Person::getMore)
            .add("MoreDetails", Person::getMoreDetails)
            .stream();
    }

    @Test
    final void testMore(){
        for(final Map.Entry<String,String> e : person.getMoreDetails().entrySet()){
            final String k = e.getKey();
            assertNotNull(k);
            assertFalse(k.isEmpty());
            assertEquals(k.trim().length(), k.length());
            assertFalse(k.endsWith(":"));
            final String v = e.getValue();
            assertNotNull(v);
            assertFalse(v.isEmpty());
            assertEquals(v.trim().length(), v.length());
            assertFalse(v.startsWith(":"));
        }
        assertNotSame(person.getMoreDetails(), person.getMoreDetails());
    }

}