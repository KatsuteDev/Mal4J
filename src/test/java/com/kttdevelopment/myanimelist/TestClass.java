package com.kttdevelopment.myanimelist;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestClass {

    private static MyAnimeList MAL;

    private static transient final String clientId = "";  // DO NOT SAVE CLIENT KEYS HERE

    @BeforeAll
    public static void beforeAll() throws IOException{
        MAL = MyAnimeList.withClientId(clientId, 5050);

        System.out.println(MAL.getAnime(13759));
    }

    @Test
    public void test(){

    }

}
