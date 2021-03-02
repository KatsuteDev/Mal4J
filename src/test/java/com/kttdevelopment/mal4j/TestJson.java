package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.kttdevelopment.mal4j.Json.*;

@SuppressWarnings("SpellCheckingInspection")
public class TestJson {

    @Test
    public void testMap() throws IOException{
        final String map = Files.readString(new File("src/test/java/resources/map.json").toPath()).replaceAll("\\r?\\n","");

        final JsonObject json = (JsonObject) parse(map);

        Assertions.assertEquals(1.0, json.getDouble("double"));
        Assertions.assertEquals(-1.0, json.getDouble("doublen"));
        Assertions.assertEquals(1.0, json.getDouble("doubles"));
        Assertions.assertEquals(1, json.getInt("int"));
        Assertions.assertEquals(-1,  json.getInt("intn"));
        Assertions.assertEquals(1, json.getInt("ints"));
        Assertions.assertTrue(json.getBoolean("bool"));
        Assertions.assertFalse(json.getBoolean("bools"));
        Assertions.assertNull(json.get("null"));
        Assertions.assertTrue(json.containsKey("null"));
        Assertions.assertNull(json.get("nulls"));
        Assertions.assertTrue(json.containsKey("nulls"));
        Assertions.assertEquals("string", json.getString("string"));
        Assertions.assertEquals("string", json.getString("strings"));
        Assertions.assertEquals("str\"ing", json.getString("str\"ingx"));
        Assertions.assertEquals("/\\", json.getString("slash\\"));
        Assertions.assertEquals("何", json.getString("何"));
        Assertions.assertEquals("\\u4f55", json.getString("\\u4f55"));
        Assertions.assertEquals("v", json.getJsonObject("obj").getString("k"));
        Assertions.assertEquals(0, json.getJsonObject("cobj").size());
        Assertions.assertEquals("str", json.getStringArray("arr")[0]);
        Assertions.assertEquals(0, json.getJsonArray("carr").length);
    }

    @Test
    public void testArray() throws IOException{
        final String arr = Files.readString(new File("src/test/java/resources/arr.json").toPath()).replaceAll("\\r?\\n","");

        final List<?> json = (List<?>) parse(arr);

        Assertions.assertTrue(json.contains(1.0));
        Assertions.assertTrue(json.contains(-1.0));
        Assertions.assertTrue(json.contains(2.0));
        Assertions.assertTrue(json.contains(1));
        Assertions.assertTrue(json.contains(-1));
        Assertions.assertTrue(json.contains(2));
        Assertions.assertTrue(json.contains(true));
        Assertions.assertTrue(json.contains(false));
        Assertions.assertTrue(json.contains(null));
        Assertions.assertTrue(json.contains("string"));
        Assertions.assertTrue(json.contains("str\"ingx"));
        Assertions.assertTrue(json.contains("/\\"));
        Assertions.assertTrue(json.contains("何"));
        Assertions.assertTrue(json.contains("\\u4f55"));
        Assertions.assertEquals("v", ((JsonObject) json.get(14)).getString("k"));
        Assertions.assertEquals(0, ((JsonObject) json.get(15)).size());
        Assertions.assertTrue(json.contains(List.of("str")));
        Assertions.assertTrue(json.contains(List.of()));
    }

    @Test
    public void testMalformed(){
        Assertions.assertThrows(JsonSyntaxException.class, () -> parse(""));
        Assertions.assertThrows(JsonSyntaxException.class, () -> parse("?"));
        Assertions.assertThrows(JsonSyntaxException.class, () -> parse("{"));
        Assertions.assertThrows(JsonSyntaxException.class, () -> parse("["));
    }

    @Test
    public void testNewLine(){
        Assertions.assertEquals("v", ((JsonObject) parse("{\"k\":\n\"v\"\n}")).getString("k"));
    }

}
