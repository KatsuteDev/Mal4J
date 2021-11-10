<div align="center">
    <a href="https://github.com/KatsuteDev/Mal4J#readme">
        <img src="https://raw.githubusercontent.com/KatsuteDev/Mal4J/main/banner.png" alt="Mal4J - MyAnimeList for Java">
    </a>
    <h3>Java wrapper for the official MyAnimeList API</h3>
    <div>
        <a href="https://docs.katsute.dev/mal4j">Docs</a>
        •
        <a href="https://myanimelist.net/apiconfig/references/api/v2">API Docs</a>
        •
        <a href="https://github.com/KatsuteDev/Mal4J/blob/main/setup.md">Setup</a>
        •
        <a href="https://github.com/KatsuteDev/Mal4J/blob/main/faq.md">FAQ</a>
        •
        <a href="https://github.com/KatsuteDev/Mal4J/issues">Issues</a>
        •
        <a href="https://github.com/KatsuteDev/Mal4J/discussions">Discussions</a>
        •
        <a href="https://myanimelist.net/forum/?topicid=1897569">Forum Post</a>
    </div>
</div>

<br>

<div align="center">
    <a href="https://github.com/KatsuteDev/Mal4J/actions/workflows/java_ci.yml"><img alt="Java CI" src="https://github.com/KatsuteDev/Mal4J/actions/workflows/java_ci.yml/badge.svg"></a>
    <a href="https://github.com/KatsuteDev/Mal4J/actions/workflows/mal_ci.yml"><img alt="MyAnimeList CI" src="https://github.com/KatsuteDev/Mal4J/actions/workflows/mal_ci.yml/badge.svg"></a>
    <a href="https://mvnrepository.com/artifact/com.kttdevelopment/mal4j"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/com.kttdevelopment/mal4j"></a>
    <a href="https://github.com/KatsuteDev/Mal4J/releases"><img alt="version" src="https://img.shields.io/github/v/release/KatsuteDev/Mal4J"></a>
    <a href="https://github.com/KatsuteDev/Mal4J/blob/main/LICENSE"><img alt="license" src="https://img.shields.io/github/license/KatsuteDev/Mal4J"></a>
</div>

# Overview

Mal4J is a modular wrapper for the MyAnimeList API written for Java 8+ and simplifies many complex operations into an easy to use library.

- [Overview](#overview)
- [Installation](#installation)
- [Features](#features)
- [Contributing](#contributing)
- [License](#license)
- [Disclaimer](#disclaimer)

<p align="right">(<a href="#readme">back to top</a>)</p>

# Installation

Mal4J requires at least Java 8. No additional dependencies/libraries are required.

Compiled jars can be found on [Maven Central](https://mvnrepository.com/artifact/com.kttdevelopment/mal4j) and the [releases](https://github.com/Katsute/Mal4J/releases) tab.

[![Maven Central](https://img.shields.io/maven-central/v/com.kttdevelopment/mal4j)](https://mvnrepository.com/artifact/com.kttdevelopment/mal4j) [![Releases](https://img.shields.io/github/v/release/Katsute/Mal4J)](https://github.com/Katsute/Mal4J/releases)

See [setup](https://github.com/KatsuteDev/Mal4J/blob/main/setup.md) for steps to authenticate and actually use this library.

<p align="right">(<a href="#readme">back to top</a>)</p>

# Features

### 🔎 Search Queries

Easily search through MyAnimeList with search, ranking, seasonal, and suggestion queries; returning only selected or all fields.

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

### 📋 List Modification
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

### 📦 Structured Objects

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

<p align="right">(<a href="#readme">back to top</a>)</p>

## Contributing

Code generated with GitHub Copilot is **strictly prohibited** on this repository. Do not bother sending pulls if you use this service.

- Found a bug? Post it in [issues](https://github.com/Katsute/Mal4J/issues).
- Have a suggestion or looking for inspiration? Check out our [discussions](https://github.com/Katsute/Mal4J/discussions).
- Want to further expand our project or site? [Fork](https://github.com/Katsute/Mal4J/fork) this repository and submit a [pull request](https://github.com/Katsute/Mal4J/pulls).

Tests will modify list status for <https://myanimelist.net/anime/13759> and <https://myanimelist.net/manga/28107>, make sure you fix your ratings after the test runs.

Ignore test failures for these known issues: [**⚠ External Issues**](https://github.com/Katsute/Mal4J/projects/10)

### Running Tests Locally

For local tests you can use Java 8+, however only methods in the Java 8 API may be used. The `src/main/java9` and `src/main/java11` folders should not be marked as a source root.

Run tests locally by adding a text file named `client.txt` that contains the client ID in the `src/test/java/resources` directory.

Please note that the client ID being used for tests must not have a client secret and must have an app redirect url of `http://localhost:5050`.

### Running Tests Remotely

Devs running remote tests may do so by running the `MyAnimeList CI` workflow manually in the actions tab of your fork. Note that this requires a secret `MAL_OAUTH` which contains the OAuth token (ex: `Bearer <oauth token>`).

<p align="right">(<a href="#readme">back to top</a>)</p>

---

### License

This library is released under the [GNU General Public License (GPL) v2.0](https://github.com/KatsuteDev/Mal4J/blob/main/LICENSE).

### Disclaimer

- [@Katsute](https://github.com/Katsute) and [@KatsuteDev](https://github.com/KatsuteDev) are not affiliated with MyAnimeList.
- By using the MyAnimeList API you are subject to their [Terms Of Service](https://myanimelist.net/static/apiagreement.html).

<p align="right">(<a href="#readme">back to top</a>)</p>