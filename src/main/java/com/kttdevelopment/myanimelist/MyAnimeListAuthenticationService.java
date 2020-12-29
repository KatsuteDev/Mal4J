package com.kttdevelopment.myanimelist;

import java.util.Map;

import static com.kttdevelopment.myanimelist.APIStruct.*;

interface MyAnimeListAuthenticationService {

    String baseURL = "https://myanimelist.net/v1/oauth2/";

    static MyAnimeListAuthenticationService create(){
        return APICall.create(baseURL, MyAnimeListAuthenticationService.class);
    }

    // auth

    @Endpoint(method="POST", value="token")
    @FormUrlEncoded
    Response<Map<String,?>> getToken(
        @Field("client_id")     final String client_id,
        @Field("client_secret") final String client_secret,
        @Field("grant_type")    final String grant_type,
        @Field("code")          final String auth_code,
        @Field("code_verifier") final String code_verifier
    );

    @Endpoint(method="POST", value="token")
    @FormUrlEncoded
    Response<Map<String,?>> refreshToken(
        @Field("client_id")     final String client_id,
        @Field("client_secret") final String client_secret,
        @Field("grant_type")    final String grant_type,
        @Field("code")          final String auth_code,
        @Field("code_verifier") final String code_verifier,
        @Field("refresh_token") final String refresh_token
    );

}
