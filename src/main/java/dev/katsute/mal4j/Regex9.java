/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
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

package dev.katsute.mal4j;

import java.util.Objects;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

/**
 * Brings Java 9 regex methods down to Java 8.
 */
abstract class Regex9 {

    /**
     * Replaces all using a function. Make sure that the function does not modify the matcher.
     *
     * @param str string used in matcher
     * @param matcher matcher
     * @param replacer replacement function
     * @return replaced string
     */
    @SuppressWarnings("StringBufferMayBeStringBuilder")
    static String replaceAll(final String str, final Matcher matcher, final Function<MatchResult,java.lang.String> replacer){
        Objects.requireNonNull(str);
        Objects.requireNonNull(matcher);
        Objects.requireNonNull(replacer);
        matcher.reset();
        boolean result = matcher.find();
        if (result){
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
    static int count(final Matcher matcher){
        Objects.requireNonNull(matcher);
        matcher.reset();
        int count = 0;
        while(matcher.find())
            count++;
        return count;
    }

}