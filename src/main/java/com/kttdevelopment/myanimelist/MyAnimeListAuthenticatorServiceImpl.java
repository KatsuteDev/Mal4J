package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.APIStruct.Response;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

final class MyAnimeListAuthenticatorServiceImpl {

    @SuppressWarnings("SameParameterValue")
    static MyAnimeListAuthenticationService create(final String baseURL){
        return new MyAnimeListAuthenticationService() {

            @Override
            final Response<Map<String,?>> getToken(final String client_id, final String client_secret, final String grant_type, final String auth_code, final String code_verifier){
                try{
                    return new APICall(
                        baseURL,
                        MyAnimeListAuthenticationService.class.getDeclaredMethod("getToken", String.class, String.class, String.class, String.class, String.class),
                        client_id, client_secret, grant_type, auth_code, code_verifier
                    ).call(Json::parseMap);
                }catch(final NoSuchMethodException e){
                    throw new UnexpectedException(e);
                }catch(final IOException e){
                    throw new UncheckedIOException(e);
                }
            }

            @Override
            final Response<Map<String,?>> refreshToken(final String client_id, final String client_secret, final String grant_type, final String auth_code, final String code_verifier, final String refresh_token){
                try{
                    return new APICall(
                        baseURL,
                        MyAnimeListAuthenticationService.class.getDeclaredMethod("refreshToken", String.class, String.class, String.class, String.class, String.class, String.class),
                        client_id, client_secret, grant_type, auth_code, code_verifier, refresh_token
                    ).call(Json::parseMap);
                }catch(final NoSuchMethodException e){
                    throw new UnexpectedException(e);
                }catch(final IOException e){
                    throw new UncheckedIOException(e);
                }
            }

        };
    }

}
