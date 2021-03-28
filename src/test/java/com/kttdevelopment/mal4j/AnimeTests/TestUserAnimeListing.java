package com.kttdevelopment.mal4j.AnimeTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.anime.AnimeListStatus;
import com.kttdevelopment.mal4j.anime.property.AnimeSort;
import com.kttdevelopment.mal4j.anime.property.AnimeStatus;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestUserAnimeListing {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testStatus(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .withStatus(AnimeStatus.Dropped)
                .withFields(Fields.Anime.list_status)
                .search();
        Assertions.assertEquals(AnimeStatus.Dropped, list.get(0).getStatus());

        Assertions.assertNotEquals("{}", list.get(0).toString());
    }

    @Test
    public void testSort(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
                .sortBy(AnimeSort.UpdatedAt)
                .withFields(Fields.Anime.list_status)
                .search();
        Assertions.assertTrue(list.get(0).getUpdatedAt().getTime() > list.get(1).getUpdatedAt().getTime());
    }

}
