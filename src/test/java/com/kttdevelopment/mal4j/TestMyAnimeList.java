package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMyAnimeList {

    @Test
    public void testNull(){
        Assertions.assertThrows(NullPointerException.class, () -> MyAnimeList.withOAuthToken(null));
    }

    @Test
    public void testNoBearer(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> MyAnimeList.withOAuthToken("x"));
    }

}
