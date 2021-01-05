package com.kttdevelopment.myanimelist.MangaTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.manga.MangaListStatus;
import com.kttdevelopment.myanimelist.manga.property.MangaSort;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestUserMangaListing {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testStatus(){
        final List<MangaListStatus> list =
            mal.getUserMangaListing()
               .withStatus(MangaStatus.PlanToRead)
               .withLimit(1)
               .withField(MyAnimeList.ALL_MANGA_FIELDS)
               .search();
        Assertions.assertEquals(MangaStatus.PlanToRead, list.get(0).getStatus());
    }

    @Test
    public void testSort(){
        final List<MangaListStatus> list =
            mal.getUserMangaListing()
               .sortBy(MangaSort.UpdatedAt)
               .withLimit(2)
               .withField(MyAnimeList.ALL_MANGA_FIELDS)
               .search();
        Assertions.assertTrue(list.get(0).getUpdatedAt() > list.get(1).getUpdatedAt());
    }

}
