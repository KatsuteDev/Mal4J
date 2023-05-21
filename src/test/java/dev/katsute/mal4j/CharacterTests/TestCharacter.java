package dev.katsute.mal4j.CharacterTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.character.Character;
import dev.katsute.mal4j.property.ExperimentalFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

final class TestCharacter {

    private static MyAnimeList mal;

    @SuppressWarnings({"ConstantConditions", "RedundantSuppression"})
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();

        mal.enableExperimentalFeature(ExperimentalFeature.ALL);
    }

    @Test
    final void test(){
        final Character character = mal.getCharacter(TestProvider.CharacterID);
        System.out.println(character);
    }

}