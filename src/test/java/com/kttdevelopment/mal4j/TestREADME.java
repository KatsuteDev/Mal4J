package com.kttdevelopment.mal4j;

import com.kttdevelopment.mal4j.anime.*;
import com.kttdevelopment.mal4j.anime.property.OpeningTheme;
import com.kttdevelopment.mal4j.manga.MangaListStatus;
import com.kttdevelopment.mal4j.manga.property.MangaStatus;
import com.kttdevelopment.mal4j.manga.property.RereadValue;
import com.kttdevelopment.mal4j.property.Genre;
import com.kttdevelopment.mal4j.property.Priority;

import java.io.IOException;
import java.util.List;

// this tests only that code compiles
@SuppressWarnings("unused")
public class TestREADME {

    public void testSearchQueries(){
        MyAnimeList mal = MyAnimeList.withOAuthToken("");
        List<Anime> search =
            mal.getAnime()
                .withQuery("さくら荘のペットな彼女")
                .withLimit(1)
                .withOffset(1)
                .includeNSFW(false)
                .search();
    }

    public void testListModification(){
        MyAnimeList mal = MyAnimeList.withOAuthToken("");
        MangaListStatus status =
            mal.updateMangaListing(28107)
                .status(MangaStatus.Reading)
                .volumesRead(7)
                .chaptersRead(2)
                .rereading(false)
                .priority(Priority.High)
                .timesReread(0)
                .rereadValue(RereadValue.VeryHigh)
                .tags("tags", "more tags")
                .comments("comments")
                .update();
    }

    public void testStructuredObjects(){
        MyAnimeList mal = MyAnimeList.withOAuthToken("");
        Anime anime = mal.getAnime(13759);

        String ja = anime.getAlternativeTitles().getJapanese();
        Genre[] genres = anime.getGenres();
        RelatedAnime[] relatedAnime = anime.getRelatedAnime();
        AnimeRecommendation[] recs = anime.getRecommendations();
        OpeningTheme[] op = anime.getOpeningThemes();
    }

    // setup

    public void testAdvancedOAuth(){
        MyAnimeList mal = MyAnimeList.withOAuthToken("oauth_token");
    }

    public void testClientAuth(){
        String authorization_url = MyAnimeListAuthenticator.getAuthorizationURL("client_id", "PKCE_code_challenge");

        MyAnimeList mal = MyAnimeList.withAuthorization(new MyAnimeListAuthenticator("client_id", "client_secret", "authorization_code", "PKCE_code_challenge"));
    }

    public void testEZAuth() throws IOException{
        MyAnimeList mal = MyAnimeList.withAuthorization(new MyAnimeListAuthenticator.LocalServerBuilder("client_id", "client_secret", 5050).openBrowser().build());
    }

}
