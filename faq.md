# General

## Are their any rate limits?

The MyAnimeList API currently has no rate limit in place so requests must be sent at your own discretion. Note that this Java library is currently operating at maximum efficiency, using only one request per method.

## What does this library offer in comparison to the Official API?

This library offers ***ALL*** the features provided by the API and even some *undocumented* fields.

## My auth token doesn't work.

- Make sure that you are providing an auth token and not the client id.
- Your token may be expired.
- Your token is missing '`Bearer `'.
- Your token may contain dangling whitespace.

## java.lang.UnsupportedClassVersionError

This issue is caused by using an older, unsupported JDK; this library requires at least JDK 8. If you are using JDK 8 and still get this error, make sure you are using v2.0.0+ of this library.

## Does this library support JDK 8 / JDK 11?

This project supports JDK 8 and JDK 11 modules.

# API

## Seasons query is returning Anime from other seasons.

The seasons query returns Anime that is running in the current season, this includes Anime that may have started the season before and is still airing.

## NSFW is not working.

For search queries make sure you also run [`#includeNSFW()`](https://mal4j.kttdevelopment.com/Mal4J/com/kttdevelopment/mal4j/query/NSFW.html#includeNSFW()) in the query builder.

## I can't get other users.

Currently the MyAnimeList API does not allow you to check users other than yourself.
