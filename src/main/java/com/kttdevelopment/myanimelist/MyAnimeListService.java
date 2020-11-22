package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import com.kttdevelopment.myanimelist.anime.property.UserAnimeListing;
import com.kttdevelopment.myanimelist.forum.ForumCategory;
import com.kttdevelopment.myanimelist.forum.ForumTopic;
import com.kttdevelopment.myanimelist.manga.*;
import com.kttdevelopment.myanimelist.manga.property.UserMangaListing;
import com.kttdevelopment.myanimelist.user.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.util.List;

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
    Call<AnimePreview> getAnime(
        @Header("Authorization")    final String token,
        @Query("q")                 final String search,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields
    );

    @GET("anime/{anime_id}")
    Call<Anime> getAnime(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id", encoded = true)   final int anime_id,
        @Query("fields")                            final String fields
    );

    @GET("anime/ranking")
    Call<List<AnimeRanking>> getAnimeRanking(
        @Header("Authorization")    final String token,
        @Query("ranking_type")      final String ranking_type,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields
    );

    @GET("anime/season/{year}/{season}")
    Call<AnimeSeason> getAnimeSeason(
        @Header("Authorization")                final String token,
        @Path(value = "year", encoded = true)   final int year,
        @Path(value = "season", encoded = true) final String season,
        @Query("sort")                          final String ranking_type,
        @Query("limit")                         final int limit,
        @Query("offset")                        final int offset,
        @Query("fields")                        final String fields
    );

    @GET("anime/season")
    Call<List<AnimePreview>> getAnimeSuggestions(
        @Header("Authorization")    final String token,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields
    );

    // anime list

    @SuppressWarnings("SpellCheckingInspection")
    @FormUrlEncoded
    @PATCH("anime/{anime_id}/my_list_status")
    Call<AnimeListing> updateAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id", encoded = true)   final int anime_id,
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
    Call<List<UserAnimeListing>> getAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final int limit,
        @Query("offset")                            final int offset
    );

    // forum

    @GET("forum/board")
    Call<List<ForumCategory>> getForumBoards(
        @Header("Authorization")        final String token
    );

    @GET("forum/topic/{topic_id}")
    Call<ForumCategory> getForumBoard(
        @Header("Authorization")                    final String token,
        @Path(value = "topic_id", encoded = true)   final int topic_id,
        @Query("limit")                             final int limit,
        @Query("offset")                            final int offset
    );

    @SuppressWarnings("SpellCheckingInspection")
    @GET("forum/topics")
    Call<List<ForumTopic>> getForumTopics(
        @Header("Authorizations")   final String token,
        @Query("board_id")          final int board_id,
        @Query("subboard_id")       final int subboard_id,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("sort")              final String sort,
        @Query("q")                 final String search,
        @Query("topic_user_name")   final String topic_username,
        @Query("user_name")         final String username
    );

    // manga

    @GET("manga")
    Call<List<MangaPreview>> getManga(
        @Header("Authorization")    final String token,
        @Query("q")                 final String search,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields
    );

    @GET("manga/{manga_id}")
    Call<Manga> getManga(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id", encoded = true)   final int manga_id,
        @Query("fields")                            final String fields
    );

    @GET("manga/ranking")
    Call<List<MangaRanking>> getMangaRanking(
        @Header("Authorization")    final String token,
        @Query("ranking_type")      final String ranking_type,
        @Query("limit")             final int limit,
        @Query("offset")            final int offset,
        @Query("fields")            final String fields
    );

    // manga list

    @FormUrlEncoded
    @PATCH("manga/{manga_id}/my_list_status")
    Call<MangaListing> updateMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "manga_id", encoded = true)   final int manga_id,
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
        @Path(value = "manga_id", encoded = true)   final int manga_id
    );

    @SuppressWarnings("SpellCheckingInspection")
    @GET("manga/{user_name}/mangalist")
    Call<List<UserMangaListing>> getMangaListing(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name", encoded = true)  final String username,
        @Query("status")                            final String status,
        @Query("sort")                              final String sort,
        @Query("limit")                             final int limit,
        @Query("offset")                            final int offset
    );

    // user

    @GET("users/{user_name}")
    Call<User> getUser(
        @Header("Authorization")                    final String token,
        @Path(value = "user_name")                  final String username,
        @Query("fields")                            final String fields
    );

}
