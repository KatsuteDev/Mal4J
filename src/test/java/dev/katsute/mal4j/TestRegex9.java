package dev.katsute.mal4j;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

final class TestRegex9 {

    @Nested
    final class TestMatcher {

        final String raw = " 123456789 ";
        final Matcher matcher = Pattern.compile("\\d").matcher(" 123456789 ");

        @Test
        final void testNullString(){
            assertThrows(NullPointerException.class, () -> Regex9.replaceAll(null, null, null));
        }

        @Test
        final void testNullMatcher(){
            assertThrows(NullPointerException.class, () -> Regex9.replaceAll(raw, null, null));
        }

        @Test
        final void testNullReplacer(){
            assertThrows(NullPointerException.class, () -> Regex9.replaceAll(raw, matcher, null));
        }

        @Test
        final void testSimpleReplaceAll(){
            assertEquals(matcher.replaceAll("0"), Regex9.replaceAll(raw, matcher, e -> "0"));
        }

        @Test
        final void testFunctionReplaceAll(){
            assertEquals(" 012345678 ", Regex9.replaceAll(raw, matcher, e -> String.valueOf(Integer.parseInt(e.group(0)) - 1)));
        }

    }

    @Nested
    final class TestCount {

        @Test
        public final void testNullMatcher(){
            assertThrows(NullPointerException.class, () -> Regex9.count(null));
        }

        @Test
        public final void testCount(){
            assertEquals(9, Regex9.count(Pattern.compile("\\d").matcher(" 123456789 ")));
            assertEquals(1, Regex9.count(Pattern.compile("\\d+").matcher(" 123456789 ")));
        }

    }

}