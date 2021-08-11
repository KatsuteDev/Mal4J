package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import com.kttdevelopment.mal4j.property.Genre;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestGenre {

    // \Qlabel_for_genre-\E(\d+)">([a-zA-Z\- ]+)\Q</label>\E$
    @SuppressWarnings("SpellCheckingInspection")
    private static final Pattern label = Pattern.compile("\\Qlabel_for_genre-\\E(\\d+)\">([a-zA-Z\\- ]+)\\Q</label>\\E");

    private static Map<String,Integer> animeGenreIDs, mangaGenreIDs;

    @BeforeAll
    public static void beforeAll() throws IOException{
        // SSL issue; Skip test on Java 9 CI
        Assumptions.assumeTrue(
            !System.getenv().containsKey("CI") ||
            System.getProperty("java.version").charAt(0) != '9'
        , Workflow.warningSupplier("Skipped test on Java 9 CI due to SSL issue"));

        animeGenreIDs = pullGenres("https://myanimelist.net/anime.php");
        mangaGenreIDs = pullGenres("https://myanimelist.net/manga.php");
    }

    @Test
    private static Map<String,Integer> pullGenres(final String URL) throws IOException{
        final URL url = new URL(URL);

        final Map<String,Integer> map = new HashMap<>();

        final BufferedReader IN = new BufferedReader(new InputStreamReader(url.openStream()));
        final StringBuilder OUT = new StringBuilder();
        String ln;
        while((ln = IN.readLine()) != null)
            OUT.append(ln);
        IN.close();

        final String raw = OUT.toString();

        final Matcher matcher = label.matcher(raw);
        while(matcher.find())
            map.put(matcher.group(2), Integer.parseInt(matcher.group(1)));
        return map;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @ParameterizedTest
    @EnumSource(Genre.class)
    public void testAnimeGenre(final Genre genre){
        boolean hasAnimeID = false, hasMangaID = false;
        try{ genre.getAnimeGenreID();
            hasAnimeID = true;
        }catch(final UnsupportedOperationException ignored){ }
        try{ genre.getMangaGenreID();
            hasMangaID = true;
        }catch(final UnsupportedOperationException ignored){ }

        if(hasAnimeID)
            Assertions.assertEquals(genre, Genre.asAnimeGenre(animeGenreIDs.get(genre.getName())),
                                    Workflow.errorSupplier("Expected Anime genre ID to match web ID"));
        if(hasMangaID)
            Assertions.assertEquals(genre, Genre.asMangaGenre(mangaGenreIDs.get(genre.getName())),
                                    Workflow.errorSupplier("Expected Manga genre ID to match web ID"));
    }

}