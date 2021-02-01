# General

## Are their any rate limits?

The MyAnimeList API currently has no rate limit in place so requests must be sent at your own discretion. Note that this Java library is currently operating at maximum efficiency, using only one request per method.

## What does this library offer in comparison to the Official API?

This library offers ***ALL*** the features provided by the API and even some *undocumented* fields.

## My auth token doesn't work.

- Make sure that you are providing an auth token and not the client id.
- Your token may be expired.

## java.lang.UnsupportedClassVersionError

This issue is caused by using an older, unsupported JDK. This library requires at least JDK 11.

## Will you update this library to support JDK 8?

The latest long-term-support version for Java Development Kit is 11. This library is modular in order to be compliant with JDK11 standards and will not be downgraded for this very reason (modules are not supported in JDK8).

# API

## Seasons query is returning Anime from other seasons.

The seasons query returns Anime that is running in the current season, this includes Anime that may have started the season before and is still airing.

## NSFW is not working.

For search queries make sure you also run `#includeNSFW()` in the query builder.

## I can't get other users.

Currently the MyAnimeList API does not allow you to check users other than yourself.