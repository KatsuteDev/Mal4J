### Search Queries

Easily search through MyAnimeList with search, ranking, seasonal, and suggestion queries; returning only selected or all fields.

<hr>
```java
MyAnimeList mal = MyAnimeList.withOAuthToken("");
List<AnimePreview> search =
    mal.getAnime()
        .withQuery("さくら荘のペットな彼女")
        .withLimit(1)
        .withOffset(1)
        .includeNSFW(false)
        .search();
```
<hr>
### List Modification
Easily update your Anime and Manga listings through update methods.
<hr>
```java
MyAnimeList mal = MyAnimeList.withOAuthToken("");
MangaListStatus status = 
    mal.updateMangaListing(28107)
        .status(MangaStatus.Reading)
        .volumesRead(7)
        .chaptersRead(2)
        .rereading(false)
        .priority(2)
        .timesReread(0)
        .rereadValue(5)
        .tags("tags", "more tags")
        .comments("comments")
        .update();
```
<hr>
### Structured Objects

**All** information provided in the [MyAnimeList API](https://myanimelist.net/apiconfig/references/api/v2) including Anime, Manga, forums, genres, pictures, and statistics is accessible in this library. Effortlessly retrive any and all information you need.
<hr>
```java
MyAnimeList mal = MyAnimeList.withOAuthToken("");
Anime anime = mal.getAnime(13759);

String ja = anime.getAlternativeTitles().getJapanese();
Genre[] genres = anime.getGenres();
RelatedAnime[] relatedAnime = anime.getRelatedAnime();
AnimeRecommendation[] recs = anime.getRecommendations();
```