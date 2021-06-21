package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.jcore.Workflow;
import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.anime.Anime;
import com.kttdevelopment.mal4j.anime.AnimePreview;
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
        final List<AnimePreview> suggestions =
            mal.getAnimeSuggestions()
                .withNoFields()
                .search();
        Assertions.assertNotNull(suggestions,
                                 Workflow.errorSupplier("Expected suggestions to not be null"));
        Assertions.assertNotEquals(0, suggestions.size(),
                                   Workflow.errorSupplier("Expected suggestions to not return 0"));
    }

}
