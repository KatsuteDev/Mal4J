package com.kttdevelopment.myanimelist;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class TestProvider {

    // Anime

    public static final String AnimeQuery = "さくら荘のペットな彼女";
    public static final long AnimeID = 13759;

    public static final long AltAnimeID = 11757; // for tests that require additional fields

    public static final String NSFW_AnimeQuery = "いただきっセーエキ";
    public static final long NSFW_AnimeID = 22429;

    // Manga

    public static final String MangaQuery = "さくら荘のペットな彼女";
    public static final long MangaID = 28107;

    public static final String AltMangaQuery = "ソードアートオンライン";
    public static final long AltMangaID = 21479;

    public static final String NSFW_MangaQuery = "いただきっセーエキ";
    public static final long NSFW_MangaID = 49697;

    //

    private static MyAnimeList mal;

    //

    @SuppressWarnings("SpellCheckingInspection")
    static final Path client = new File("src/test/java/com/kttdevelopment/myanimelist/client.txt").toPath();
    @SuppressWarnings("SpellCheckingInspection")
    static final Path oauth  = new File("src/test/java/com/kttdevelopment/myanimelist/oauth.txt").toPath();

    public static void init() throws IOException{
        if(oauth.toFile().exists()){ // use existing OAuth
            mal = MyAnimeList.withOAuthToken(Files.readString(oauth));
            if(mal.getAnime().withQuery("さくら荘のペットな彼女").withLimit(1).withFields((List<String>) null).search() != null)
                TestAuthRefresh.beforeAll(); // refresh if old token
        }
        Assumptions.assumeTrue(client.toFile().exists(), "Skipping tests (client id missing)");
    }

    public static MyAnimeList getMyAnimeList(){
        try{
            init();
            return mal;
        }catch(final IOException e){
            e.printStackTrace();
            Assertions.fail();
            return null;
        }
    }

}
