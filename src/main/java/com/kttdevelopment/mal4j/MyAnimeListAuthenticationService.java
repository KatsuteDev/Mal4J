/*
 * Copyright (C) 2021 Katsute <https://github.com/Katsute>
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

package com.kttdevelopment.mal4j;

import static com.kttdevelopment.mal4j.APIStruct.*;
import static com.kttdevelopment.mal4j.Json.*;

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
