/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
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

import static dev.katsute.mal4j.APIStruct.*;
import static dev.katsute.mal4j.Json.*;

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

    @Endpoint(method="GET", value="anime")
    Response<JsonObject> getAnime(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Query("q")                                 final String search,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @Endpoint(method="GET", value="anime/{anime_id}")
    Response<JsonObject> getAnime(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "anime_id")                   final Long anime_id,
        @Query(value = "fields", encoded = true)    final String fields
    );

    @Endpoint(method="GET", value="anime/{anime_id}/characters")
    Response<JsonObject> getAnimeCharacters(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "anime_id")                   final Long anime_id,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields
    );

    @Endpoint(method="GET", value="anime/ranking")
    Response<JsonObject> getAnimeRanking(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Query("ranking_type")                      final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @Endpoint(method="GET", value="anime/season/{year}/{season}")
    Response<JsonObject> getAnimeSeason(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "year")                       final Integer year,
        @Path(value = "season")                     final String season,
        @Query("sort")                              final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @Endpoint(method="GET", value="anime/suggestions")
    Response<JsonObject> getAnimeSuggestions(
        @Header("Authorization")                    final String token,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    // anime list

    @SuppressWarnings("SpellCheckingInspection")
    @FormUrlEncoded
    @Endpoint(method="PATCH", value="anime/{anime_id}/my_list_status")
    Response<JsonObject> updateAnimeListing(
        @Header("Authorization")                        final String token,
        @Path(value = "anime_id")                       final Long anime_id,
        @Field("status")                                final String status,
        @Field("is_rewatching")                         final Boolean rewatching,
        @Field("score")                                 final Integer score,
        @Field(value = "start_date", encoded = true)    final String start_date,
        @Field(value = "finish_date", encoded = true)   final String finish_date,
        @Field("num_watched_episodes")                  final Integer episodes_watched,
        @Field("priority")                              final Integer priority,
        @Field("num_times_rewatched")                   final Integer times_rewatched,
        @Field("rewatch_value")                         final Integer rewatch_value,
        @Field("tags")                                  final String tags,
        @Field("comments")                              final String comments
    );

    @Endpoint(method="DELETE", value="anime/{anime_id}/my_list_status")
    Response<Void> deleteAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id")                   final Integer anime_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @Endpoint(method="GET", value="users/{user_name}/animelist")
    Response<JsonObject> getUserAnimeListing(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    // character

    @Endpoint(method="GET", value="characters/{character_id}")
    Response<JsonObject> getCharacter(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "character_id")               final Long character_id,
        @Query(value = "fields", encoded = true)    final String fields
    );

    // forum

    @Endpoint(method="GET", value="forum/boards")
    Response<JsonObject> getForumBoards(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id
    );

    @Endpoint(method="GET", value="forum/topic/{topic_id}")
    Response<JsonObject> getForumBoard(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "topic_id")                   final Long topic_id,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset
    );

    @SuppressWarnings("SpellCheckingInspection")
    @Endpoint(method="GET", value="forum/topics")
    Response<JsonObject> getForumTopics(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Query("board_id")                          final Long board_id,
        @Query("subboard_id")                       final Long subboard_id,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query("sort")                              final String sort,
        @Query("q")                                 final String search,
        @Query("topic_user_name")                   final String topic_username,
        @Query("user_name")                         final String username
    );

    // manga

    @Endpoint(method="GET", value="manga")
    Response<JsonObject> getManga(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Query("q")                                 final String search,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @Endpoint(method="GET", value="manga/{manga_id}")
    Response<JsonObject> getManga(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "manga_id")                   final Long manga_id,
        @Query(value = "fields", encoded = true)    final String fields
    );

    @Endpoint(method="GET", value="manga/ranking")
    Response<JsonObject> getMangaRanking(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Query("ranking_type")                      final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    // manga list

    @FormUrlEncoded
    @Endpoint(method="PATCH", value="manga/{manga_id}/my_list_status")
    Response<JsonObject> updateMangaListing(
        @Header("Authorization")                        final String token,
        @Path(value = "manga_id")                       final Long manga_id,
        @Field("status")                                final String status,
        @Field("is_rereading")                          final Boolean rereading,
        @Field("score")                                 final Integer score,
        @Field(value = "start_date", encoded = true)    final String start_date,
        @Field(value = "finish_date", encoded = true)   final String finish_date,
        @Field("num_volumes_read")                      final Integer volumes_read,
        @Field("num_chapters_read")                     final Integer chapters_read,
        @Field("priority")                              final Integer priority,
        @Field("num_times_reread")                      final Integer times_reread,
        @Field("reread_value")                          final Integer reread_value,
        @Field("tags")                                  final String tags,
        @Field("comments")                              final String comments
    );

    @Endpoint(method="DELETE", value="manga/{manga_id}/my_list_status")
    Response<Void> deleteMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id")                   final Long manga_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @Endpoint(method="GET", value="users/{user_name}/mangalist")
    Response<JsonObject> getUserMangaListing(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    // people

    @Endpoint(method="GET", value="people/{person_id}")
    Response<JsonObject> getPerson(
        @Header("Authorization")                    final String token,
        @Header("X-MAL-CLIENT-ID")                  final String client_id,
        @Path(value = "person_id")                  final Long person_id,
        @Query(value = "fields", encoded = true)    final String fields
    );

    // user

    @Endpoint(method="GET", value="users/{user_name}")
    Response<JsonObject> getUser(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query(value = "fields", encoded = true)    final String fields
    );

}