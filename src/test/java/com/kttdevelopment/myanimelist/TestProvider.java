package com.kttdevelopment.myanimelist;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class TestProvider {

    // Anime

    public static String AnimeQuery = "さくら荘のペットな彼女";
    public static long AnimeID = 13759;

    public static long AltAnimeID = 11757; // for tests that require additional fields

    public static String NSFW_AnimeQuery = "いただきっセーエキ";
    public static long NSFW_AnimeID = 22429;

    // Manga

    public static String MangaQuery = "さくら荘のペットな彼女";
    public static long MangaID = 28107;

    public static String AltMangaQuery = "ソードアートオンライン";
    public static long AltMangaID = 21479;

    public static String NSFW_MangaQuery = "いただきっセーエキ";
    public static long NSFW_MangaID = 49697;

    //

    private static MyAnimeList mal;

    //

    private static final Path client = new File("src/test/java/com/kttdevelopment/myanimelist/client.txt").toPath();
    private static final Path oauth  = new File("src/test/java/com/kttdevelopment/myanimelist/oauth.txt").toPath();

    private static MyAnimeListAuthenticator authenticator;

    private static void init() throws IOException{
        if(oauth.toFile().exists()){ // use existing OAuth
            mal = MyAnimeList.withOAuthToken(Files.readString(oauth));
            if(mal.getAnime().withQuery("さくら荘のペットな彼女").search() != null)
                return; // create new auth only if above null
        }

        Assumptions.assumeTrue(client.toFile().exists(), "Skipping tests (client id missing)");
        final String clientId = Files.readString(client);
        authenticator = new MyAnimeListAuthenticator(clientId, null, 5050);
        mal = MyAnimeList.withAuthorization(authenticator);

        // test refresh auth
        Assertions.assertNotNull(mal.getAnime().withQuery("さくら荘のペットな彼女").search());
        mal.refreshOAuthToken();

        Files.write(oauth, authenticator.getAccessToken().getToken().getBytes(StandardCharsets.UTF_8));
    }

    public static MyAnimeList getMyAnimeList(){
        if(mal != null)
            return mal;
        else
            synchronized(TestProvider.class){
                if(mal == null)
                    try{
                        init();
                    }catch(final IOException e){
                        throw new UncheckedIOException(e);
                    }
                return mal;
            }
    }

}
