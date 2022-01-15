package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJava9 {

    @Test
    public void testMatcher(){
        // replaceAll
        final String raw = " 123456789 ";
        final Matcher matcher = Pattern.compile("\\d").matcher(" 123456789 ");

        Assertions.assertThrows(NullPointerException.class, () -> Regex9.replaceAll(null, null, null),
                                Workflow.errorSupplier("Expected replaceAll with null string to throw a NullPointerException"));
        Assertions.assertThrows(NullPointerException.class, () -> Regex9.replaceAll(raw, null, null),
                                Workflow.errorSupplier("Expected replaceAll with null matcher to throw a NullPointerException"));
        Assertions.assertThrows(NullPointerException.class, () -> Regex9.replaceAll(raw, matcher, null),
                                Workflow.errorSupplier("Expected replaceAll with null replacer to throw a NullPointerException"));

        Assertions.assertEquals(matcher.replaceAll("0"), Regex9.replaceAll(raw, matcher, e -> "0"),
                                Workflow.errorSupplier("Expected replaceAll matcher to match native replaceAll"));
        Assertions.assertEquals(" 012345678 ", Regex9.replaceAll(raw, matcher, e -> String.valueOf(Integer.parseInt(e.group(0)) - 1)),
                                Workflow.errorSupplier("Expected replaceAll matcher to work"));

        // count
        Assertions.assertThrows(NullPointerException.class, () -> Regex9.count(null),
                                Workflow.errorSupplier("Expected count with null matcher to throw a NullPointerException"));

        Assertions.assertEquals(9, Regex9.count(Pattern.compile("\\d").matcher(" 123456789 ")) ,
                                Workflow.errorSupplier("Expected count regex to match"));
        Assertions.assertEquals(1, Regex9.count(Pattern.compile("\\d+").matcher(" 123456789 ")),
                                Workflow.errorSupplier("Expected count regex to match"));
    }

}