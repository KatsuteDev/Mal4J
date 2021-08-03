package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static com.kttdevelopment.mal4j.Json.*;

@SuppressWarnings("SpellCheckingInspection")
public class TestJson {

    private static JsonObject jsonObject;
    private static List<?> jsonArray;

    @BeforeAll
    public static void beforeAll() throws IOException{
        final String map = TestProvider.readFile(new File("src/test/java/resources/map.json")).replaceAll("\\r?\\n", "");
        jsonObject = (JsonObject) parse(map);

        final String arr = TestProvider.readFile(new File("src/test/java/resources/arr.json")).replaceAll("\\r?\\n", "");
        jsonArray = (List<?>) parse(arr);
    }

    // map

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("mapProvider")
    public void testMap(final Object expected, final Object actual){
        Assertions.assertEquals(expected, actual,
                                Workflow.errorSupplier(expected + " was missing"));
    }

    @Test
    public void testMapNull(){
        Assertions.assertNull(jsonObject.get("null"),
                              Workflow.errorSupplier("Expected json['null'] to be null"));
        Assertions.assertTrue(jsonObject.containsKey("null"),
                              Workflow.errorSupplier("Expected json['null'] to exist"));
        Assertions.assertNull(jsonObject.get("nulls"),
                              Workflow.errorSupplier("Expected json['nulls'] to be null"));
        Assertions.assertTrue(jsonObject.containsKey("nulls"),
                              Workflow.errorSupplier("Expected json['nulls'] to exist"));
    }

    @Test
    public void testMapBoolean(){
        Assertions.assertTrue(jsonObject.getBoolean("bool"),
                              Workflow.errorSupplier("Expected json['bool'] to be true"));
        Assertions.assertFalse(jsonObject.getBoolean("bools"),
                               Workflow.errorSupplier("Expected json['bools'] to be false"));
    }

    @Test
    public void testMapMap(){
        Assertions.assertEquals("v", jsonObject.getJsonObject("obj").getString("k"),
                                Workflow.errorSupplier("Expected json['obj']['k'] to be 'v'"));
        Assertions.assertEquals(0, jsonObject.getJsonObject("cobj").size(),
                                Workflow.errorSupplier("Exepected json['obj'] to be {}"));
    }

    @Test
    public void testMapArray(){
        Assertions.assertEquals("str", jsonObject.getStringArray("arr")[0],
                                Workflow.errorSupplier("Expected json['arr'][0] to be 'str'"));
        Assertions.assertEquals(0, jsonObject.getJsonArray("carr").length,
                                Workflow.errorSupplier("Expected json['carr'] to be []"));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> mapProvider(){
        return new TestProvider.ObjectStream()
            .add(1.0, jsonObject.getDouble("double"))
            .add(-1.0, jsonObject.getDouble("doublen"))
            .add(1.0, jsonObject.getDouble("doubles"))
            .add(1, jsonObject.getInt("int"))
            .add(-1, jsonObject.getInt("intn"))
            .add(1, jsonObject.getInt("ints"))
            .add("string", jsonObject.getString("string"))
            .add("string", jsonObject.getString("strings"))
            .add("str\"ing", jsonObject.getString("str\"ingx"))
            .add("/\\", jsonObject.getString("slash\\"))
            .add("何", jsonObject.getString("何"))
            .add("\\u4f55", jsonObject.getString("\\u4f55"))
            .stream();
    }

    // array

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("arrayProvider")
    public void testArray(final Object object){
        Assertions.assertTrue(jsonArray.contains(object),
                              Workflow.errorSupplier(object + " was missing"));
    }

    @Test
    public void testArrayMap(){
        Assertions.assertEquals("v", ((JsonObject) jsonArray.get(14)).getString("k"),
                                Workflow.errorSupplier("Expected json[14]['k'] to be 'v'"));
    }

    @Test
    public void testArrayEmptyMap(){
        Assertions.assertEquals(0, ((JsonObject) jsonArray.get(15)).size(),
                                Workflow.errorSupplier("Expected json[15] to be []"));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> arrayProvider(){
        return new TestProvider.ObjectStream()
            .add(1.0)
            .add(1.0)
            .add(-1.0)
            .add(2.0)
            .add(1)
            .add(-1)
            .add(2)
            .add(true)
            .add(false)
            .add((Object) null)
            .add("string")
            .add("str\"ingx")
            .add("/\\")
            .add("何")
            .add("\\u4f55")
            .add(new ArrayList<String>(){{ add("str"); }})
            .add(new ArrayList<String>())
            .stream();
    }

    // malformed

    @ParameterizedTest(name="[{index}] {0}")
    @ValueSource(strings={"", "?", "{", "}", "[", "]", "{{", "}}", "[[", "]]", "{[", "[{", "}]", "]}", "{[}]", "[{]}"})
    public void testMalformed(final String string){
        try{
            parse(string);
        }catch(final JsonSyntaxException e){
            Assertions.assertEquals(string, e.getRaw(),
                                    Workflow.errorSupplier("Raw exception json did not match input json"));
            return;
        }
        Assertions.fail(Workflow.errorSupplier("Expected JsonSyntaxException for: \"" + string + '"'));
    }

    // newline

    @Test
    public void testNewLine(){
        Assertions.assertEquals("v", ((JsonObject) parse("{\"k\":\n\"v\"\n}")).getString("k"),
                                Workflow.errorSupplier("Expected json['k'] to be 'v' for a json with new lines"));
    }

}
