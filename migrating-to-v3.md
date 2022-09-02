# Migrating to v3

Version 3 includes several breaking changes which are documented below.

## 📋 Types of Breaking Changes

If you are not using any deprecated methods you can ignore any changes marked as **`❌ Removed`**.

 - **`🆕 New`** A new addition to the library, class, method, or field.
 - **`⛔ Breaking Change`** A major change to the library, class, method, or field.
 - **`⚠️ Changed`** A minor behavioral change to a class or method, no change is required on your part.
 - **`❌ Removed`** A deprecated class, method, or field that has been removed.

## 📋 Changes

### **`⛔`** Changed package from `com.kttdevelopment.mal4j` to `dev.katsute.mal4j`

As part of the v3 migration, this package's group ID has been changed from `com.kttdevelopment` to `dev.katsute`. All future versions will be released under this new group ID; v2 will still use the old group ID.

When upgrading to v3 the dependency group ID must be changed in the dependencies section:

```diff
...
<dependency>
--  <groupId>com.kttdevelopment</groupId>
++  <groupId>dev.katsute</groupId>
    <artifactId>mal4j</artifactId>
</dependency>
...
```

Along with the group ID, any imports referencing to this library must also be updated. The easiest way to migrate from v2 to v3 is to replace all `import com.kttdevelopment.mal4j.` with `import dev.katsute.mal4j.`.

If you are using modules you must change `requires com.kttdevelopment.mal4j` to `requires dev.katsute.mal4j`.

## &nbsp;

### **`⚠️`** Using experimental features without explicitly enabling them will now throw an exception

Previously using an experimental feature without enabling it would only print a warning, now using an experimental feature without enabling it will throw a `DisabledFeatureException` exception.

To enable an experimental feature use `MyAnimeList.enableExperimentalFeature`.

### **`❌`** Removed deprecated authentication methods

The following deprecated authentication methods have been been removed:

 - `withOAuthToken` is now `withToken`.
 - `refreshOAuthToken` is now `refreshToken`.
 - `withAuthorization` is now `withOAuth2`.

### **`❌`** Removed deprecated String parameters from `MyAnimeListAuthenticator`

The deprecated String parameter has been replaced with the `Authorization` parameter.

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

### **`❌`** Removed `getMyself` from `MyAnimeList`

The deprecated `getMyself` method has replaced with `getAuthenticatedUser`.

## &nbsp;

### **`⛔`** Moved exceptions from `mal4j` to `mal4j.exceptions`

All exceptions have been moved from the base package to a new `exceptions` package.

Any exception from this package must have its import updated manually.

### **`❌`** Removed `AndroidCompatibilityException`

The `AndroidCompatibilityException` is no longer thrown and has been removed.

## &nbsp;

### **`⚠️`** `PaginatedIterator` now returns a modifiable list

`PaginatedIterator.toList()` and `PaginatedIterator.toSet()` now returns a new list instead of an unmodifiable list.

## &nbsp;

### **`⛔`** `getStartDate`, `getEndDate` and `getFinishDate` methods now return a `NullableDate`

MyAnimeList does not always have a complete date for a Anime or Manga listing, as a consequence of using the Date object, any listing that was missing a month or day would automatically resolve to January 1st when it should be null.

These methods now return a `NullableDate` such that:

```
NullableDate{
    getYear(): int | null
    getMonth(): Month | null
    getMonthNum(): int | null
    getDay(): int | null
}
```

## &nbsp;

### **`❌`** Removed `Digital_Manga` and `Web_Manga` from `AnimeSource`

The `Digital_Manga` and `Web_Manga` enums have removed, instead use `DigitalManga` and `WebManga` enums.

## &nbsp;

### **`❌`** Removed `Novel` from `MangaType`

The `Novel` enum has been removed, instead use `LightNovel`.