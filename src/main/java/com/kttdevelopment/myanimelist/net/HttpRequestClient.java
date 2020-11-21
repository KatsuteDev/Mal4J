package com.kttdevelopment.myanimelist.net;

import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.Map;

public class HttpRequestClient {

    public final String request(final String url) throws IOException{
        return request(url, Collections.emptyMap());
    }

    public final String request(final String url, final Map<String,String> args) throws IOException{
        final URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Authorization", "Bearer " + token);
    }

}
