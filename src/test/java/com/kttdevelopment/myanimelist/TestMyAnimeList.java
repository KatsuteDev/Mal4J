package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;
import com.kttdevelopment.myanimelist.manga.property.MangaType;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class TestMyAnimeList {

    private static MyAnimeList mal;

    @BeforeAll
    public static void beforeAll() throws IOException{
        final File file = new File("src/test/java/com/kttdevelopment/myanimelist/client.txt");
        Assumptions.assumeTrue(file.exists(), "Skipping tests (requires user authentication)");
        final String clientId = Files.readString(file.toPath());

        mal = MyAnimeList.withClientId(clientId, 5050);
    }

    // Anime

    @Test
    public void testAnimeSearch(){
        // test standard
        final long second;
        {
            final List<AnimePreview> search =
                mal.getAnime()
                    .withQuery("さくら荘のペットな彼女")
                    .search();
            Assertions.assertEquals(13759, search.get(0).getID());
            second = search.get(1).getID();
            Assertions.assertNotEquals(1, search.size());
        }
        // test offset & limit
        {
            final List<AnimePreview> search =
                mal.getAnime()
                    .withQuery("さくら荘のペットな彼女")
                    .withLimit(1)
                    .withOffset(1)
                    .search();
            Assertions.assertEquals(second, search.get(0).getID());
            Assertions.assertEquals(1, search.size());
        }
        // test limit bounds todo
        {

        }
        // test fields
        {
            final List<AnimePreview> search =
                mal.getAnime()
                    .withQuery("さくら荘のペットな彼女")
                    .withLimit(1)
                    .withFields(new String[0])
                    .search();
            Assertions.assertNull(search.get(0).getType());
        }
        // test NSFW todo
        {

        }
    }

    @Test
    public void testAnime(){
        final Anime anime = mal.getAnime(13759);
        Assertions.assertEquals(anime, anime.getAnime());

        Assertions.assertEquals(13759, anime.getID());
        Assertions.assertNotNull(anime.getTitle());
        Assertions.assertNotNull(anime.getMainPicture().getMediumURL());
        Assertions.assertNotNull(anime.getMainPicture().getLargeURL());
        Assertions.assertNotNull(anime.getAlternativeTitles().getEnglish());
        Assertions.assertNotNull(anime.getAlternativeTitles().getJapanese());
        Assertions.assertNotNull(anime.getAlternativeTitles().getSynonyms());
        Assertions.assertNotEquals(-1, anime.getStartDate());
        Assertions.assertNotEquals(-1, anime.getEndDate());
        Assertions.assertNotNull(anime.getSynopsis());
        Assertions.assertNotEquals(0, anime.getMeanRating());
        Assertions.assertNotEquals(0, anime.getRank());
        Assertions.assertNotEquals(0, anime.getPopularity());
        Assertions.assertNotEquals(0, anime.getUserListingCount());
        Assertions.assertNotEquals(0, anime.getUserScoringCount());
        Assertions.assertNotNull(anime.getNSFW());
        Assertions.assertNotNull(anime.getGenres());
        Assertions.assertNotEquals(-1, anime.getCreatedAt());
        Assertions.assertNotEquals(-1, anime.getUpdatedAt());
        Assertions.assertNotNull(anime.getType());
        Assertions.assertNotNull(anime.getStatus());
        // todo: list status
        {

        }
        Assertions.assertNotNull(anime.getStartSeason().getSeason());
        Assertions.assertNotEquals(-1, anime.getStartSeason().getYear());
        Assertions.assertNotNull(anime.getBroadcast().getDayOfWeek());
        Assertions.assertNotNull(anime.getBroadcast().getStartTime());
        Assertions.assertNotNull(anime.getSource());
        Assertions.assertNotEquals(-1, anime.getAverageEpisodeLength());
        Assertions.assertNotNull(anime.getRating());
        Assertions.assertNotEquals(-1, anime.getStudios()[0].getID());
        Assertions.assertNotNull(anime.getStudios()[0].getName());
        Assertions.assertNotNull(anime.getPictures()[0].getMediumURL());
        Assertions.assertNotNull(anime.getPictures()[0].getLargeURL());
        Assertions.assertNotNull(anime.getBackground());
        // todo: related
        {

        }
        // todo: recommendations
        {

        }
        // statistics
        {
            final AnimeStatistics statistics = anime.getStatistics();
            Assertions.assertNotEquals(0, statistics.getCompleted());
            Assertions.assertNotEquals(0, statistics.getDropped());
            Assertions.assertNotEquals(0, statistics.getOnHold());
            Assertions.assertNotEquals(0, statistics.getPlanToWatch());
            Assertions.assertNotEquals(0, statistics.getWatching());
            Assertions.assertNotEquals(0, statistics.getUserCount());
        }
    }

    @Test
    public void testAnimeRank(){
        // test standard
        {
            final List<AnimeRanking> ranking =
                mal.getAnimeRanking(AnimeRankingType.Movie)
                    .withLimit(1)
                    .search();
            final AnimeRanking first = ranking.get(0);
            Assertions.assertEquals(1,first.getRanking());
            Assertions.assertTrue(first.getPreviousRank() < 1);
            Assertions.assertEquals(AnimeType.Movie, first.getAnimePreview().getType());
        }
        // test NSFW todo
        {

        }
    }

    @Test // this test may fail if the Anime spans more than one airing season
    public void testAnimeSeason(){
        // test standard
        {
            final List<AnimePreview> season =
                mal.getAnimeSeason(2020, Season.Spring)
                    .withLimit(1)
                    .search();
            final AnimePreview anime = season.get(0);
            Assertions.assertEquals(2020, anime.getStartSeason().getYear());
            Assertions.assertEquals(Season.Spring, anime.getStartSeason().getSeason());
        }
        // test sort
        {
            final List<AnimePreview> season =
                mal.getAnimeSeason(2020, Season.Winter)
                    .withLimit(2)
                    .sortBy(AnimeSeasonSort.Score)
                    .search();
            final AnimePreview first = season.get(0);
            final AnimePreview second = season.get(1);
            Assertions.assertTrue(first.getMeanRating() > second.getMeanRating());
        }
        // test NSFW todo
        {

        }
    }

    @Test @Disabled
    public void testUserAnimeListing(){
        // todo
    }

    // Forum todo

    // Manga

    @Test
    public void testMangaSearch(){
        // test standard
        final long second;
        {
            final List<MangaPreview> search =
                mal.getManga()
                    .withQuery("ソードアートオンライン")
                    .search();

            Assertions.assertEquals(21479, search.get(0).getID());
            second = search.get(1).getID();
            Assertions.assertNotEquals(1, search.size());
        }
        // test offset & limit
        {
            final List<MangaPreview> search =
                mal.getManga()
                    .withQuery("ソードアートオンライン")
                    .withLimit(1)
                    .withOffset(1)
                    .search();
            Assertions.assertEquals(second, search.get(0).getID());
            Assertions.assertEquals(2, search.size());
        }
        // test limit bounds todo
        {

        }
        // test fields
        {
            final List<MangaPreview> search =
                mal.getManga()
                    .withQuery("ソードアートオンライン")
                    .withLimit(1)
                    .withFields(new String[0])
                    .search();
            Assertions.assertNull(search.get(0).getType());
        }
    }

    @Test
    public void testManga(){
        final Manga manga = mal.getManga(28107);
        Assertions.assertEquals(manga, manga.getManga());

        Assertions.assertEquals(28107, manga.getID());
        Assertions.assertNotNull(manga.getTitle());
        Assertions.assertNotNull(manga.getMainPicture().getMediumURL());
        Assertions.assertNotNull(manga.getMainPicture().getLargeURL());
        Assertions.assertNotNull(manga.getAlternativeTitles().getEnglish());
        Assertions.assertNotNull(manga.getAlternativeTitles().getJapanese());
        Assertions.assertNotNull(manga.getAlternativeTitles().getSynonyms());
        Assertions.assertNotEquals(-1, manga.getStartDate());
        Assertions.assertNotEquals(-1, manga.getEndDate());
        Assertions.assertNotNull(manga.getSynopsis());
        Assertions.assertNotEquals(0, manga.getMeanRating());
        Assertions.assertNotEquals(0, manga.getRank());
        Assertions.assertNotEquals(0, manga.getPopularity());
        Assertions.assertNotEquals(0, manga.getUserListingCount());
        Assertions.assertNotEquals(0, manga.getUserScoringCount());
        Assertions.assertNotNull(manga.getNSFW());
        Assertions.assertNotNull(manga.getGenres());
        Assertions.assertNotEquals(-1, manga.getCreatedAt());
        Assertions.assertNotEquals(-1, manga.getUpdatedAt());
        Assertions.assertNotNull(manga.getType());
        Assertions.assertNotNull(manga.getStatus());
        // todo: list status
        {

        }
        Assertions.assertNotEquals(0, manga.getVolumes());
        Assertions.assertNotEquals(0, manga.getChapters());
        Assertions.assertNotEquals(-1, manga.getAuthors()[0].getID());
        Assertions.assertNotNull(manga.getAuthors()[0].getFirstName());
        Assertions.assertNotNull(manga.getAuthors()[0].getLastName());
        Assertions.assertNotNull(manga.getAuthors()[0].getRole());
        Assertions.assertNotNull(manga.getPictures()[0].getMediumURL());
        Assertions.assertNotNull(manga.getPictures()[0].getLargeURL());
        Assertions.assertNotNull(manga.getBackground());
        // todo: related
        {

        }
        // todo: recommendations
        {

        }
        Assertions.assertNotEquals(-1, manga.getSerialization()[0].getID());
        Assertions.assertNotNull(manga.getSerialization()[0].getName());
        Assertions.assertNotNull(manga.getSerialization()[0].getRole());
    }

    @Test
    public void testMangaRank(){
        // test standard
        {
            final List<MangaRanking> ranking =
                mal.getMangaRanking(MangaRankingType.Manga)
                    .withLimit(1)
                    .search();
            final MangaRanking first = ranking.get(0);
            Assertions.assertEquals(1,first.getRanking());
            Assertions.assertTrue(first.getPreviousRank() < 1);
            Assertions.assertEquals(MangaType.Manga, first.getMangaPreview().getType());
        }
        // test NSFW todo
        {

        }
    }

    @Test @Disabled
    public void testUserMangaListing(){
        // todo
    }

    // User

    @Test @Disabled
    public void testUser(){
        // todo
    }

}
