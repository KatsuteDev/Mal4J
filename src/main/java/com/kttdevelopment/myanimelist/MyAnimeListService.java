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
        @Header("Authorization")    final String token,
        @Query("q")                 final String search,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields,
        @Query("nsfw")              final boolean nsfw
    );

    @GET("anime/{anime_id}")
    Call<GetAnime> getAnime(
        @Header("Authorization")                   final String token,
        @Path(value = "anime_id", encoded = true)  final long anime_id,
        @Query(value = "fields", encoded = true)   final String fields,
        @Query("nsfw")                             final boolean nsfw
    );

    @GET("anime/ranking")
    Call<GetAnimeRanking> getAnimeRanking(
        @Header("Authorization")    final String token,
        @Query("ranking_type")      final String ranking_type,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields,
        @Query("nsfw")              final boolean nsfw
    );

    @GET("anime/season/{year}/{season}")
    Call<GetSeasonalAnime> getAnimeSeason(
        @Header("Authorization")                final String token,
        @Path(value = "year", encoded = true)   final int year,
        @Path(value = "season", encoded = true) final String season,
        @Query("sort")                          final String ranking_type,
        @Query("limit")                         final int limit,
        @Query("offset")                        final int offset,
        @Query("fields")                        final String fields,
        @Query("nsfw")                          final boolean nsfw
    );

    @GET("anime/suggestions")
    Call<GetSuggestedAnime> getAnimeSuggestions(
        @Header("Authorization")    final String token,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields,
        @Query("nsfw")              final boolean nsfw
    );

    // anime list

    @SuppressWarnings("SpellCheckingInspection")
    @FormUrlEncoded
    @PATCH("anime/{anime_id}/my_list_status")
    Call<UpdateUserAnimeList> updateAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id", encoded = true)   final long anime_id,
        @Field("status")                            final String status,
        @Field("is_rewatching")                     final boolean rewatching,
        @Field("score")                             final int score,
        @Field("num_watched_episodes")              final int episodes_watched,
        @Field("priority")                          final int priority,
        @Field("num_times_rewatched")               final int times_rewatched,
        @Field("rewatch_value")                     final int rewatch_value,
        @Field("tags")                              final String tags,
        @Field("comments")                          final String comments
    );

    @DELETE("anime/{anime_id}/my_list_status")
    Call<Void> deleteAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id", encoded = true)   final int anime_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @GET("anime/{user_name}/animelist")
    Call<GetUserAnimeList> getAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final int limit,
        @Query("offset")                            final int offset,
        @Query("nsfw")                              final boolean nsfw
    );

    // forum

    @GET("forum/board")
    Call<GetForumBoards> getForumBoards(
        @Header("Authorization")        final String token,
        @Query("nsfw")                  final boolean nsfw
    );

    @GET("forum/topic/{topic_id}")
    Call<GetForumTopicDetail> getForumBoard(
        @Header("Authorization")                    final String token,
        @Path(value = "topic_id", encoded = true)   final long topic_id,
        @Query("limit")                             final int limit,
        @Query("offset")                            final int offset,
        @Query("nsfw")                              final boolean nsfw
    );

    @SuppressWarnings("SpellCheckingInspection")
    @GET("forum/topics")
    Call<GetForumTopics> getForumTopics(
        @Header("Authorizations")   final String token,
        @Query("board_id")          final long board_id,
        @Query("subboard_id")       final long subboard_id,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("sort")              final String sort,
        @Query("q")                 final String search,
        @Query("topic_user_name")   final String topic_username,
        @Query("user_name")         final String username,
        @Query("nsfw")              final boolean nsfw
    );

    // manga

    @GET("manga")
    Call<GetMangaList> getManga(
        @Header("Authorization")    final String token,
        @Query("q")                 final String search,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields,
        @Query("nsfw")              final boolean nsfw
    );

    @GET("manga/{manga_id}")
    Call<GetManga> getManga(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id", encoded = true)   final long manga_id,
        @Query("fields")                            final String fields,
        @Query("nsfw")                              final boolean nsfw
    );

    @GET("manga/ranking")
    Call<GetMangaRanking> getMangaRanking(
        @Header("Authorization")    final String token,
        @Query("ranking_type")      final String ranking_type,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields,
        @Query("nsfw")              final boolean nsfw
    );

    // manga list

    @FormUrlEncoded
    @PATCH("manga/{manga_id}/my_list_status")
    Call<UpdateUserMangaList> updateMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id", encoded = true)   final long manga_id,
        @Field("status")                            final String status,
        @Field("is_rereading")                      final boolean rereading,
        @Field("score")                             final int score,
        @Field("num_volumes_read")                  final int volumes_read,
        @Field("num_chapters_read")                 final int chapters_read,
        @Field("priority")                          final int priority,
        @Field("num_times_reread")                  final int times_reread,
        @Field("reread_value")                      final int reread_value,
        @Field("tags")                              final String tags,
        @Field("comments")                          final String comments
    );

    @DELETE("manga/{manga_id}/my_list_status")
    Call<Void> deleteMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id", encoded = true)   final long manga_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @GET("manga/{user_name}/mangalist")
    Call<GetUserMangaList> getMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final int limit,
        @Query("offset")                            final int offset,
        @Query("nsfw")                              final boolean nsfw
    );

    // user

    @GET("users/{user_name}")
    Call<GetUserInformation> getUser(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name")                  final String username,
        @Query("fields")                            final String fields,
        @Query("nsfw")                              final boolean nsfw
    );

}
