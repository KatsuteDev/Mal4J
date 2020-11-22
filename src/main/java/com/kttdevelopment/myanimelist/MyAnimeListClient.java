package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.anime.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MyAnimeListClient {

    @GET("anime")
    Call<AnimePreview> getAnime(
        @Header("Authorization")    final String token,
        @Field("q")                 final String search,
        @Field("limit")             final int limit,
        @Field("offset")            final int offset
    );

    @GET("anime/{anime_id}")
    Call<Anime> getAnime(
        @Header("Authorization")                    final String token,
        @Path(value = "anime_id", encoded = true)   final int anime_id
    );

    @GET("anime/ranking")
    Call<List<AnimeRanking>> getAnimeRanking(
        @Header("Authorization")    final String token,
        @Field("ranking_type")      final String ranking_type,
        @Field("limit")             final int limit,
        @Field("offset")            final int offset
    );

    @GET("anime/season/{year}/{season}")
    Call<List<AnimePreview>> getAnimeSeason(
        @Header("Authorization")                final String token,
        @Path(value = "year", encoded = true)   final int year,
        @Path(value = "season", encoded = true) final String season,
        @Field("sort")                          final String ranking_type,
        @Field("limit")                         final int limit,
        @Field("offset")                        final int offset
    );

    @GET("anime/season")
    Call<List<AnimePreview>> getAnimeSuggestions(
        @Header("Authorization")    final String token,
        @Field("limit")             final int limit,
        @Field("offset")            final int offset
    );

    @SuppressWarnings("SpellCheckingInspection")
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
        @Path(value="anime_id", encoded = true)     final int anime_id
    );

    @GET("anime/{user_name}/animelist")
    Call<List<AnimeList>> getAnimeListing(
        @Header("Authorization")                    final String token,
        @Path(value="user_name", encoded = true)    final String username,
        @Field("status")                            final String status,
        @Field("sort")                              final String sort,
        @Field("limit")                             final int limit,
        @Field("offset")                            final int offset
    );

}
