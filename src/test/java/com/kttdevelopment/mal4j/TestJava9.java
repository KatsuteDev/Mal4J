package com.kttdevelopment.mal4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJava9 {

    @SuppressWarnings("SpellCheckingInspection")
    private static String[][] getDecodeParameters(){
        return new String[][]{
            {"The string \u00FC@foo-bar"}, // the string from javadoc example
            {""}, // an empty string
            {"x"}, // a string of length 1
            {"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-.*"}, // the string of characters should remain the same
            {charactersRange('\u0000', '\u007F')}, // a string of characters from 0 to 127
            {charactersRange('\u0080', '\u00FF')}, // a string of characters from 128 to 255
            {"\u0100 \u0101 \u0555 \u07FD \u07FF"}, // a string of Unicode values can be expressed as 2 bytes
            {"\u8000 \u8001 \uA000 \uFFFD \uFFFF"}, // a string of Unicode values can be expressed as 3 bytes
        };
    }

    private static String charactersRange(final char c1, final char c2) {
        final StringBuilder sb = new StringBuilder(c2 - c1);
        for (char c = c1; c < c2; c++)
            sb.append(c);
        return sb.toString();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testURLEncoder() throws UnsupportedEncodingException{
        // jdk.java.net.URLEncoder.EncodingTest
        {
            for(final String[] decodeParameter : getDecodeParameters())
                for(final String s : decodeParameter){
                    String enc1, enc2;
                    Assertions.assertEquals(
                        enc1 = URLEncoder.encode(s, StandardCharsets.UTF_8.name()),
                        enc2 = Java9.URLEncoder.encode(s, StandardCharsets.UTF_8)
                    );
                    // decoder
                    Assertions.assertEquals(
                        URLDecoder.decode(enc1, StandardCharsets.UTF_8.name()),
                        Java9.URLDecoder.decode(enc2, StandardCharsets.UTF_8)
                    );
                }
        }
        // jdk.java.net.URLEncoder.URLEncoderEncodeArgs
        Assertions.assertThrows(NullPointerException.class, () -> Java9.URLEncoder.encode("Hello World", null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testURLDecoder(){
        // jdk.java.net.URLDecoder.URLDecoderArgs
        Assertions.assertThrows(NullPointerException.class, () -> Java9.URLDecoder.decode("Hello World", null));
    }

    private static String[] blank(){
        return new String[]{"", " ", " \t", " \t\n", " \u1680 "};
    }

    private static String[] notBlank(){
        return new String[]{" abc ", " abc\u2022 "};
    };

    @Test // jdk.java.lang.String.IsBlank
    public void testString(){
        Assertions.assertThrows(NullPointerException.class, () -> Java9.String.isBlank(null));

        for(final String s : blank())
            Assertions.assertTrue(Java9.String.isBlank(s));
        for(final String s : notBlank())
            Assertions.assertFalse(Java9.String.isBlank(s));
    }

    @Test
    public void testMatcher(){
        // replaceAll
        final Matcher matcher = Pattern.compile("\\d").matcher(" 123456789 ");

        Assertions.assertThrows(NullPointerException.class, () -> Java9.Matcher.replaceAll(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> Java9.Matcher.replaceAll(matcher, null));

        Assertions.assertEquals(matcher.replaceAll("0"), Java9.Matcher.replaceAll(matcher, e -> "0"));
        Assertions.assertEquals(" 012345678 ", Java9.Matcher.replaceAll(matcher, e -> String.valueOf(Integer.parseInt(e.group(1)) - 1)));

        // count
        Assertions.assertThrows(NullPointerException.class, () -> Java9.Matcher.count(null));

        Assertions.assertEquals(9, Java9.Matcher.count(Pattern.compile("\\d").matcher(" 123456789 ")));
    }

}
