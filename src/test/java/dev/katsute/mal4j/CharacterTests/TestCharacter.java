package dev.katsute.mal4j.CharacterTests;

import dev.katsute.mal4j.*;
import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.anime.RelatedAnime;
import dev.katsute.mal4j.anime.property.AnimeSource;
import dev.katsute.mal4j.anime.property.AnimeType;
import dev.katsute.mal4j.character.Character;
import dev.katsute.mal4j.manga.RelatedManga;
import dev.katsute.mal4j.property.ExperimentalFeature;
import dev.katsute.mal4j.property.RelationType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
        final Character character = mal.getCharacter(36828);
        System.out.println(character);
    }

}