package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.auth.AccessToken;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public interface MyAnimeListAuthenticationService {

    static MyAnimeListAuthenticationService create(){
        return new Retrofit
            .Builder()
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
        @Field("grant_type")    final String grant_type,
        @Field("code")          final String auth_code,
        @Field("code_verifier") final String code_verifier
    );

    @POST("token")
    @FormUrlEncoded
    Call<AccessToken> refreshToken(
        @Field("grant_type")    final String grant_type,
        @Field("refresh_token") final String refresh_token
    );

}
