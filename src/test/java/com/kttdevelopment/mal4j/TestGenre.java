package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.property.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestGenre {

    // \Qlabel_for_genre-\E(\d+)">([a-zA-Z]+)\Q</label>\E$
    private static final Pattern label = Pattern.compile("\\Qlabel_for_genre-\\E(\\d+)\">([a-zA-Z]+)\\Q</label>\\E");

    @Test
    public void testAnimeGenres() throws IOException{
        final URL url = new URL("https://myanimelist.net/anime.php");

        final BufferedReader IN = new BufferedReader(new InputStreamReader(url.openStream()));
        final StringBuilder OUT = new StringBuilder();
        String ln;
        while((ln = IN.readLine()) != null)
            OUT.append(ln);
        IN.close();

        final String raw = OUT.toString();

        final Matcher matcher = label.matcher(raw);
        while(matcher.find()){
            Assertions.assertEquals(
                Integer.parseInt(matcher.group(1)), // expected ID
                Objects.requireNonNull(Genre.asEnum(matcher.group(2))).getAnimeGenreID(), //actual id
                "MyAnimeList Anime Genre ID doesn't match Genre enum"
            );
        }
    }

    @Test
    public void testMangaGenres() throws IOException{
        final URL url = new URL("https://myanimelist.net/manga.php");

        final BufferedReader IN = new BufferedReader(new InputStreamReader(url.openStream()));
        final StringBuilder OUT = new StringBuilder();
        String ln;
        while((ln = IN.readLine()) != null)
            OUT.append(ln);
        IN.close();

        final String raw = OUT.toString();

        final Matcher matcher = label.matcher(raw);
        while(matcher.find()){
            Assertions.assertEquals(
                Integer.parseInt(matcher.group(1)), // expected ID
                Objects.requireNonNull(Genre.asEnum(matcher.group(2))).getMangaGenreID(), //actual id
                "MyAnimeList Manga Genre ID doesn't match Genre enum"
            );
        }
    }

}
