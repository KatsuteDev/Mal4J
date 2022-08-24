package dev.katsute.mal4j;

import dev.katsute.mal4j.property.ExperimentalFeature;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExperimentalEnabler {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test @Order(0)
    public void testExperimental(){
        mal.getAnime(TestProvider.AnimeID).getOpeningThemes();
    }

    @Test @Order(1)
    public void testExperimentalEnabled(){
        mal.enableExperimentalFeature(ExperimentalFeature.OP_ED_THEMES);

        mal.getAnime(TestProvider.AnimeID).getOpeningThemes();
    }

    // todo: add test cases for when an experimental feature becomes native

}
