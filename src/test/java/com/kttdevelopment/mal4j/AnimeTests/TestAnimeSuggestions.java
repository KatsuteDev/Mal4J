package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.anime.Anime;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestAnimeSuggestions {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testAnimeSuggestions(){
        final List<Anime> suggestions =
            mal.getAnimeSuggestions()
                .withNoFields()
                .search();
        Assertions.assertNotNull(suggestions);
        Assertions.assertNotEquals(0, suggestions.size());
    }

}
