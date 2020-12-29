package com.kttdevelopment.myanimelist;


import java.util.Map;

import static com.kttdevelopment.myanimelist.APIStruct.*;

/**
 * Represents the HTTP requests for MyAnimeList
 *
 * @see MyAnimeList
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("DefaultAnnotationParam")
public interface MyAnimeListService {

    String baseURL = "https://api.myanimelist.net/v2/";

    static MyAnimeListService create(){
        return APICall.create(baseURL, MyAnimeListService.class);
    }

    // anime

    @Endpoint(method="GET", value="anime")
    Response<Map<String,?>> getAnime(
        @Header("Authorization")                    final String token,
        @Query("q")                                 final String search,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @Endpoint(method="GET", value="anime/{anime_id}")
    Response<Map<String,?>> getAnime(
        @Header("Authorization")                   final String token,
        @Path(value = "anime_id")                  final Long anime_id,
        @Query(value = "fields", encoded = true)   final String fields
    );

    @Endpoint(method="GET", value="anime/ranking")
    Response<Map<String,?>> getAnimeRanking(
        @Header("Authorization")                    final String token,
        @Query("ranking_type")                      final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @Endpoint(method="GET", value="anime/season/{year}/{season}")
    Response<Map<String,?>> getAnimeSeason(
        @Header("Authorization")                    final String token,
        @Path(value = "year")                       final Integer year,
        @Path(value = "season")                     final String season,
        @Query("sort")                              final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @Endpoint(method="GET", value="anime/suggestions")
    Response<Map<String,?>> getAnimeSuggestions(
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
    Response<Map<String,?>> updateAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id")                   final Long anime_id,
        @Field("status")                            final String status,
        @Field("is_rewatching")                     final Boolean rewatching,
        @Field("score")                             final Integer score,
        @Field("num_watched_episodes")              final Integer episodes_watched,
        @Field("priority")                          final Integer priority,
        @Field("num_times_rewatched")               final Integer times_rewatched,
        @Field("rewatch_value")                     final Integer rewatch_value,
        @Field("tags")                              final String tags,
        @Field("comments")                          final String comments
    );

    @Endpoint(method="DELETE", value="anime/{anime_id}/my_list_status")
    Response<Void> deleteAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id")                   final Integer anime_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @Endpoint(method="GET", value="users/{user_name}/animelist")
    Response<Map<String,?>> getUserAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields
    );

    // forum

    @Endpoint(method="GET", value="forum/boards")
    Response<Map<String,?>> getForumBoards(
        @Header("Authorization") final String token
    );

    @Endpoint(method="GET", value="forum/topic/{topic_id}")
    Response<Map<String,?>> getForumBoard(
        @Header("Authorization")                    final String token,
        @Path(value = "topic_id")                   final Long topic_id,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset
    );

    @SuppressWarnings("SpellCheckingInspection")
    @Endpoint(method="GET", value="forum/topics")
    Response<Map<String,?>> getForumTopics(
        @Header("Authorization")   final String token,
        @Query("board_id")          final Long board_id,
        @Query("subboard_id")       final Long subboard_id,
        @Query("limit")             final Integer limit,
        @Query("offset")            final Integer offset,
        @Query("sort")              final String sort,
        @Query("q")                 final String search,
        @Query("topic_user_name")   final String topic_username,
        @Query("user_name")         final String username
    );

    // manga

    @Endpoint(method="GET", value="manga")
    Response<Map<String,?>> getManga(
        @Header("Authorization")                    final String token,
        @Query("q")                                 final String search,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @Endpoint(method="GET", value="manga/{manga_id}")
    Response<Map<String,?>> getManga(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id")                   final Long manga_id,
        @Query(value = "fields", encoded = true)    final String fields
    );

    @Endpoint(method="GET", value="manga/ranking")
    Response<Map<String,?>> getMangaRanking(
        @Header("Authorization")                    final String token,
        @Query("ranking_type")                      final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    // manga list

    @FormUrlEncoded
    @Endpoint(method="PATCH", value="manga/{manga_id}/my_list_status")
    Response<Map<String,?>> updateMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id")                   final Long manga_id,
        @Field("status")                            final String status,
        @Field("is_rereading")                      final Boolean rereading,
        @Field("score")                             final Integer score,
        @Field("num_volumes_read")                  final Integer volumes_read,
        @Field("num_chapters_read")                 final Integer chapters_read,
        @Field("priority")                          final Integer priority,
        @Field("num_times_reread")                  final Integer times_reread,
        @Field("reread_value")                      final Integer reread_value,
        @Field("tags")                              final String tags,
        @Field("comments")                          final String comments
    );

    @Endpoint(method="DELETE", value="manga/{manga_id}/my_list_status")
    Response<Void> deleteMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id")                   final Long manga_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @Endpoint(method="GET", value="users/{user_name}/mangalist")
    Response<Map<String,?>> getUserMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields
    );

    // user

    @Endpoint(method="GET", value="users/{user_name}")
    Response<Map<String,?>> getUser(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query(value = "fields", encoded = true)    final String fields
    );

}
