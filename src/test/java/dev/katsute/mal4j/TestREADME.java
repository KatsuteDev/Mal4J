package dev.katsute.mal4j;

import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.anime.AnimeRecommendation;
import dev.katsute.mal4j.anime.RelatedAnime;
import dev.katsute.mal4j.anime.property.OpeningTheme;
import dev.katsute.mal4j.anime.property.Video;
import dev.katsute.mal4j.character.Character;
import dev.katsute.mal4j.manga.MangaListStatus;
import dev.katsute.mal4j.manga.property.MangaStatus;
import dev.katsute.mal4j.manga.property.RereadValue;
import dev.katsute.mal4j.people.Person;
import dev.katsute.mal4j.property.Genre;
import dev.katsute.mal4j.property.Priority;

import java.io.IOException;
import java.util.List;

// this tests only that code compiles
@SuppressWarnings("unused")
abstract class TestREADME {

    public void testSearchQueries(){
        MyAnimeList mal = MyAnimeList.withClientID("");
        List<Anime> search =
            mal.getAnime()
                .withQuery("さくら荘のペットな彼女")
                .withLimit(1)
                .withOffset(1)
                .includeNSFW(false)
                .search();
    }

    public void testListModification(){
        MyAnimeList mal = MyAnimeList.withToken("");
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
        MyAnimeList mal = MyAnimeList.withClientID("");
        Anime anime = mal.getAnime(13759);

        String ja = anime.getAlternativeTitles().getJapanese();
        Genre[] genres = anime.getGenres();
        RelatedAnime[] relatedAnime = anime.getRelatedAnime();
        AnimeRecommendation[] recs = anime.getRecommendations();
        OpeningTheme[] op = anime.getOpeningThemes();
        Video[] PV = anime.getVideos();
        List<Character> characters = anime.getCharacters().search();

        Character character = mal.getCharacter(61371);
        Person person = mal.getPerson(10765);
    }

    // setup

    public void testClient(){
        MyAnimeList mal = MyAnimeList.withClientID("client_id");
    }

    public void testToken(){
        MyAnimeList mal = MyAnimeList.withToken("Bearer oauth_token");
    }

    public void testOAuth2(){
        String authorization_url = MyAnimeListAuthenticator.getAuthorizationURL("client_id", "PKCE_code_challenge");

        MyAnimeListAuthenticator authenticator = new MyAnimeListAuthenticator(new Authorization("client_id", "client_secret", "authorization_code", "PKCE_code_challenge"));
        MyAnimeList mal = MyAnimeList.withOAuth2(authenticator);
    }

    public void testEZAuth() throws IOException{
        MyAnimeListAuthenticator authenticator = new MyAnimeListAuthenticator
            .LocalServerBuilder("client_id", "client_secret", 5050)
            .openBrowser()
            .build();
        MyAnimeList mal = MyAnimeList.withOAuth2(authenticator);
    }

}