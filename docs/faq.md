---
heading: |
    <h1 class="display-3">FAQ</h1>
    <p class="mt-3 mb-5">
        Frequently asked questions.
    </p>
---
# General

## Are their any rate limits?

The MyAnimeList API currently has no rate limit in place so requests must be sent at your own discretion. Note that this Java library is currently operating at maximum efficiency, using only one request per method.

## What does this library offer in comparison to the Official API?

This library offers ***ALL*** the features provided by the API.

## My auth token doesn't work.

- Make sure that you are providing an auth token and not the client id.
- Your token may be expired.

# Methods

## All the fields are null.

By default this library follows the API and returns only the fields provided in the fields parameter. If you want all the fields use `MyAnimeList#ALL_ANIME_FIELDS`, `MyAnimeList#ALL_MANGA_FIELDS`, or `MyAnimeList#ALL_USER_FIELDS`.

## NSFW is not working.

For search queries make sure you also run `#includeNSFW()` in the query builder.

## I can't get other users.

Currently the MyAnimeList API does not allow you to check users other than yourself.