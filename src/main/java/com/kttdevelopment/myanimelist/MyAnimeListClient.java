package com.kttdevelopment.myanimelist;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Map;
import java.util.Scanner;

// handles http requests to MAL endpoint
final class MyAnimeListClient {



    public final void request(final String url, final String token) throws IOException{
        final URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        try(final InputStream response = connection.getInputStream()){
            try(final Scanner scanner = new Scanner(response)){
                final String json = scanner.useDelimiter("\\A").next();

            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
