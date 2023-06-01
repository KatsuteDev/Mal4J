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

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
final class TestCharacter {

    private static MyAnimeList mal;
    private static Character character;

    @SuppressWarnings({"ConstantConditions", "RedundantSuppression"})
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();

        mal.enableExperimentalFeature(ExperimentalFeature.CHARACTERS);
        character = mal.getCharacter(TestProvider.AltCharacterID);
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
            .add("AlternativeNames[0]", character -> character.getAlternativeNames()[0])
            .add("MainPicture", Character::getMainPicture)
            .add("MainPicture#MediumURL", character -> character.getMainPicture().getMediumURL())
            // .add("MainPicture#LargeURL", character -> character.getMainPicture().getLargeURL())
            .add("Favorites", Character::getFavorites)
            .add("Pictures", Character::getPictures)
            .add("Pictures[0]", character -> character.getPictures()[0])
            .add("Pictures#MediumURL", character -> character.getPictures()[0].getMediumURL())
            // .add("Pictures#LargeURL", character -> character.getPictures()[0].getLargeURL())
            .add("Biography", Character::getBiography)
            .add("BiographyDetails", Character::getBiographyDetails)
            .add("Animeography", Character::getAnimeography)
            .add("Animeography[0]", character -> character.getAnimeography()[0])
            .add("Animeography[0]#AnimePreview", character -> character.getAnimeography()[0].getAnime().getID())
            .add("Animeography[0]#Role", character -> character.getAnimeography()[0].getRole())
            .stream();
    }

    @Test
    final void testAlternativeNames(){
        final String[] names = mal.getCharacter(TestProvider.AltCharacterID).getAlternativeNames();
        assertTrue(names.length > 1);
        for(final String n : names){
            assertFalse(n.isEmpty());
            assertFalse(n.contains(","));
            assertEquals(n.trim().length(), n.length());
        }
    }

    @Test
    final void testPartial(){
        final Character character = mal.getAnimeCharacters(TestProvider.AnimeID).search().get(0);
        final int was = character.toString().length();
        character.getAnimeography();
        assertTrue(character.toString().length() > was);
    }

    @Test
    final void testBiographyDetails(){
        for(final Map.Entry<String,String> e : character.getBiographyDetails().entrySet()){
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
        assertNotSame(character.getBiographyDetails(), character.getBiographyDetails());
    }

}