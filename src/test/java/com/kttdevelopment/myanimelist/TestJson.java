package com.kttdevelopment.myanimelist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public class TestJson {

    @Test
    public void testMap(){
        final String map = String.join("",
            "{",
                "\"double\": 1.0,",
                "\"doublen\": -1.0,",
                "\"doubles\":1.0,",
                "\"int\": 2,",
                "\"intn\": -2,",
                "\"ints\":2,",
                "\"string\": \"string\",",
                "\"strings\":\"string\",",
                "\"str\\\"ingx\":\"str\\\"ing\",",
                "\"obj\": {" +
                    "\"k\": \"v\"" +
                "}," +
                "\"cobj\": {}," +
                "\"arr\": [" +
                    "\"str\"" +
                "]," +
                "\"carr\": []" +
            "}"
        );

        final Map<String,?> json = Json.parseMap(map);

        Assertions.assertEquals(1.0, json.get("double"));
        Assertions.assertEquals(-1.0, json.get("doublen"));
        Assertions.assertEquals(1.0, json.get("doubles"));
        Assertions.assertEquals(2.0, json.get("int"));
        Assertions.assertEquals(-2.0,  json.get("intn"));
        Assertions.assertEquals(2.0, json.get("ints"));
        Assertions.assertEquals("string", json.get("string"));
        Assertions.assertEquals("string", json.get("strings"));
        Assertions.assertEquals("str\"ing", json.get("str\"ingx"));
        Assertions.assertEquals(Map.of("k", "v"), json.get("obj"));
        Assertions.assertEquals(Map.of(), json.get("cobj"));
        Assertions.assertEquals(List.of("str"), json.get("arr"));
        Assertions.assertEquals(List.of(), json.get("carr"));
    }

    @Test
    public void testArray(){
        final String arr = String.join("",
           "[",
                " 1.0,",
                " -1.0,",
                "2.0,",
                " 3,",
                " -3,",
                "4,",
                "\"string\",",
                " \"str\\\"ingx\",",
                "{" +
                    "\"k\": \"v\"" +
                "}," +
                "{}," +
                "[" +
                    "\"str\"" +
                "]," +
                "[]" +
            "]"
        );

        final List<?> json = Json.parseArray(arr);

        Assertions.assertTrue(json.contains(1.0));
        Assertions.assertTrue(json.contains(-1.0));
        Assertions.assertTrue(json.contains(2.0));
        Assertions.assertTrue(json.contains(3.0));
        Assertions.assertTrue(json.contains(-3.0));
        Assertions.assertTrue(json.contains(4.0));
        Assertions.assertTrue(json.contains("string"));
        Assertions.assertTrue(json.contains("str\"ingx"));
        Assertions.assertTrue(json.contains(Map.of("k", "v")));
        Assertions.assertTrue(json.contains(Map.of()));
        Assertions.assertTrue(json.contains(List.of("str")));
        Assertions.assertTrue(json.contains(List.of()));
    }

    @Test
    public void testMalformed(){
        Assertions.assertThrows(Json.JsonSyntaxException.class, () -> Json.parse(""));
        Assertions.assertThrows(Json.JsonSyntaxException.class, () -> Json.parse("?"));
        Assertions.assertThrows(Json.JsonSyntaxException.class, () -> Json.parse("{"));
        Assertions.assertThrows(Json.JsonSyntaxException.class, () -> Json.parse("["));
    }

}
