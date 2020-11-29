package com.kttdevelopment.myanimelist.auth;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public interface MyAnimeListAuthenticationService {

    static MyAnimeListAuthenticationService create(){
        // final okhttp3.logging.HttpLoggingInterceptor interceptor = new okhttp3.logging.HttpLoggingInterceptor();
        // interceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);
        return new Retrofit
            .Builder()
            // .client(new okhttp3.OkHttpClient.Builder()
            //     .addInterceptor(interceptor).build())
            .baseUrl("https://myanimelist.net/v1/oauth2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAnimeListAuthenticationService.class);
    }

    // auth

    @POST("token")
    @FormUrlEncoded
    Call<AccessToken> getToken(
        @Field("client_id")     final String client_id,
        @Field("client_secret") final String client_secret,
        @Field("grant_type")    final String grant_type,
        @Field("code")          final String auth_code,
        @Field("code_verifier") final String code_verifier
    );

    @POST("token")
    @FormUrlEncoded
    Call<AccessToken> refreshToken(
        @Field("client_id")     final String client_id,
        @Field("client_secret") final String client_secret,
        @Field("grant_type")    final String grant_type,
        @Field("code")          final String auth_code,
        @Field("code_verifier") final String code_verifier,
        @Field("refresh_token") final String refresh_token
    );

}
