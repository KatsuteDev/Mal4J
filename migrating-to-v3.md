# Migrating to v3

## ⚠️ Breaking Changes

 - Package was changed from `com.kttdevelopment.mal4j` to `dev.katsute.mal4j`.
 - Remove deprecated classes
    - `AndroidCompatibilityException`
 - Removed deprecated fields
    - `AnimeSource.Web_Manga` (use `AnimeSource.WebManga`)
    - `AnimeSource.Digital_Manga` (use `AnimeSource.DigitalManga`)
    - `MangaType.Novel` (use `MangaType.LightNovel`)
 - Remove deprecated methods
    - `MyAnimeList.withOAuthToken` (use `MyAnimeList.withToken`)
    - `MyAnimeList.withAuthorization` (use `MyAnimeList.withOAuth2`)
    - `MyAnimeList.refreshOAuthToken` (use `MyAnimeList.refreshToken`)
    - `MyAnimeList.getMyself` (use `MyAnimeList.getAuthenticatedUser`)
    - `MyAnimeListAuthenticator` `String` constructors (use `Authorization` constructors)
 - Moved exceptions from `dev.katsute.mal4j` to `dev.katsute.mal4j.exception`
 - Using an experimental feature without explicitly enabling it using `MyAnimeList.enableExperimentalFeature` will now throw an exception (previously would only log a warning).

## ⭐ New Features

 -

## 📋 How to Migrate

 - In your `pom.xml` file change the groupId `com.kttdevelopment` to `dev.katsute`.