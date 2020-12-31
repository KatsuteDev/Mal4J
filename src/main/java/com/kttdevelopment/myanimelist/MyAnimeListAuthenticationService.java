package com.kttdevelopment.myanimelist;

import static com.kttdevelopment.myanimelist.APIStruct.*;
import static com.kttdevelopment.myanimelist.Json.*;

/**
 * Represents the HTTP requests for authentication.
 *
 * @see MyAnimeListAuthenticator
 */
interface MyAnimeListAuthenticationService {

    String baseURL = "https://myanimelist.net/v1/oauth2/";

    static MyAnimeListAuthenticationService create(){
        return APICall.create(baseURL, MyAnimeListAuthenticationService.class);
    }

    // auth

    @Endpoint(method="POST", value="token")
    @FormUrlEncoded
    Response<JsonObject> getToken(
        @Field("client_id")     final String client_id,
        @Field("client_secret") final String client_secret,
        @Field("grant_type")    final String grant_type,
        @Field("code")          final String auth_code,
        @Field("code_verifier") final String code_verifier
    );

    @Endpoint(method="POST", value="token")
    @FormUrlEncoded
    Response<JsonObject> refreshToken(
        @Field("client_id")     final String client_id,
        @Field("client_secret") final String client_secret,
        @Field("grant_type")    final String grant_type,
        @Field("code")          final String auth_code,
        @Field("code_verifier") final String code_verifier,
        @Field("refresh_token") final String refresh_token
    );

}
