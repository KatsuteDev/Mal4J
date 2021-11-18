package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.provider.Arguments;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
    private static boolean isTokenAuth = false;

    public static void requireToken(){
        Assumptions.assumeTrue(isTokenAuth, "Test requires a token");
    }

    static final File client = new File("src/test/java/resources/client.txt");
    static final File token  = new File("src/test/java/resources/token.txt");

    private static final boolean hasClient = client.exists();
    private static final boolean hasToken  = token.exists();

    static {
        APICall.debug = false;
        //noinspection ConstantConditions
        if(APICall.debug)
            System.out.println("\u001b[41;1m" + "\n                    WARNING: DEBUG MODE IS ON\n\n" + "\u001b[0m");
    }

    // this value should be TRUE if you are committing this file
    private static final boolean preferTokenAuth = true; // preferred auth when both files exist

    @SuppressWarnings("GrazieInspection")
    public static void init() throws IOException{
        if(
            hasClient || hasToken && // if has client or token file
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
        TestAuthorizationLocalServer.beforeAll(); // create token
    }

    public static void requireHuman(){ // skip test on CI
        Assumptions.assumeFalse("true".equals(System.getenv("CI")), "Test requires a human, CI testing not supported");
    }

    public static MyAnimeList getMyAnimeList(){
        try{
            init();
            return mal;
        }catch(final IOException e){
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Assertions.fail(Workflow.errorSupplier(sw.toString()));
            return null;
        }
    }

    // java 9

    static String readFile(final File file) throws IOException{
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
