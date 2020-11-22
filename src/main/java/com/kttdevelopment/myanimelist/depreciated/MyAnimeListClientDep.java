package com.kttdevelopment.myanimelist.depreciated;

import com.kttdevelopment.myanimelist.depreciated.net.*;
import com.kttdevelopment.myanimelist.depreciated.net.exception.*;

import java.util.Map;

@Deprecated
// handles http requests to MAL endpoint
final class MyAnimeListClientDep extends HttpRequestClient {

    private static final String url = "https://api.myanimelist.net/v2/";
    private transient final String oauth;

    private final Map<String,String> headers;

    public MyAnimeListClientDep(final String oauth){
        this.oauth = oauth;

        headers = Map.of(
            "Accept-Charset", "UTF-8",
            "Authorization", "Bearer " + oauth
        );
    }

    /*

    public final JSONObject request(final String path, final String method){
        final Response response = request(url + path, method, headers);
        final JSONObject obj = new JSONObject(response.getResponse());
        System.out.println(obj);

        switch(response.getCode()){
            case 400:
                throw new InvalidRequestException(obj.getString("message"));
            case 401:
                throw new InvalidTokenException(obj.getString("message"));
            case 403:
                throw new ForbiddenRequestException(obj.getString("message"));
            case 404:
                throw new NotFoundException(obj.getString("message"));
        }

        return new JSONObject(obj);
    }

    // new stuff

     */
}
