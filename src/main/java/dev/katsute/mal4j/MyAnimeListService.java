/*
 * Copyright (C) 2021-2022 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package dev.katsute.mal4j;

/**
 * Represents the HTTP requests for MyAnimeList.
 *
 * @see MyAnimeList
 */
@SuppressWarnings("DefaultAnnotationParam")
interface MyAnimeListService {

    String baseURL = "https://api.myanimelist.net/v2/";

    static MyAnimeListService create(){
        return APICall.create(baseURL, MyAnimeListService.class);
    }

    // anime

    @APIStruct.Endpoint(method="GET", value="anime")
    APIStruct.Response<Json.JsonObject> getAnime(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Query("q")                                 final String search,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields,
        @APIStruct.Query("nsfw")                              final Boolean nsfw
    );

    @APIStruct.Endpoint(method="GET", value="anime/{anime_id}")
    APIStruct.Response<Json.JsonObject> getAnime(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Path(value = "anime_id")                   final Long anime_id,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields
    );

    @APIStruct.Endpoint(method="GET", value="anime/ranking")
    APIStruct.Response<Json.JsonObject> getAnimeRanking(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Query("ranking_type")                      final String ranking_type,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields,
        @APIStruct.Query("nsfw")                              final Boolean nsfw
    );

    @APIStruct.Endpoint(method="GET", value="anime/season/{year}/{season}")
    APIStruct.Response<Json.JsonObject> getAnimeSeason(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Path(value = "year")                       final Integer year,
        @APIStruct.Path(value = "season")                     final String season,
        @APIStruct.Query("sort")                              final String ranking_type,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields,
        @APIStruct.Query("nsfw")                              final Boolean nsfw
    );

    @APIStruct.Endpoint(method="GET", value="anime/suggestions")
    APIStruct.Response<Json.JsonObject> getAnimeSuggestions(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields,
        @APIStruct.Query("nsfw")                              final Boolean nsfw
    );

    // anime list

    @SuppressWarnings("SpellCheckingInspection")
    @APIStruct.FormUrlEncoded
    @APIStruct.Endpoint(method="PATCH", value="anime/{anime_id}/my_list_status")
    APIStruct.Response<Json.JsonObject> updateAnimeListing(
        @APIStruct.Header("Authorization")                        final String token,
        @APIStruct.Path(value = "anime_id")                       final Long anime_id,
        @APIStruct.Field("status")                                final String status,
        @APIStruct.Field("is_rewatching")                         final Boolean rewatching,
        @APIStruct.Field("score")                                 final Integer score,
        @APIStruct.Field(value = "start_date", encoded = true)    final String start_date,
        @APIStruct.Field(value = "finish_date", encoded = true)   final String finish_date,
        @APIStruct.Field("num_watched_episodes")                  final Integer episodes_watched,
        @APIStruct.Field("priority")                              final Integer priority,
        @APIStruct.Field("num_times_rewatched")                   final Integer times_rewatched,
        @APIStruct.Field("rewatch_value")                         final Integer rewatch_value,
        @APIStruct.Field("tags")                                  final String tags,
        @APIStruct.Field("comments")                              final String comments
    );

    @APIStruct.Endpoint(method="DELETE", value="anime/{anime_id}/my_list_status")
    APIStruct.Response<Void> deleteAnimeListing(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Path(value = "anime_id")                   final Integer anime_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @APIStruct.Endpoint(method="GET", value="users/{user_name}/animelist")
    APIStruct.Response<Json.JsonObject> getUserAnimeListing(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Path(value = "user_name", encoded = true)  final String username,
        @APIStruct.Query("status")                            final String status,
        @APIStruct.Query("sort")                              final String sort,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields,
        @APIStruct.Query("nsfw")                              final Boolean nsfw
    );

    // forum

    @APIStruct.Endpoint(method="GET", value="forum/boards")
    APIStruct.Response<Json.JsonObject> getForumBoards(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id
    );

    @APIStruct.Endpoint(method="GET", value="forum/topic/{topic_id}")
    APIStruct.Response<Json.JsonObject> getForumBoard(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Path(value = "topic_id")                   final Long topic_id,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset
    );

    @SuppressWarnings("SpellCheckingInspection")
    @APIStruct.Endpoint(method="GET", value="forum/topics")
    APIStruct.Response<Json.JsonObject> getForumTopics(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Query("board_id")                          final Long board_id,
        @APIStruct.Query("subboard_id")                       final Long subboard_id,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query("sort")                              final String sort,
        @APIStruct.Query("q")                                 final String search,
        @APIStruct.Query("topic_user_name")                   final String topic_username,
        @APIStruct.Query("user_name")                         final String username
    );

    // manga

    @APIStruct.Endpoint(method="GET", value="manga")
    APIStruct.Response<Json.JsonObject> getManga(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Query("q")                                 final String search,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields,
        @APIStruct.Query("nsfw")                              final Boolean nsfw
    );

    @APIStruct.Endpoint(method="GET", value="manga/{manga_id}")
    APIStruct.Response<Json.JsonObject> getManga(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Path(value = "manga_id")                   final Long manga_id,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields
    );

    @APIStruct.Endpoint(method="GET", value="manga/ranking")
    APIStruct.Response<Json.JsonObject> getMangaRanking(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Query("ranking_type")                      final String ranking_type,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields,
        @APIStruct.Query("nsfw")                              final Boolean nsfw
    );

    // manga list

    @APIStruct.FormUrlEncoded
    @APIStruct.Endpoint(method="PATCH", value="manga/{manga_id}/my_list_status")
    APIStruct.Response<Json.JsonObject> updateMangaListing(
        @APIStruct.Header("Authorization")                        final String token,
        @APIStruct.Path(value = "manga_id")                       final Long manga_id,
        @APIStruct.Field("status")                                final String status,
        @APIStruct.Field("is_rereading")                          final Boolean rereading,
        @APIStruct.Field("score")                                 final Integer score,
        @APIStruct.Field(value = "start_date", encoded = true)    final String start_date,
        @APIStruct.Field(value = "finish_date", encoded = true)   final String finish_date,
        @APIStruct.Field("num_volumes_read")                      final Integer volumes_read,
        @APIStruct.Field("num_chapters_read")                     final Integer chapters_read,
        @APIStruct.Field("priority")                              final Integer priority,
        @APIStruct.Field("num_times_reread")                      final Integer times_reread,
        @APIStruct.Field("reread_value")                          final Integer reread_value,
        @APIStruct.Field("tags")                                  final String tags,
        @APIStruct.Field("comments")                              final String comments
    );

    @APIStruct.Endpoint(method="DELETE", value="manga/{manga_id}/my_list_status")
    APIStruct.Response<Void> deleteMangaListing(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Path(value = "manga_id")                   final Long manga_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @APIStruct.Endpoint(method="GET", value="users/{user_name}/mangalist")
    APIStruct.Response<Json.JsonObject> getUserMangaListing(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Header("X-MAL-CLIENT-ID")                  final String client_id,
        @APIStruct.Path(value = "user_name", encoded = true)  final String username,
        @APIStruct.Query("status")                            final String status,
        @APIStruct.Query("sort")                              final String sort,
        @APIStruct.Query("limit")                             final Integer limit,
        @APIStruct.Query("offset")                            final Integer offset,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields,
        @APIStruct.Query("nsfw")                              final Boolean nsfw
    );

    // user

    @APIStruct.Endpoint(method="GET", value="users/{user_name}")
    APIStruct.Response<Json.JsonObject> getUser(
        @APIStruct.Header("Authorization")                    final String token,
        @APIStruct.Path(value = "user_name", encoded = true)  final String username,
        @APIStruct.Query(value = "fields", encoded = true)    final String fields
    );

}
