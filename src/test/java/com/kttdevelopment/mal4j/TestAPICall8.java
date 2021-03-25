package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.*;

import java.io.File;

public class TestAPICall8 {

    @SuppressWarnings("SpellCheckingInspection")
    @BeforeAll
    public static void beforeAll(){
        Assertions.assertEquals(new File("src/test/java/com/kttdevelopment/mal4j/TestAPICall8.java").length(), new File("src/test/java11/com/kttdevelopment/mal4j/TestAPICall11.java").length(), "Java 11 Test should match Java 8 Test");
    }

    // todo: Test!
    @Test
    public void testCall(){

    }

}
