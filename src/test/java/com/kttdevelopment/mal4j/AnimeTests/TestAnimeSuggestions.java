package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.anime.AnimePreview;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestAnimeSuggestions {

    private static MyAnimeList mal;

    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        TestProvider.requireToken();
    }

    @Test
    final void testAnimeSuggestions(){
        final List<AnimePreview> suggestions =
            mal.getAnimeSuggestions()
                .withNoFields()
                .search();
        annotateTest(() -> assertNotNull(suggestions));
        annotateTest(() -> assertNotEquals(0, suggestions.size()));
    }

}
