package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.property.ExperimentalFeature;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExperimentalEnabler {

    private static MyAnimeList mal;

    private static Anime anime;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        ((MyAnimeListImpl) mal).clearExperimentalFeatures();

        anime = mal.getAnime(TestProvider.AnimeID);
    }

    @AfterAll
    public static void afterAll(){
        if(mal != null)
            ((MyAnimeListImpl) mal).clearExperimentalFeatures();
    }

    @Test @Order(0)
    public void testExperimental(){
        Assertions.assertThrows(ExperimentalFeatureException.class, anime::getOpeningThemes);
    }

    @Test @Order(1)
    public void testExperimentalEnabled(){
        mal.enableExperimentalFeature(ExperimentalFeature.OP_ED_THEMES);

        Assertions.assertDoesNotThrow(anime::getOpeningThemes);
    }

    @Test @Order(2)
    public void testExperimentalAllEnabled(){
        mal.enableExperimentalFeature(ExperimentalFeature.ALL);

        Assertions.assertDoesNotThrow(anime::getVideos);
    }

    // todo: add test cases for when an experimental feature becomes native

}
