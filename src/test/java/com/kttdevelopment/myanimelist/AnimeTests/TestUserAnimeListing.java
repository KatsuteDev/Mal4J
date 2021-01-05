package com.kttdevelopment.myanimelist.AnimeTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.anime.AnimeListStatus;
import com.kttdevelopment.myanimelist.anime.property.AnimeSort;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
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
               .withField(MyAnimeList.ALL_ANIME_FIELDS)
               .search();
        Assertions.assertEquals(AnimeStatus.Dropped, list.get(0).getStatus());
    }

    @Test
    public void testSort(){
        final List<AnimeListStatus> list =
            mal.getUserAnimeListing()
               .sortBy(AnimeSort.UpdatedAt)
               .withField(MyAnimeList.ALL_ANIME_FIELDS)
               .search();
        Assertions.assertTrue(list.get(0).getUpdatedAt() > list.get(1).getUpdatedAt());
    }

}
