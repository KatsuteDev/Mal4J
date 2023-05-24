package dev.katsute.mal4j;

import dev.katsute.mal4j.auth.TestLocalServerToken;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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

    public static final String testComment = "if you see this then a test case has failed to run the cleanup method";

    // Character

    public static final long CharacterID = 61371;
    public static final long AltCharacterID = 36828; // for tests that require additional fields
    public static final long NSFW_CharacterID = 104119;

    // People

    public static final long PersonID = 10765;
    public static final long AltPersonID = 34785; // for tests that require additional fields

    //

    private static MyAnimeList mal;
    private static boolean isTokenAuth = false;

    public static void requireToken(){
        Assumptions.assumeTrue(isTokenAuth, "Test requires a token");
    }

    public static final File client = new File("src/test/java/resources/client.txt");
    public static final File token  = new File("src/test/java/resources/token.txt");

    private static final boolean hasClient = client.exists();
    private static final boolean hasToken  = token.exists();

    static {
        final boolean debug = false; // this value should be FALSE if you are committing this file
        MyAnimeList.setDebug(debug);
        //noinspection ConstantConditions
        if(debug)
            System.out.println("\u001b[41;1m" + "\n                    WARNING: DEBUG MODE IS ON\n\n" + "\u001b[0m");
    }

    // this value should be TRUE if you are committing this file
    private static final boolean preferTokenAuth = true; // preferred auth when both files exist

    public static void init() throws IOException{
        if(
            (hasClient || hasToken) && // if has client or token file
            (
                (hasClient && (!hasToken || !preferTokenAuth) && // if has client file or both but prefers client file
                (mal = MyAnimeList.withClientID(strip(readFile(client)))) != null) // and client id was valid
                || // OR
                (hasToken && (!hasClient || preferTokenAuth) && // if has token file or both but prefers token file
                (mal = MyAnimeList.withToken(strip(readFile(token)))) != null) // and token was valid
            )
        ){
            isTokenAuth = hasClient && hasToken // if has both
                        ? preferTokenAuth // use preferred
                        : hasToken; // use token if exists, otherwise use client
            return; // authenticated successfully
        }

        requireHuman(); // prevent CI from trying to authenticate
        TestLocalServerToken.beforeAll(); // create token
    }

    public static void requireHuman(){ // skip test on CI
        Assumptions.assumeFalse("true".equalsIgnoreCase(System.getenv("CI")), "Test requires a human, CI testing not supported");
    }

    public static MyAnimeList getMyAnimeList(){
        try{
            init();
            return mal;
        }catch(final IOException e){
            fail(e);
            return null;
        }
    }

    // java 9

    public static String readFile(final File file) throws IOException{
        final StringBuilder OUT = new StringBuilder();
        for(final String s : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8))
            OUT.append(s);
        return OUT.toString();
    }

    // ^(\s+)|(\s+)$
    private static final Pattern dangling = Pattern.compile("^(\\s+)|(\\s+)$");

    static String strip(final String s){
        return dangling.matcher(s).replaceAll("");
    }

    //

    public static final class MethodStream<T> {

        private final List<Arguments> args = new ArrayList<>();

        public final MethodStream<T> add(final Function<T,Object> function){
            args.add(Arguments.of(function));
            return this;
        }

        public final MethodStream<T> add(final String method, final Function<T,Object> function){
            args.add(Arguments.of(method, function));
            return this;
        }

        public final Stream<Arguments> stream(){
            return args.stream();
        }

    }

    public static final class ObjectStream {

        private final List<Arguments> args = new ArrayList<>();

        public final ObjectStream add(final Object... object){
            args.add(Arguments.of(object));
            return this;
        }

        public final Stream<Arguments> stream(){
            return args.stream();
        }

    }

}