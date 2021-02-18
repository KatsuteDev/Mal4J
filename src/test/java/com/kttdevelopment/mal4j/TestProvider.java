package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class TestProvider {

    // Anime

    public static final String AnimeQuery = "さくら荘のペットな彼女";
    public static final long AnimeID = 13759;

    public static final String AltAnimeQuery = "ソードアートオンライン"; // for tests that require additional fields
    public static final long AltAnimeID = 11757; // for tests that require additional fields

    public static final String NSFW_AnimeQuery = "いただきっセーエキ";
    public static final long NSFW_AnimeID = 22429;
    public static final long AltNSFW_AnimeID = 23779;

    // Manga

    public static final String MangaQuery = "さくら荘のペットな彼女";
    public static final long MangaID = 28107;

    public static final String AltMangaQuery = "ソードアートオンライン"; // for tests that require additional fields
    public static final long AltMangaID = 21479; // for tests that require additional fields

    public static final String NSFW_MangaQuery = "いただきっセーエキ";
    public static final long NSFW_MangaID = 49697;

    //

    private static MyAnimeList mal;

    //

    static final Path client = new File("src/test/java/resources/client.txt").toPath();
    static final Path oauth  = new File("src/test/java/resources/oauth.txt").toPath();

    public static void init() throws IOException{
        APICall.debug = false;
        if(oauth.toFile().exists()){ // use existing OAuth
            mal = MyAnimeList.withOAuthToken(Files.readString(oauth));
            if(mal.getAnime(AnimeID, Fields.NO_FIELDS) != null)
                return;
        }
        testRequireClientID(); // prevent CI from executing tests
        TestAuthorizationLocalServer.beforeAll(); // refresh old token
    }

    public static void testRequireClientID(){
        Assumptions.assumeTrue(client.toFile().exists(), "File with Client ID was missing, please create a file with the Client ID at: " + client.toFile().getAbsolutePath());
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
