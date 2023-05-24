package dev.katsute.mal4j.CharacterTests;

import dev.katsute.mal4j.Fields;
import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.character.Character;
import dev.katsute.mal4j.property.ExperimentalFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestAnimeCharacter {

    private static MyAnimeList mal;

    @SuppressWarnings({"ConstantConditions", "RedundantSuppression"})
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();

        mal.enableExperimentalFeature(ExperimentalFeature.CHARACTERS);
    }

    @Test
    final void testCharacter(){
        final List<Character> characters = mal.getAnimeCharacters(TestProvider.AnimeID)
            .withLimit(100)
            .withNoFields()
            .search();

        assertNotNull(characters);
        assertFalse(characters.isEmpty());

        for(final Character character : characters)
            if(character.getID() == TestProvider.CharacterID)
                return;
        fail("Failed to find character in list");
    }

    @Test
    final void testFields(){
        assertTrue(mal.getAnimeCharacters(TestProvider.AnimeID).withNoFields().search().get(0).toString().contains(", firstName='null'"));
        assertFalse(mal.getAnimeCharacters(TestProvider.AnimeID).withField(Fields.Character.first_name).search().get(0).toString().contains(", firstName='null'"));
    }

}