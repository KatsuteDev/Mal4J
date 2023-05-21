package dev.katsute.mal4j.CharacterTests;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.character.Character;
import dev.katsute.mal4j.property.ExperimentalFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

final class TestCharacter {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static Character character;

    @SuppressWarnings({"ConstantConditions", "RedundantSuppression"})
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();

        mal.enableExperimentalFeature(ExperimentalFeature.CHARACTERS);
        character = mal.getCharacter(TestProvider.CharacterID);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("characterProvider")
    final void testCharacter(final String method, final Function<Character,Object> function){
        assertNotNull(function.apply(character), "Expected Character#" + method + " to not be null");
    }

    private static Stream<Arguments> characterProvider(){
        return new TestProvider.MethodStream<Character>()
            .add("FirstName", Character::getFirstName)
            .add("LastName", Character::getLastName)
            .add("AlternativeNames", Character::getAlternativeNames)
            .add("MainPicture", Character::getMainPicture)
            .add("MainPicture#MediumURL", character -> character.getMainPicture().getMediumURL())
            // .add("MainPicture#LargeURL", character -> character.getMainPicture().getLargeURL())
            .add("Biography", Character::getBiography)
            .add("Animeography", Character::getAnimeography)
            .add("Animeography[0]", character -> character.getAnimeography()[0])
            .add("Animeography[0]#AnimePreview", character -> character.getAnimeography()[0].getAnime().getID())
            .add("Animeography[0]#Role", character -> character.getAnimeography()[0].getRole())
            .stream();
    }

    @Test
    final void testCharacter(){
        assertEquals(TestProvider.CharacterID, character.getID());
    }

}