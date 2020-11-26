package com.kttdevelopment.myanimelist;

import com.kttdevelopment.myanimelist.auth.MyAnimeListAuthenticator;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestClass {

    private static MyAnimeList MAL;

    @BeforeAll
    public static void beforeAll() throws IOException{
        final File file = new File("src/test/java/com/kttdevelopment/myanimelist/client.txt");
        Assumptions.assumeTrue(file.exists());
        final String clientId = Files.readString(file.toPath());


        // MAL = MyAnimeList.withClientId(clientId, 5050);
        System.out.println(new MyAnimeListAuthenticator(clientId, 5050).getToken().getToken());
        Assumptions.assumeTrue(MAL != null);
    }

    @Test
    public void test(){
        System.out.println(MAL.getAnime(13759));
    }

}
