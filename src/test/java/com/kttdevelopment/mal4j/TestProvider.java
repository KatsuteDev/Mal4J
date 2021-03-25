package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

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

    // List

    public static String[] testTags(){
        return new String[]{"test_tag", "何"};
    }

    public static final String testComment = "if you see this then my test case has failed to run the cleanup method";

    //

    private static MyAnimeList mal;

    //

    static final File client = new File("src/test/java/resources/client.txt");
    static final File oauth  = new File("src/test/java/resources/oauth.txt");

    static {
        APICall.debug = false;
    }

    public static void init() throws IOException{
        if(oauth.exists()){ // use existing OAuth
            mal = MyAnimeList.withOAuthToken(strip(readFile(oauth)));
            if(mal.getAnime(AnimeID, Fields.NO_FIELDS) != null)
                return;
        }
        testRequireClientID(); // prevent CI from trying to authenticate
        TestAuthorizationLocalServer.beforeAll(); // refresh old token
    }

    public static void testRequireClientID(){
        Assumptions.assumeTrue(client.exists(), "File with Client ID was missing, please create a file with the Client ID at: " + client.getAbsolutePath());
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

    // java 9

    static String readFile(final File file) throws IOException{
        final List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        final StringBuilder OUT = new StringBuilder();
        for(final String s : lines)
            OUT.append(s);
        return OUT.toString();
    }

    // ^(\s+)|(\s+)$
    private static final Pattern dangling = Pattern.compile("^(\\s+)|(\\s+)$");

    static String strip(final String s){
        return dangling.matcher(s).replaceAll("");
    }

}
