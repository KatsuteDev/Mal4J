<p align="center">
    <a href="https://github.com/Katsute/Mal4J">
        <img src="https://raw.githubusercontent.com/Katsute/Mal4J/main/logo.png" alt="Logo" width="100" height="100">
    </a>
    <h3 align="center">Mal4J</h3>
    <p align="center">
        📘 Java wrapper for the official MyAnimeList API
        <br />
        <a href="https://mal4j.kttdevelopment.com/">Docs</a>
        •
        <a href="https://myanimelist.net/apiconfig/references/api/v2">API Docs</a>
        •
        <a href="https://github.com/Katsute/Mal4J/blob/main/setup.md">Setup</a>
        •
        <a href="https://github.com/Katsute/Mal4J/blob/main/faq.md">FAQ</a>
        •
        <a href="https://github.com/Katsute/Mal4J/issues">Issues</a>
        •
        <a href="https://github.com/Katsute/Mal4J/discussions">Discussions</a>
    </p>
</p>

<p align="center">
    <a href="https://github.com/Katsute/Mal4J/actions?query=workflow%3ADeploy"><img title="Deploy" src="https://github.com/Katsute/Mal4J/workflows/Deploy/badge.svg"></a>
    <a href="https://github.com/Katsute/Mal4J/actions?query=workflow%3A%22Java+CI%22"><img title="Java CI" src="https://github.com/Katsute/Mal4J/workflows/Java%20CI/badge.svg"></a>
    <a href="https://github.com/Katsute/Mal4J/actions?query=workflow%3A%22MyAnimeList+CI%22"><img title="MyAnimeList CI" src="https://github.com/Katsute/Mal4J/workflows/MyAnimeList%20CI/badge.svg"></a>
    <a href="https://mvnrepository.com/artifact/com.kttdevelopment/mal4j"><img title="Maven Central" src="https://img.shields.io/maven-central/v/com.kttdevelopment/mal4j"></a>
    <a href="https://github.com/Katsute/Mal4J/releases"><img title="version" src="https://img.shields.io/github/v/release/Katsute/Mal4J"></a>
    <a href="https://github.com/Katsute/Mal4J/blob/main/LICENSE"><img title="license" src="https://img.shields.io/github/license/Katsute/Mal4J"></a>
</p>

---

# Overview

Mal4J is a wrapper for the MyAnimeList API written for JDK 11 and simplifies many complex operations into an easy to use library.

# [Setup](https://github.com/Katsute/Mal4J/blob/main/setup.md)

Dependencies are hosted on Maven Central and compiled builds can be found in releases.

**Mal4J requires at least JDK 11.**

## Maven Central

Compiled binaries can be found on Maven Central → [![Maven Central](https://img.shields.io/maven-central/v/com.kttdevelopment/mal4j)](https://mvnrepository.com/artifact/com.kttdevelopment/mal4j)

## Local

For projects built locally, jars can be found in releases → [![Releases](https://img.shields.io/github/v/release/Katsute/Mal4J)](https://github.com/Katsute/Mal4J/releases)

Mal4J is a standalone library and requires no additional dependencies.


### More information on further stages of the setup can be found [here](https://github.com/Katsute/Mal4J/blob/main/setup.md).

# Features

### Search Queries

Easily search through MyAnimeList with search, ranking, seasonal, and suggestion queries; returning only selected or all fields.

```java
MyAnimeList mal = MyAnimeList.withOAuthToken("");
List<Anime> search =
    mal.getAnime()
        .withQuery("さくら荘のペットな彼女")
        .withLimit(1)
        .withOffset(1)
        .includeNSFW(false)
        .search();
```

### List Modification
Easily update your Anime and Manga listings through update methods.

```java
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
```

### Structured Objects

**All** information provided in the [MyAnimeList API](https://myanimelist.net/apiconfig/references/api/v2) including Anime, Manga, forums, genres, pictures, statistics, and even some *undocumented* fields are accessible in this library. Effortlessly retrieve any and all information you need.

```java
MyAnimeList mal = MyAnimeList.withOAuthToken("");
Anime anime = mal.getAnime(13759);

String ja = anime.getAlternativeTitles().getJapanese();
Genre[] genres = anime.getGenres();
RelatedAnime[] relatedAnime = anime.getRelatedAnime();
AnimeRecommendation[] recs = anime.getRecommendations();
OpeningTheme[] op = anime.getOpeningThemes();
```

---

## Contributing

For devs running tests locally simply add a text file named `client.txt` that contains the client ID in the `src/test/java/resources` directory.

Please note that the Client ID being used for tests must not have a client secret and must have an app redirect url of `http://localhost:5050`.

- Found a bug? Post it in [issues](https://github.com/Katsute/Mal4J/issues).
- Have a suggestion or looking for inspiration? Check out our [discussions](https://github.com/Katsute/Mal4J/discussions).
- Want to further expand our project or site? [Fork](https://github.com/Katsute/Mal4j/fork) this repository and submit a pull.

## Disclaimer
- [@Katsute](https://github.com/Katsute) and [Ktt&nbsp;Development](https://github.com/Ktt-Development) are not affiliated with MyAnimeList.
- By using the MyAnimeList API you are subject to their [Terms Of Service](https://myanimelist.net/static/apiagreement.html).
