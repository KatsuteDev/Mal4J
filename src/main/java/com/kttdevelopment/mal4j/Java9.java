/*
 * Copyright (C) 2021 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.kttdevelopment.mal4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * Brings Java 9 features down to Java 8.
 */
abstract class Java9 {

    static class URLEncoder {

        /**
         * Encodes a string.
         *
         * @param s string to encode
         * @param enc encoding
         * @return encoded string
         *
         * @see Charset
         * @see java.nio.charset.StandardCharsets
         */
        static java.lang.String encode(final java.lang.String s, final Charset enc){
            try{
                return java.net.URLEncoder.encode(s, enc.name());
            }catch(final UnsupportedEncodingException e){
                Logging.getLogger().severe("This should not occur, please report this issue.");
                e.printStackTrace(); // shouldn't occur for case UTF-8
                return s;
            }
        }

    }

    static class URLDecoder {

        /**
         * Decodes a string.
         *
         * @param s string to decode
         * @param enc encoding
         * @return decoded string
         *
         * @see Charset
         * @see java.nio.charset.StandardCharsets
         */
        @SuppressWarnings("SameParameterValue")
        static java.lang.String decode(final java.lang.String s, final Charset enc){
            try{
                return java.net.URLDecoder.decode(s, enc.name());
            }catch(final UnsupportedEncodingException e){
                Logging.getLogger().severe("This should not occur, please report this issue.");
                e.printStackTrace(); // shouldn't occur for case UTF-8
                return s;
            }
        }

    }

    static class String {

        // [^\s\u1680]
        private static final Pattern nsp = Pattern.compile("[^\\s\\u1680]");

        /**
         * Returns if the string contains only whitespace.
         *
         * @param s string
         * @return if string is blank
         */
        static boolean isBlank(final java.lang.String s) {
            Objects.requireNonNull(s);
            return s.length() == 0 || !nsp.matcher(s).find();
        }

    }

    static class Matcher {

        /**
         * Replaces all using a function. Make sure that the function does not modify the matcher.
         *
         * @param str string used in matcher
         * @param matcher matcher
         * @param replacer replacement function
         * @return replaced string
         */
        @SuppressWarnings("StringBufferMayBeStringBuilder")
        static java.lang.String replaceAll(final java.lang.String str, final java.util.regex.Matcher matcher, final Function<MatchResult,java.lang.String> replacer) {
            Objects.requireNonNull(str);
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
            return str;
        }

        /**
         * Returns match count.
         *
         * @param matcher matcher
         * @return count
         */
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
