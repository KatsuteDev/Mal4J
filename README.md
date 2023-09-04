<div id="top" align="center">
    <a href="https://github.com/KatsuteDev/Mal4J#readme">
        <img src="https://raw.githubusercontent.com/KatsuteDev/Mal4J/main/assets/banner.png" alt="Mal4J - MyAnimeList for Java">
    </a>
    <h3>Java wrapper for the official MyAnimeList API</h3>
    <div>
        <a href="https://docs.katsute.dev/mal4j">Documentation</a>
        •
        <a href="https://myanimelist.net/apiconfig/references/api/v2">API Docs</a>
        •
        <a href="https://github.com/KatsuteDev/Mal4J/blob/main/setup.md#readme">Setup</a>
        •
        <a href="https://github.com/KatsuteDev/Mal4J/blob/main/faq.md#readme">FAQ</a>
        •
        <a href="https://myanimelist.net/forum/?topicid=1897569">Forum Post</a>
    <br>
        <a href="https://mvnrepository.com/artifact/dev.katsute/mal4j">Maven Central</a>
        •
        <a href="https://github.com/KatsuteDev/Mal4J/packages/1769630">GitHub Packages</a>
        •
        <a href="https://github.com/KatsuteDev/Mal4J/releases">Releases</a>
    </div>
</div>

<br>

Mal4J is a Java wrapper for the [MyAnimeList](https://myanimelist.net/) API. This library is compatible with Java 8+, Java 9+ modules, and Android.

This library supports read and write operations, including list updates.

 - [📃 Installation](#-installation)
 - [✨ Features](#-features)
 - [👨‍💻 Contributing](#-contributing)
 - [💼 License](#-license)
 - [⚠️️ Disclaimer](#%EF%B8%8F%EF%B8%8F-disclaimer)

<div align="right"><a href="#top"><code>▲</code></a></div>

## 📃 Installation

Mal4J requires at least Java 8. No additional dependencies/libraries are required.

Compiled binaries can be installed from:

 - [Maven Central](https://mvnrepository.com/artifact/dev.katsute/mal4j)
 - [GitHub Packages](https://github.com/KatsuteDev/Mal4J/packages/1769630)
 - [Releases](https://github.com/KatsuteDev/Mal4J/releases)

See [setup](https://github.com/KatsuteDev/Mal4J/blob/main/setup.md#readme) for steps to authenticate and actually use this library.

<div align="right"><a href="#top"><code>▲</code></a></div>

## ✨ Features

#### 🔎 Search Queries

Find Anime and Manga by search, ranking, season, and suggestions.

```java
MyAnimeList mal = MyAnimeList.withClientID("");
List<Anime> search =
    mal.getAnime()
        .withQuery("さくら荘のペットな彼女")
        .withLimit(1)
        .withOffset(1)
        .includeNSFW(false)
        .search();
```

#### 📋 Anime and Manga Lists

Read and edit your Anime and Manga listings.

```java
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
```

#### 📦 Everything Else

**All** information provided in the [MyAnimeList API](https://myanimelist.net/apiconfig/references/api/v2) including Anime, Manga, forums, genres, pictures, statistics, and characters, are accessible in this library.

```java
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
```

<div align="right"><a href="#top"><code>▲</code></a></div>

## 👨‍💻 Contributing

<!-- Copilot -->
<table>
    <img alt="GitHub Copilot" align="left" src="https://raw.githubusercontent.com/Katsute/Manager/main/assets/copilot-dark.png#gh-dark-mode-only" width="50">
    <img alt="Open AI" align="left" src="https://raw.githubusercontent.com/Katsute/Manager/main/assets/openai-dark.png#gh-dark-mode-only" width="50">
    <img alt="GitHub Copilot" align="left" src="https://raw.githubusercontent.com/Katsute/Manager/main/assets/copilot-light.png#gh-light-mode-only" width="50">
    <img alt="Open AI" align="left" src="https://raw.githubusercontent.com/Katsute/Manager/main/assets/openai-light.png#gh-light-mode-only" width="50">
    <p>AI generated code is <b>strictly prohibited</b> on this repository.</p>
</table>
<!-- Copilot -->

#### ⚠️️ Before you start

For Anime/Manga list tests *Sakura-sou no Pet na Kanojo* ([Anime#13759](https://myanimelist.net/anime/13759) and [Manga#28107](https://myanimelist.net/manga/28107)) will be used.

The test cases will create a backup of your list in the `anime-list.txt` and `manga-list.txt` files. Make sure you fix your ratings and any other information that might be overwritten by this test.

Ignore test failures for these known issues: [**⚠️ External Issues**](https://github.com/KatsuteDev/Mal4J/projects/10).

#### 💻 Running Tests Locally

For local tests you can use Java 8+, however only methods in the Java 8 API may be used. The `src/main/java9` and `src/main/java11` folders should not be marked as a source root.

Run tests locally by adding a text file named `client.txt` that contains the client ID in the `src/test/java/resources` directory.

Please note that the client ID being used for tests must not have a client secret and must have an app redirect url of `http://localhost:5050`.

#### 🌐 Running Tests using GitHub Actions

Tests can be run using GitHub Actions by running the `MyAnimeList CI` workflow manually in the actions tab of your fork. Note that this requires two secrets, a `MAL_CLIENT` which contains the client ID, and a `MAL_TOKEN` which contains the OAuth token (ex: `Bearer <oauth token>`).

<div align="right"><a href="#top"><code>▲</code></a></div>

<hr>

### 💼 License

This library is released under the [GNU General Public License (GPL) v2.0](https://github.com/KatsuteDev/Mal4J/blob/main/LICENSE).

### ⚠️️ Disclaimer

 - [@Katsute](https://github.com/Katsute) and [@KatsuteDev](https://github.com/KatsuteDev) are not affiliated with MyAnimeList.
 - By using the MyAnimeList API you are subject to their [Terms Of Service](https://myanimelist.net/static/apiagreement.html).

<div align="right"><a href="#top"><code>▲</code></a></div>