package com.kttdevelopment.mal4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.*;

/**
 * Brings Java 9 features down to Java 8.
 */
abstract class Java9 {

    static class URLEncoder {

        static java.lang.String encode(final java.lang.String s, final Charset enc){
            try{
                return java.net.URLEncoder.encode(s, enc.name());
            }catch(final UnsupportedEncodingException e){
                System.out.println("This should not occur, please report this issue.");
                e.printStackTrace(); // shouldn't occur for case UTF-8
                return s;
            }
        }

    }

    static class URLDecoder {

        static java.lang.String decode(final java.lang.String s, final Charset enc){
            try{
                return java.net.URLDecoder.decode(s, enc.name());
            }catch(final UnsupportedEncodingException e){
                System.out.println("This should not occur, please report this issue.");
                e.printStackTrace(); // shouldn't occur for case UTF-8
                return s;
            }
        }

    }

    static class String {

        // [^\s\u1680]
        private static final Pattern nsp = Pattern.compile("[^\\s\\u1680]");

        static boolean isBlank(final java.lang.String s) {
            Objects.requireNonNull(s);
            return s.length() == 0 || !nsp.matcher(s).find();
        }

    }

    static class Matcher {

        // replacer must not modify matcher
        static java.lang.String replaceAll(final java.util.regex.Matcher matcher, final Function<MatchResult,java.lang.String> replacer) {
            Objects.requireNonNull(matcher);
            Objects.requireNonNull(replacer);
            matcher.reset();
            boolean result = matcher.find();
            if (result) {
                final StringBuffer sb = new StringBuffer();
                do{
                    java.lang.String replacement = replacer.apply(matcher);
                    matcher.appendReplacement(sb, replacement);
                    result = matcher.find();
                }while(result);
                matcher.appendTail(sb);
                return sb.toString();
            }
            return matcher.toString();
        }

        static int count(final java.util.regex.Matcher matcher){
            Objects.requireNonNull(matcher);
            matcher.reset();
            int count = 0;
            while(matcher.find())
                count++;
            return count;
        }

    }

}
