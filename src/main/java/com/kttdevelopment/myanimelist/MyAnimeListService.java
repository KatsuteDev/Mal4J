package com.kttdevelopment.myanimelist;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import static com.kttdevelopment.myanimelist.MyAnimeListAPIResponse.Call.*;

public interface MyAnimeListService {

    static MyAnimeListService create(){
        return new Retrofit
            .Builder()
            .baseUrl("https://api.myanimelist.net/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAnimeListService.class);
    }

    // anime

    @GET("anime")
    Call<GetAnimeList> getAnime(
        @Header("Authorization")                    final String token,
        @Query("q")                                 final String search,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @GET("anime/{anime_id}")
    Call<GetAnime> getAnime(
        @Header("Authorization")                   final String token,
        @Path(value = "anime_id")                  final Long anime_id,
        @Query(value = "fields", encoded = true)   final String fields
    );

    @GET("anime/ranking")
    Call<GetAnimeRanking> getAnimeRanking(
        @Header("Authorization")                    final String token,
        @Query("ranking_type")                      final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @GET("anime/season/{year}/{season}")
    Call<GetSeasonalAnime> getAnimeSeason(
        @Header("Authorization")                    final String token,
        @Path(value = "year")                       final Integer year,
        @Path(value = "season")                     final String season,
        @Query("sort")                              final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @GET("anime/suggestions")
    Call<GetSuggestedAnime> getAnimeSuggestions(
        @Header("Authorization")                    final String token,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    // anime list

    @SuppressWarnings("SpellCheckingInspection")
    @FormUrlEncoded
    @PATCH("anime/{anime_id}/my_list_status")
    Call<UpdateUserAnimeList> updateAnimeListing(
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

    @DELETE("anime/{anime_id}/my_list_status")
    Call<Void> deleteAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id")                   final Integer anime_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @GET("users/{user_name}/animelist")
    Call<GetUserAnimeList> getUserAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset
    );

    // forum

    @GET("forum/board")
    Call<GetForumBoards> getForumBoards(
        @Header("Authorization") final String token
    );

    @GET("forum/topic/{topic_id}")
    Call<GetForumTopicDetail> getForumBoard(
        @Header("Authorization")                    final String token,
        @Path(value = "topic_id")                   final Long topic_id,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset
    );

    @SuppressWarnings("SpellCheckingInspection")
    @GET("forum/topics")
    Call<GetForumTopics> getForumTopics(
        @Header("Authorizations")   final String token,
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

    @GET("manga")
    Call<GetMangaList> getManga(
        @Header("Authorization")                    final String token,
        @Query("q")                                 final String search,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    @GET("manga/{manga_id}")
    Call<GetManga> getManga(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id")                   final Long manga_id,
        @Query(value = "fields", encoded = true)    final String fields
    );

    @GET("manga/ranking")
    Call<GetMangaRanking> getMangaRanking(
        @Header("Authorization")                    final String token,
        @Query("ranking_type")                      final String ranking_type,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset,
        @Query(value = "fields", encoded = true)    final String fields,
        @Query("nsfw")                              final Boolean nsfw
    );

    // manga list

    @FormUrlEncoded
    @PATCH("manga/{manga_id}/my_list_status")
    Call<UpdateUserMangaList> updateMangaListing(
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

    @DELETE("manga/{manga_id}/my_list_status")
    Call<Void> deleteMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id")                   final Long manga_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @GET("users/{user_name}/mangalist")
    Call<GetUserMangaList> getUserMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final Integer limit,
        @Query("offset")                            final Integer offset
    );

    // user

    @GET("users/{user_name}")
    Call<GetUserInformation> getUser(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query(value = "fields", encoded = true)    final String fields
    );

}
