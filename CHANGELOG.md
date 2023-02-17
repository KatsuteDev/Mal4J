# Changelog

## 3.0.2

### 🔧 Optimizations

* Schema parsing optimizations [#363](https://github.com/KatsuteDev/Mal4J/pull/363) ([@Katsute](https://github.com/Katsute))
* JSON parsing optimizations [#364](https://github.com/KatsuteDev/Mal4J/pull/364) ([@Katsute](https://github.com/Katsute))
  This library is now 70% faster after this change.

### 📘 Dependencies

* Bump maven-surefire-plugin from 3.0.0-M8 to 3.0.0-M9 [#365](https://github.com/KatsuteDev/Mal4J/pull/365) ([@dependabot](https://github.com/dependabot))
* Bump maven-javadoc-plugin from 3.4.1 to 3.5.0 [#366](https://github.com/KatsuteDev/Mal4J/pull/366) ([@dependabot](https://github.com/dependabot))

**Full Changelog**: [`3.0.1...3.0.2`](https://github.com/KatsuteDev/Mal4J/compare/3.0.1...3.0.2)

## 3.0.1

### 🐞 Bug Fixes

* Fix NullableDate [#360](https://github.com/KatsuteDev/Mal4J/pull/360) ([@Katsute](https://github.com/Katsute))

### 📘 Dependencies

* Bump maven-surefire-plugin from 3.0.0-M7 to 3.0.0-M8 [#359](https://github.com/KatsuteDev/Mal4J/pull/359) ([@dependabot](https://github.com/dependabot))

**Full Changelog**: [`3.0.0...3.0.1`](https://github.com/KatsuteDev/Mal4J/compare/3.0.0...3.0.1)

## 3.0.0

### ⚠️ Breaking Change

* Change groupid [#355](https://github.com/KatsuteDev/Mal4J/pull/355) ([@Katsute](https://github.com/Katsute))
  <h3 align="center">🛑 This major update includes breaking changes 🛑</h3>

   * Changed groupid from `com.kttdevelopment` to `dev.katsute`
   * Changed package from `com.kttdevelopment` to `dev.katsute`
   * Moved exceptions from `com.kttdevelopment.mal4j` to `dev.katsute.mal4j.exceptions`
   * Anime/Manga start/end dates now return `NullableDate` instead of a `Date`

  To bring your code up to date:

   * Change the dependency groupid from `com.kttdevelopment` to `dev.katsute`
   * Replace all `import com.kttdevelopment.mal4j` with `import dev.katsute.mal4j`
   * Exceptions are now imported from `dev.katsute.mal4j.exceptions`, fix this import manually

### 📘 Dependencies

* Bump junit-jupiter from 5.9.1 to 5.9.2 [#358](https://github.com/KatsuteDev/Mal4J/pull/358) ([@mashiro-san](https://github.com/mashiro-san))

**Full Changelog**: [`2.12.0...3.0.0`](https://github.com/KatsuteDev/Mal4J/compare/2.12.0...3.0.0)

## 2.12.0

### ⚠️ Breaking Change

* Drop use of 'Preview' classes [#353](https://github.com/KatsuteDev/Mal4J/pull/353) ([@Katsute](https://github.com/Katsute))
  Any methods that previously returned `AnimePreview` and `MangaPreview` now instead returns `Anime` and `Manga`. There is no need to call `getAnime` and `getManga` to get all details, all details are now returned by default.

  Simply change any usage of `AnimePreview` to `Anime` and `MangaPreview` to `Manga`.

  * Removed `getAnimePreview` and `getMangaPreview`
  * Removed redundant `getAnime` and `getManga` on certain classes

**Full Changelog**: [`2.11.0...2.12.0`](https://github.com/KatsuteDev/Mal4J/compare/2.11.0...2.12.0)

## 2.11.0

### ⚠️ Breaking Change

* Using experimental features without enabling them will now throw an exception [#346](https://github.com/KatsuteDev/Mal4J/pull/346) ([@Katsute](https://github.com/Katsute))
  * Using an experimental feature without enabling it will throw a `ExperimentalFeatureException`, previously would only print a warning

### ❌ Removed

* Remove deprecated authentication methods [#345](https://github.com/KatsuteDev/Mal4J/pull/345) ([@Katsute](https://github.com/Katsute))
  * Removed `withOAuthToken`, use `withToken`
  * Removed `refreshOAuthToken`, use `refreshToken`
  * Removed `withAuthorization`, use `withOAuth2`
  * The deprecated String parameter has been replaced with the Authorization parameter.
    Previously:
    ```java
    new MyAnimeListAuthenticator("client_id", "client_secret", "authorization_code", "PKCE_code_challenge");
    ```
    Should be replaced with:
     ```java
     new MyAnimeListAuthenticator(new Authorization("client_id", "client_secret", "authorization_code", "PKCE_code_challenge"));
     ```
     If you were not already using this new object before, an access token can be passed to this method to use an existing token, rather than generating a new one:
     ```java
     new MyAnimeListAuthenticator(new Authorization(...), new AccessToken("access_token");
     new MyAnimeListAuthenticator(new Authorization(...), new AccessToken("access_token", "refresh_token");
     new MyAnimeListAuthenticator(new Authorization(...), new AccessToken("access_token", "refresh_token", 1640995200);
     ```

### 📘 Dependencies

* Bump maven-jar-plugin from 3.2.2 to 3.3.0 [#344](https://github.com/KatsuteDev/Mal4J/pull/344) ([@dependabot](https://github.com/dependabot))

**Full Changelog**: [`2.10.0...2.11.0`](https://github.com/KatsuteDev/Mal4J/compare/2.10.0...2.11.0)

## 2.10.0

This update adds support for Anime promotional videos (PVs) and trailers. Use `Anime.getVideos()` to retrieve all PVs.

### ❌ Removed

* Removed deprecated enums https://github.com/KatsuteDev/Mal4J/pull/338 (@Katsute)
   - Removed `AnimeSource.Web_Manga`, use `AnimeSource.WebManga`
   - Removed `AnimeSource.Digital_Manga`, use `AnimeSource.DigitalManga`
   - Removed `MangaType.Novel`, use `MangaType.LightNovel`
* Removed deprecated method `MyAnimeList.getMyself` https://github.com/KatsuteDev/Mal4J/pull/339 (@Katsute)
  Use `MyAnimeList.getAuthenticatedUser`
* Removed deprecated AndroidCompatibilityException https://github.com/KatsuteDev/Mal4J/pull/340 (@Katsute)

### ⭐ New Features

* Added Anime PV/Trailers https://github.com/KatsuteDev/Mal4J/pull/341 (@Katsute)
   * Retrieve Anime PVs/Trailers using `Anime.getVideos()`, returns an array
   * Added corresponding field variable `videos` or `Fields.Anime.videos`
   * Added corresponding experimental field `ExperimentalFeature.VIDEOS`
* Paginated iterator now returns a modifiable list instead of an unmodifiable one https://github.com/KatsuteDev/Mal4J/pull/337 (@Katsute)
* Added `All` option for experimental features https://github.com/KatsuteDev/Mal4J/pull/342 (@Katsute)
   * Enable all experimental features using `MyAnimeList.enableExperimentalFeature(ExperimentalFeature.ALL)`

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.9.1...2.10.0

## 2.9.1

### 🐞 Bug Fixes

* Add missing Manga publishing status `discontinued` https://github.com/KatsuteDev/Mal4J/pull/334 (@Katsute)

### 📘 Dependencies

* Bump maven-javadoc-plugin from 3.4.0 to 3.4.1 https://github.com/KatsuteDev/Mal4J/pull/330 (@dependabot)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.9.0...2.9.1

## 2.9.0

### ⭐ New Features

* Enum changes and fixes https://github.com/KatsuteDev/Mal4J/pull/329 (@Katsute)
  * Methods that have an enum parameter can now also accept string field parameters
  * Methods that return enums can now also return as a string
  * Fix asEnum throwing a NullPointerException

### 📘 Dependencies

* Bump junit-jupiter-params and junit-jupiter-api from 5.8.2 to 5.9.0 https://github.com/KatsuteDev/Mal4J/pull/328 (@mashiro-san)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.8.2...2.9.0

## 2.8.2

### 🐞 Bug Fixes

* Add missing relation types https://github.com/KatsuteDev/Mal4J/pull/320 (@Katsute)
  * Add missing relation type Spin off
  * Add missing relation type Character
  * For unknown relation type return Unknown rather than null

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.8.1...2.8.2

## 2.8.1

### 🐞 Bug Fixes

* Fix visual novel Anime source https://github.com/KatsuteDev/Mal4J/pull/315 (@Katsute)

### 📘 Dependencies

* Bump maven-surefire-plugin from 3.0.0-M6 to 3.0.0-M7 https://github.com/KatsuteDev/Mal4J/pull/313 (@dependabot)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.8.0...2.8.1

## 2.8.0

### ⚠️ Breaking Change

* Rename Anime source Digital_Manga to DigitalManga https://github.com/KatsuteDev/Mal4J/pull/312 (@Katsute)
* Rename Anime source Web_Manga to WebManga https://github.com/KatsuteDev/Mal4J/pull/312 (@Katsute)
* All Anime/Manga enums now fallback to Unknown rather than _null_ when field is unknown https://github.com/KatsuteDev/Mal4J/pull/312 (@Katsute)

### 🐞 Bug Fixes

* Add missing Manga status Hiatus https://github.com/KatsuteDev/Mal4J/pull/312 (@Katsute)
* Add missing Anime source Mixed Media & Web Novel https://github.com/KatsuteDev/Mal4J/pull/312 (@Katsute)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.7.4...2.8.0

## 2.7.4

### 🐞 Bug Fixes

* Fix modularity for Java 9-10 https://github.com/KatsuteDev/Mal4J/pull/310 (@Katsute)

### 📘 Dependencies

* Bump jcore from 2.0.0 to 2.0.1 https://github.com/KatsuteDev/Mal4J/pull/308 (@dependabot)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.7.3...2.7.4

## 2.7.3

### 📘 Dependencies

* Bump maven-javadoc-plugin from 3.3.1 to 3.3.2 https://github.com/KatsuteDev/Mal4J/pull/293 (@dependabot)
* Bump maven-compiler-plugin from 3.9.0 to 3.10.0 https://github.com/KatsuteDev/Mal4J/pull/294 (@dependabot)
* Bump actions/setup-java from 2.5.0 to 3 https://github.com/KatsuteDev/Mal4J/pull/298 (@dependabot)
* Bump actions/checkout from 2.4.0 to 3 https://github.com/KatsuteDev/Mal4J/pull/299 (@dependabot)
* Bump maven-compiler-plugin from 3.10.0 to 3.10.1 https://github.com/KatsuteDev/Mal4J/pull/300 (@dependabot)
* Bump maven-surefire-plugin from 3.0.0-M5 to 3.0.0-M6 https://github.com/KatsuteDev/Mal4J/pull/301 (@dependabot)
* Bump maven-javadoc-plugin from 3.3.2 to 3.4.0 https://github.com/KatsuteDev/Mal4J/pull/306 (@dependabot)
* Bump nexus-staging-maven-plugin from 1.6.8 to 1.6.13 https://github.com/KatsuteDev/Mal4J/pull/307 (@dependabot)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.7.2...2.7.3

## 2.7.2

### 🧰 Internal

* Removed unused code https://github.com/KatsuteDev/Mal4J/pull/284 (@Katsute)
* Relocate and remove Java 9 methods https://github.com/KatsuteDev/Mal4J/pull/288 (@Katsute)

### 📘 Dependencies

* Bump actions/setup-java from 2.4.0 to 2.5.0 https://github.com/KatsuteDev/Mal4J/pull/280 (@dependabot)
* Bump maven-jar-plugin from 3.2.0 to 3.2.1 https://github.com/KatsuteDev/Mal4J/pull/285 (@dependabot)
* Bump maven-jar-plugin from 3.2.1 to 3.2.2 https://github.com/KatsuteDev/Mal4J/pull/286 (@dependabot)
* Bump maven-compiler-plugin from 3.8.1 to 3.9.0 https://github.com/KatsuteDev/Mal4J/pull/287 (@dependabot)
* Bump jcore from 1.3.0 to 2.0.0 https://github.com/KatsuteDev/Mal4J/pull/290 (@Katsute)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.7.1...2.7.2

## 2.7.1

### 🐞 Bug Fixes

* Fix random newlines appearing in log https://github.com/KatsuteDev/Mal4J/pull/278 (@Katsute)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.7.0...2.7.1

## 2.7.0

### ⭐ New Features

* Search for public Anime/Manga lists using only client id https://github.com/KatsuteDev/Mal4J/pull/266 (@Katsute)
* Add additional authentication methods https://github.com/KatsuteDev/Mal4J/pull/270 (@Katsute)
   - Rename `MyAnimeList.withAuthorization(...)` to `MyAnimeList.withOAuth2(...)`.
   - Replace `MyAnimeListAuthenticator` parameters with a single `Authorization` class.
     ```java
     new MyAnimeListAuthenticator("client_id", "client_secret", "authorization_code", "PKCE_code_challenge");
     ```
     ```java
     new MyAnimeListAuthenticator(new Authorization("client_id", "client_secret", "authorization_code", "PKCE_code_challenge"));
     ```
   - Fix #269 - Incorrect expiry date for `AccessToken`.
   - Add optional `AccessToken` parameter for `MyAnimeListAuthenticator`.
     ```java
     new MyAnimeListAuthenticator(new Authorization(...), new AccessToken("access_token");

     new MyAnimeListAuthenticator(new Authorization(...), new AccessToken("access_token", "refresh_token");

     new MyAnimeListAuthenticator(new Authorization(...), new AccessToken("access_token", "refresh_token", 1640995200);
     ```
  - Add method to retrieve expiry as seconds since epoch.
    ```java
    new AccessToken("access_token", "refresh_token", 1640995200).getExpiryEpochSeconds();
    ```
   - Deprecate old authenticator constructors.
   - Add warning when using authorization URL in place of the authorization code.
   - Add method to generate PKCE.
     ```java
     MyAnimeListAuthenticator.generatePKCE(128);
     ```
* Add warning when using deprecated classes/methods https://github.com/KatsuteDev/Mal4J/pull/276 (@Katsute)

### 🐞 Bug Fixes

* Fix Anime/Manga list ID sometimes being null https://github.com/KatsuteDev/Mal4J/pull/268 (@Katsute)

### 📄 Documentation

* Remove rare exceptions from documentation https://github.com/KatsuteDev/Mal4J/pull/277 (@Katsute)

### 🧰 Internal

* Adjust warning messages https://github.com/KatsuteDev/Mal4J/pull/263 (@Katsute)
* Code quality fixes https://github.com/KatsuteDev/Mal4J/pull/264 (@Katsute)
* Add debug mode https://github.com/KatsuteDev/Mal4J/pull/265 (@Katsute)

### 📘 Dependencies

* Bump junit-jupiter from 5.8.1 to 5.8.2 https://github.com/KatsuteDev/Mal4J/pull/274
* Bump actions/setup-java from 2.3.1 to 2.4.0 https://github.com/KatsuteDev/Mal4J/pull/271 (@dependabot)

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.6.0...2.7.0

## 2.6.0

### ⚠️ Breaking Change

* Transfer repository to @KatsuteDev https://github.com/KatsuteDev/Mal4J/pull/258 (@Katsute)

  This repository has moved from [Katsute/Mal4J](https://github.com/Katsute/Mal4J) to [KatsuteDev/Mal4J](https://github.com/KatsuteDev/Mal4J), see this discussion post for more details: https://github.com/KatsuteDev/Mal4J/discussions/230

* Rename token methods https://github.com/KatsuteDev/Mal4J/pull/257 (@Katsute)

  Some token auth methods have been renamed for simplicity, the old methods have been deprecated.

  `MyAnimeList.withOAuthToken(String)` is now `MyAnimeList.withToken(String)`

  `MyAnimeList.refreshOAuthToken()` is now `MyAnimeList.refreshToken()`

### ⭐ New Features

* Ability to authenticate using client ID https://github.com/KatsuteDev/Mal4J/pull/257 (@Katsute)

  The MyAnimeList API now support authentication using only the client ID, see this [forum post](https://myanimelist.net/forum/?topicid=1973077&show=0#msg64957586).

* Deploy to GitHub Packages https://github.com/KatsuteDev/Mal4J/pull/231 (@Katsute)

  This project is now available on [GitHub Packages](https://github.com/KatsuteDev/Mal4J/packages)!

**Full Changelog**: https://github.com/KatsuteDev/Mal4J/compare/2.5.1...2.6.0

## 2.5.1

### 🐞 Bug Fixes

* Fix issue with null priority and rewatch/reread value https://github.com/Katsute/Mal4J/pull/255 (@Katsute)

### 📘 Dependencies

* Bump actions/checkout from 2.3.5 to 2.4.0 https://github.com/Katsute/Mal4J/pull/253 (@dependabot)

**Full Changelog**: https://github.com/Katsute/Mal4J/compare/2.5.0...2.5.1

## 2.5.0

### 🐞 Bug Fixes

* Fix PKCE accepting disallowed characters https://github.com/Katsute/Mal4J/pull/250 (@Katsute)
* Fix token generation when using `redirect_uri` https://github.com/Katsute/Mal4J/pull/251 (@Katsute)
* Fix token generation sometimes having empty error message https://github.com/Katsute/Mal4J/pull/252 (@Katsute)

### 🧰 Internal

* Update CI to Java 17 (LTS) https://github.com/Katsute/Mal4J/pull/237 (@Katsute)
* Enforce timeouts on workflows https://github.com/Katsute/Mal4J/pull/240 (@Katsute)
* Migrate to reusable workflows https://github.com/Katsute/Mal4J/pull/243 (@Katsute)

### 📘 Dependencies

* Bump junit-jupiter-params from 5.8.0 to 5.8.1 https://github.com/Katsute/Mal4J/pull/239 (@dependabot)
* Bump junit-jupiter-api from 5.8.0 to 5.8.1 https://github.com/Katsute/Mal4J/pull/238 (@dependabot)
* Bump actions/setup-java from 2.3.0 to 2.3.1 https://github.com/Katsute/Mal4J/pull/241 (@dependabot)
* Bump jcore from 1.2.0 to 1.3.0 https://github.com/Katsute/Mal4J/pull/242 (@dependabot)
* Bump actions/checkout from 2.3.4 to 2.3.5 https://github.com/Katsute/Mal4J/pull/247 (@dependabot)

**Full Changelog**: https://github.com/Katsute/Mal4J/compare/2.4.0...2.5.0

## 2.4.0

### ⚠️ Breaking Change
**MyAnimeList has changed genres, see this forum post: https://myanimelist.net/forum/?topicid=1956762.**
- Fix MyAnimeList genre changes → Genre has been changed from an enum to a class #236 (@Katsute)

### ⭐ New Features
- Multiple exception changes (see pull) #224 (@Katsute)
- (android) Use `X-HTTP-Method-Override` when PATCH is not available #220 (@Katsute)

### 🐞 Bug Fixes
- Fix MyAnimeList genre changes → Genre has been changed from an enum to a class #236 (@Katsute)
- Fix RelationType.toString() #215 (@Katsute)

### 📘 Dependencies
- Bump junit-jupiter-api and junit-jupiter-params from 5.7.2 to 5.8.0 #234 (@Katsute)
- Bump jcore from 1.1.0 to 1.2.0 #229 (@dependabot[bot])
- Bump maven-javadoc-plugin from 3.3.0 to 3.3.1 #227 (@dependabot[bot])
- Migrate to actions/setup-java #219 (@Katsute)
- Bump jcore from 1.0.0 to 1.1.0 #217 (@dependabot[bot])

## 2.3.0

### ⭐ New Features
- Add User Anime/Manga affinity #180 (@Katsute)
- Add callback methods to Anime/Manga affinity #211 (@Katsute)
- Add exception for invalid or expired token #209 (@Katsute)
- Experimental features must now be enabled manually #206 (@Katsute)
- Add support for all Android versions; disabled only PATCH methods on unsupported #205 (@Katsute)

### 📄 Documentation
- Migrate documentation #204 (@Katsute)

### 📘 Dependencies
- Bump crazy-max/ghaction-import-gpg from 3.1.0 to 3.2.0 #212 (@dependabot[bot])
- Update jcore test dependency #208 (@Katsute)

## 2.2.1

**Breaking Change:** This update changes query return type from full to preview. `Anime` → `AnimePreview` and `Manga` → `MangaPreview`.

### 🐞 Bug Fixes

- Fix queries not returning preview type #197 (@Katsute)
- Fix Android compatibility issues #194 (@Katsute)

### 📘 Dependencies

- Add jCore to test cases #190 (@Katsute)
- Bump org.junit.jupiter from 5.7.1 to 5.7.2 #188 (@Katsute)

## 2.2.0

### ⭐ New Features

- Replace `MyAnimeList#getMyself` with `MyAnimeList#getAuthenticatedUser` #179 (@Katsute)
- Add inverted fields #173 (@Katsute)
- Improve JsonSyntaxException #165 (@Katsute)

### 🔧 Optimizations

- Backend improvements & optimizations #178 (@Katsute)

### 📄 Documentation

- Improve documentation #164 (@Katsute)

### 🐞 Bug Fixes

- Fix compatibility issue with Android API 28+ (9+) #185 (@Katsute)
- Fix array manipulation issue #172 (@Katsute)

### 📘 Dependencies

- Bump maven-gpg-plugin from 1.6 to 3.0.1 #174 (@dependabot)
- Bump crazy-max/ghaction-import-gpg from 3 to 3.1.0 #182 (@Katsute)
- Bump maven-javadoc-plugin from 3.2.0 to 3.3.0 #181 (@dependabot)
- Bump AdoptOpenJDK/install-jdk from 1 to 1.1.1 #175 (@dependabot)
- Bump actions/checkout from 2 to 2.3.4 #177 (@dependabot)

## 2.1.0

### 🐞 Bug Fixes

- Fix Genre #161 (@Katsute)

## 2.0.1

### 🐞 Bug Fixes

- Fix Android reflection issue. #155 (@Katsute)

## 2.0.0

This update brings support to Java 8+

Please check the [FAQ](https://github.com/Katsute/Mal4J/blob/main/faq.md) for new issues that may occur.

### ⭐ New Features

- Add post iterator for ForumTopicDetail; Added additional ID methods to forum objects. #151 (@Katsute)
- Add URL callback to LocalServerBuilder #148 (@Katsute)
- Add support for JDK 8 #138 (@Katsute)

### 📄 Documentation

- Cleanup documentation. #152 (@Katsute)

### ❌ Removed

- Remove deprecated server constructors. #147 (@Katsute)
- Remove deprecated fields. #146 (@Katsute)

### 🐞 Bug Fixes

- Fix toString methods #149 (@Katsute)

## 1.2.0

### ⭐ New Features

- Retrieve User Anime/Manga listings from User object. #128 (@Katsute)

## 1.1.2

## 🐞 Bug Fixes
#
- Fix issue where android would not parse regex correctly #134 (@Katsute)

## 1.1.1

### 🐞 Bug Fixes

- Fixed issue where PaginatedIterator randomly skipped items #132 (@Katsute)
- Fixed HttpException #130 (@Katsute)

## 1.1.0

### ⭐ New Features

- Add more helpful error messages #116 (@Katsute)
- Overhaul MyAnimeList Authenticator (Local server & builder) #102 (@Katsute)
- Fields can be accessed as static strings #100 (@Katsute)

### 🔧 Optimizations

- Fixed issue where unicode characters weren't decoded; Json optimizations. #123 (@Katsute)
- Optimize PaginatedIterator #120 (@Katsute)

### 📄 Documentation

- Update Documentation #117 (@Katsute)
- Add more helpful error messages #116 (@Katsute)

### 🐞 Bug Fixes

- Fixed Light novels having unknown type. #127 (@Katsute)
- Fixed issue where unicode characters weren't decoded; Json optimizations. #123 (@Katsute)
- Fix critical issue where UserRetrievable could not be accessed #118 (@Katsute)

## 1.0.1

### 📄 Documentation

- Minor sourcecode/documentation fixes #104 (@Katsute)

### 🐞 Bug Fixes

- Fixed newline Json issue. #101 (@Katsute)

## 1.0.0

Initial Release