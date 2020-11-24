package com.kttdevelopment.myanimelist;

import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.Scanner;

public class TestClass {

    private static MyAnimeList MAL;

    @BeforeAll
    public static void beforeAll() throws IOException{
        System.out.print("Client ID: ");
        final String clientId = new Scanner(System.in).nextLine();
        MAL = MyAnimeList.withClientId(clientId, 5050);
    }

}
