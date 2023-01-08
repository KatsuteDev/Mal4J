package dev.katsute.mal4j.AnimeTests;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.TestProvider;
import dev.katsute.mal4j.anime.Anime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        final List<Anime> suggestions =
            mal.getAnimeSuggestions()
                .withNoFields()
                .search();
        assertNotNull(suggestions);
        assertNotEquals(0, suggestions.size());
    }

}
