package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJava9 {

    @SuppressWarnings("SpellCheckingInspection")
    @ParameterizedTest
    @ValueSource(strings={"The string \u00FC@foo-bar", "", "x", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-.*", "\u0100 \u0101 \u0555 \u07FD \u07FF", "\u8000 \u8001 \uA000 \uFFFD \uFFFF"})
    public void testEncoder(final String string) throws UnsupportedEncodingException{
        String enc1, enc2;
        Assertions.assertEquals(
            enc1 = URLEncoder.encode(string, StandardCharsets.UTF_8.name()),
            enc2 = Java9.URLEncoder.encode(string, StandardCharsets.UTF_8),
            '\'' + string + "' was not encoded correctly"
        );
        // decoder
        Assertions.assertEquals(
            URLDecoder.decode(enc1, StandardCharsets.UTF_8.name()),
            Java9.URLDecoder.decode(enc2, StandardCharsets.UTF_8),
            '\'' + string + "' was not decoded correctly"
        );
    }

    @Test
    public void testEncoderChars() throws UnsupportedEncodingException{
        testEncoder(charactersRange('\u0000', '\u00FF'));
    }

    @SuppressWarnings("SameParameterValue")
    private static String charactersRange(final char c1, final char c2) {
        final StringBuilder sb = new StringBuilder(c2 - c1);
        for (char c = c1; c < c2; c++)
            sb.append(c);
        return sb.toString();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testNullEncoder(){
        Assertions.assertThrows(NullPointerException.class, () -> Java9.URLEncoder.encode("Hello World", null));
    }

    //

    @Test
    public void testBlankNull(){
        Assertions.assertThrows(NullPointerException.class, () -> Java9.String.isBlank(null));
    }

    @ParameterizedTest(name="[{index}] \"{0}\"")
    @ValueSource(strings={"", " ", "\t", " \t", "\t\n", " \t\n", "\u1680", " \u1680 "})
    public void testBlank(final String string){
        Assertions.assertTrue(Java9.String.isBlank(string), '\'' + string + "' was not blank");
    }

    @ParameterizedTest(name="[{index}] \"{0}\"")
    @ValueSource(strings={"abc", " abc ", "\u2022", " \u2022 "})
    public void testNotBlank(final String string){
         Assertions.assertFalse(Java9.String.isBlank(string), '\'' + string + "' was blank");
    }

    //

    @Test
    public void testMatcher(){
        // replaceAll
        final String raw = " 123456789 ";
        final Matcher matcher = Pattern.compile("\\d").matcher(" 123456789 ");

        Assertions.assertThrows(NullPointerException.class, () -> Java9.Matcher.replaceAll(null, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> Java9.Matcher.replaceAll(raw, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> Java9.Matcher.replaceAll(raw,matcher, null));

        Assertions.assertEquals(matcher.replaceAll("0"), Java9.Matcher.replaceAll(raw, matcher, e -> "0"));
        Assertions.assertEquals(" 012345678 ", Java9.Matcher.replaceAll(raw, matcher, e -> String.valueOf(Integer.parseInt(e.group(0)) - 1)));

        // count
        Assertions.assertThrows(NullPointerException.class, () -> Java9.Matcher.count(null));

        Assertions.assertEquals(9, Java9.Matcher.count(Pattern.compile("\\d").matcher(" 123456789 ")));
        Assertions.assertEquals(1, Java9.Matcher.count(Pattern.compile("\\d+").matcher(" 123456789 ")));
    }

}