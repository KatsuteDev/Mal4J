package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.AnimePreview;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestClass {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll() throws IOException{
        final File file = new File("src/test/java/com/kttdevelopment/myanimelist/client.txt");
        Assumptions.assumeTrue(file.exists(), "Skipping tests (requires user authentication)");
        final String clientId = Files.readString(file.toPath());

        mal = MyAnimeList.withClientId(clientId, 5050);
    }

    // Anime

    @Test
    public void testAnimeSearch(){

    }

    @Test
    public void testAnime(){
        System.out.println(mal.getAnime(13759));
    }

    @Test
    public void testAnimeRank(){

    }

    @Test
    public void testAnimeSeason(){

    }

    @Test
    public void testUserAnimeListing(){

    }

    // Forum

    // Manga

    @Test
    public void testMangaSearch(){

    }

    @Test
    public void testManga(){

    }

    @Test
    public void testMangaRank(){

    }

    @Test
    public void testUserMangaListing(){

    }

    // User

}
