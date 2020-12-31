package com.kttdevelopment.myanimelist.MangaTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.anime.AnimePreview;
import com.kttdevelopment.myanimelist.manga.MangaPreview;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestMangaSearch {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
    }

    @Test
    public void testSearch(){
        final List<MangaPreview> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .search();
        Assertions.assertEquals(TestProvider.AltMangaID, search.get(0).getID());
        Assertions.assertNotEquals(1, search.size());
    }

    @Test
    public void testOffsetLimit(){
        final List<MangaPreview> search =
            mal.getManga()
                .withQuery(TestProvider.AltMangaQuery)
                .withLimit(1)
                .withOffset(1)
                .search();
        Assertions.assertNotEquals(TestProvider.AltMangaID, search.get(0).getID());
        Assertions.assertEquals(1, search.size());
    }

    @Test
    public void testFields(){
        final List<AnimePreview> search =
            mal.getAnime()
                .withQuery(TestProvider.MangaQuery)
                .withLimit(1)
                .withFields(new String[0])
                .search();
        Assertions.assertNull(search.get(0).getType());
    }

    @Test
    public void testNSFW(){
        {
            final List<MangaPreview> search =
                mal.getManga()
                    .withQuery(TestProvider.NSFW_MangaQuery)
                    .withLimit(1)
                    .search();
            Assertions.assertEquals(0, search.size());
        }
        {
            final List<MangaPreview> search =
                mal.getManga()
                   .withQuery(TestProvider.NSFW_MangaQuery)
                   .withLimit(1)
                   .includeNSFW(true)
                   .search();
            Assertions.assertEquals(TestProvider.NSFW_MangaID, search.get(0).getID());
        }
    }

    @Test @DisplayName("#6 - Limit") @Disabled
    public void testOverLimit(){
        Assertions.assertNotNull(mal.getManga().withLimit(200).search());
    }

    @Test @DisplayName("#6 - Offset") @Disabled
    public void testZeroOffset(){
        Assertions.assertNotNull(mal.getManga().withOffset(0).search());
    }

}
