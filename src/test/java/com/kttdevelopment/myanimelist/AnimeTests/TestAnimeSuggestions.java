package com.kttdevelopment.myanimelist.AnimeTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.AnimePreview;
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
                .search();
        Assertions.assertNotNull(suggestions);
        Assertions.assertNotEquals(0, suggestions.size());
    }

}
