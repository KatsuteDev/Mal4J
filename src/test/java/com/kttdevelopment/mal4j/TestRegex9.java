package com.kttdevelopment.mal4j;

import dev.katsute.jcore.Workflow;
import org.junit.jupiter.api.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class TestRegex9 {

    @Nested
    final class TestMatcher {

        final String raw = " 123456789 ";
        final Matcher matcher = Pattern.compile("\\d").matcher(" 123456789 ");

        @Test
        public final void testNullString(){
             Assertions.assertThrows(NullPointerException.class, () -> Regex9.replaceAll(null, null, null),
                                    Workflow.errorSupplier("Expected replaceAll with null string to throw a NullPointerException"));
        }

        @Test
        public final void testNullMatcher(){
            Assertions.assertThrows(NullPointerException.class, () -> Regex9.replaceAll(raw, null, null),
                                    Workflow.errorSupplier("Expected replaceAll with null matcher to throw a NullPointerException"));
        }

        @Test
        public final void testNullReplacer(){
            Assertions.assertThrows(NullPointerException.class, () -> Regex9.replaceAll(raw, matcher, null),
                                Workflow.errorSupplier("Expected replaceAll with null replacer to throw a NullPointerException"));
        }

        @Test
        public final void testSimpleReplaceAll(){
            Assertions.assertEquals(matcher.replaceAll("0"), Regex9.replaceAll(raw, matcher, e -> "0"),
                                    Workflow.errorSupplier("Expected replaceAll matcher to match native replaceAll"));
        }

        @Test
        public final void testFunctionReplaceAll(){
            Assertions.assertEquals(" 012345678 ", Regex9.replaceAll(raw, matcher, e -> String.valueOf(Integer.parseInt(e.group(0)) - 1)),
                                    Workflow.errorSupplier("Expected replaceAll matcher to work"));
        }

    }

    @Nested
    final class TestCount {

        @Test
        public final void testNullMatcher(){
            Assertions.assertThrows(NullPointerException.class, () -> Regex9.count(null),
                                    Workflow.errorSupplier("Expected count with null matcher to throw a NullPointerException"));
        }

        @Test
        public final void testCount(){
            Assertions.assertEquals(9, Regex9.count(Pattern.compile("\\d").matcher(" 123456789 ")) ,
                                    Workflow.errorSupplier("Expected count regex to match"));
            Assertions.assertEquals(1, Regex9.count(Pattern.compile("\\d+").matcher(" 123456789 ")),
                                    Workflow.errorSupplier("Expected count regex to match"));
        }


    }


}