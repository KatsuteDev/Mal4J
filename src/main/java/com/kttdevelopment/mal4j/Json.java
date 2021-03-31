/*
 * Copyright (C) 2021 Ktt Development
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

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.*;

/**
 * A massively simplified json parsing class. Supports the bare minimum read requirements for the REST API responses.
 */
@SuppressWarnings("SpellCheckingInspection")
class Json {

    /*
     * Notable issues:
     * - Allows dangling commas on last item in map and list
     */

    // [\{\}\[\],]
    @SuppressWarnings("RegExpRedundantEscape") // android requires this syntax (#133)
    private static final Pattern split = Pattern.compile("[\\{\\}\\[\\],]");

    // (?<!\\)(?:\\\\)*"
    private static final Pattern nonEscQuote = Pattern.compile("(?<!\\\\)(?:\\\\\\\\)*\"");

    // (?<!\\)\\u([\da-f]{4})
    private static final Pattern escUnicode =
        Pattern.compile("(?<!\\\\)\\\\u([\\da-f]{4})");

    private static final Function<MatchResult,String> unicodeReplacer = matchResult -> String.valueOf((char) Integer.parseInt(matchResult.group(1), 16));

    // \\"|\\\/|\\\\
    @SuppressWarnings("RegExpRedundantEscape") // android requires this syntax (#133)
    private static final Pattern escapedCharacters =
        Pattern.compile("\\\\\"|\\\\\\/|\\\\\\\\");

    private static final Function<MatchResult,String> escapedReplacer = matchResult -> {
        final String chars = matchResult.group(0);
        switch(chars){
            case "\\\"":
                return "\"";
            case "\\/":
                return "/";
            case "\\\\":
                return "\\\\"; // this needs to be \\ to return \ for some reason
            default:
                return chars;
        }
    };

    // ^\s*(?<!\\)"(?<key>.+(?<!\\)(?:\\\\)*)": ?((?<double>-?\d+\.\d+) *,?|(?<int>-?\d+) *,?|(?<boolean>\Qtrue\E|\Qfalse\E) *,?|(?<null>\Qnull\E) *,?|(?<!\\)"(?<string>.*(?<!\\)(?:\\\\)*)" *,?|(?<array>\[)|(?<map>\{))\s*$
    private static final Pattern mapType =
        Pattern.compile("^\\s*(?<!\\\\)\"(?<key>.+(?<!\\\\)(?:\\\\\\\\)*)\": ?((?<double>-?\\d+\\.\\d+) *,?|(?<int>-?\\d+) *,?|(?<boolean>\\Qtrue\\E|\\Qfalse\\E) *,?|(?<null>\\Qnull\\E) *,?|(?<!\\\\)\"(?<string>.*(?<!\\\\)(?:\\\\\\\\)*)\" *,?|(?<array>\\[)|(?<map>\\{))\\s*$");
    // ^\s*} *,?\s*$
    @SuppressWarnings("RegExpRedundantEscape") // android requires this syntax (#133)
    private static final Pattern mapClose =
        Pattern.compile("^\\s*\\} *,?\\s*$");

    // ^\s*((?<double>-?\d+\.\d+) *,?|(?<int>-?\d+) *,?|(?<boolean>\Qtrue\E|\Qfalse\E) *,?|(?<null>\Qnull\E) *,?|(?<!\\)"(?<string>.*(?<!\\)(?:\\\\)*)" *,?|(?<array>\[)|(?<map>\{))\s*$
    private static final Pattern arrType =
        Pattern.compile("^\\s*((?<double>-?\\d+\\.\\d+) *,?|(?<int>-?\\d+) *,?|(?<boolean>\\Qtrue\\E|\\Qfalse\\E) *,?|(?<null>\\Qnull\\E) *,?|(?<!\\\\)\"(?<string>.*(?<!\\\\)(?:\\\\\\\\)*)\" *,?|(?<array>\\[)|(?<map>\\{))\\s*$");

    // ^\s*] *,?\s*$
    private static final Pattern arrClose =
        Pattern.compile("^\\s*] *,?\\s*$");

    // \r?\n
    private static final Pattern newline =
        Pattern.compile("\\r?\\n");

    //

    private final Matcher splitMatcher = split.matcher("");
    private final Matcher nonEscQuoteMatcher = nonEscQuote.matcher("");

    private final Matcher arrayMatcher = arrType.matcher("");
    private final Matcher mapMatcher = mapType.matcher("");

    private final Matcher unicodeMatcher = escUnicode.matcher("");
    private final Matcher escapedMatcher = escapedCharacters.matcher("");

    private Json(){ }

    // required for lambda
    static Object parse(final String json){
        return new Json().parseJson(json);
    }

    /**
     * Returns json as a JsonObject or List. <b>Mutable</b>.
     *
     * @param json json string
     * @return parsed json
     *
     * @see JsonObject
     */
    private synchronized Object parseJson(final String json){
        Objects.requireNonNull(json);
        final String flatJson = newline.matcher(json).replaceAll("");

        // split by symbols {}[], except within non-escaped quotes
        final StringBuilder OUT = new StringBuilder();
        int lastMatch = -1; // the index after the previous match
        splitMatcher.reset(flatJson);
        final Matcher quotes = nonEscQuoteMatcher.reset();
        while(splitMatcher.find()){ // while still contains line splitting symbol
            final int index = splitMatcher.end() - 1; // before the comma/split character
            final String after = flatJson.substring(index + 1);
            final long count = Java9.Matcher.count(quotes.reset(after));
            if(count %2 == 0){ // even means symbol is not within quotes
                if(lastMatch != -1) // if not first (no before content)
                    OUT.append(flatJson, lastMatch, index); // add content between last match and here
                lastMatch = index + 1;
                final char ch = splitMatcher.group().charAt(0);
                switch(ch){ // determine where to break line
                    case '{':
                    case '[':
                    case ',':
                        OUT.append(ch).append('\n');
                        break;
                    case '}':
                    case ']':
                        OUT.append('\n').append(ch);
                        break;
                }
            }
        }

        // parse line by line
        final String lines = lastMatch == 0 ? json : OUT.toString();

        try(final BufferedReader IN = new BufferedReader(new StringReader(lines))){
            final String line = IN.readLine();
            if(line != null){
                final String ln = line.trim();
                if(ln.equals("{"))
                    return openMap(IN);
                else if(ln.equals("["))
                    return openArray(IN);
                else
                    throw new JsonSyntaxException("Unexpected starting character: '" + ln + "' expected '{' or '['");
            }else
                throw new JsonSyntaxException("Json string was empty");
        }catch(final IOException e){ // should never occur, but just in case:
            throw new UncheckedIOException(e);
        }
    }

    private List<?> openArray(final BufferedReader reader) throws IOException{
        final List<Object> list = new ArrayList<>();
        String ln;
        while((ln = reader.readLine()) != null){ // while not closing tag
            ln = ln.trim();
            if(arrayMatcher.reset(ln).matches()){
                String raw;
                if((raw = arrayMatcher.group("double")) != null)
                    try{
                        list.add(Double.parseDouble(raw));
                    }catch(final NumberFormatException ignored){ // only occurs if too large
                        list.add(Long.parseLong(raw));
                    }
                else if((raw = arrayMatcher.group("int")) != null)
                    try{
                        list.add(Integer.parseInt(raw));
                    }catch(final NumberFormatException ignored){ // only occurs if too large
                        list.add(Long.parseLong(raw));
                    }
                else if((raw = arrayMatcher.group("boolean")) != null)
                    list.add(Boolean.parseBoolean(raw));
                else if(arrayMatcher.group("null") != null)
                    list.add(null);
                else if((raw = arrayMatcher.group("string")) != null)
                    list.add(decodeString(raw));
                else if(arrayMatcher.group("array") != null) // open new array
                    list.add(openArray(reader));
                else if(arrayMatcher.group("map") != null) // open new map
                    list.add(openMap(reader));
            }else if(arrClose.matcher(ln).matches())
                return list;
            else if(!Java9.String.isBlank(ln))
                throw new JsonSyntaxException("Unexpected array value syntax: '" + ln + '\'');
        }
        throw new JsonSyntaxException("Object was missing closing character: ']'");
    }

    private JsonObject openMap(final BufferedReader reader) throws IOException{
        final JsonObject obj = new JsonObject();
        String ln;
        while((ln = reader.readLine()) != null){
            ln = ln.trim();
            if(mapMatcher.reset(ln).matches()){
                final String key = decodeString(mapMatcher.group("key"));
                String raw;
                if((raw = mapMatcher.group("double")) != null)
                    try{
                        obj.set(key, Double.parseDouble(raw));
                    }catch(final NumberFormatException ignored){ // only occurs if too large
                        obj.set(key, Long.parseLong(raw));
                    }
                else if((raw = mapMatcher.group("int")) != null)
                    try{
                        obj.set(key, Integer.parseInt(raw));
                    }catch(final NumberFormatException ignored){ // only occurs if too large
                        obj.set(key, Long.parseLong(raw));
                    }
                else if((raw = mapMatcher.group("boolean")) != null)
                    obj.set(key, Boolean.parseBoolean(raw));
                else if(mapMatcher.group("null") != null)
                    obj.set(key, null);
                else if((raw = mapMatcher.group("string")) != null)
                    obj.set(
                        key,
                        decodeString(raw)
                    );
                else if(mapMatcher.group("array") != null) // open new array
                    obj.set(key, openArray(reader));
                else if(mapMatcher.group("map") != null) // open new map
                    obj.set(key, openMap(reader));
            }else if(mapClose.matcher(ln).matches())
                return obj;
            else if(!Java9.String.isBlank(ln))
                throw new JsonSyntaxException("Unexpected object value syntax: '" + ln + '\'');
        }
        throw new JsonSyntaxException("Object was missing closing character: '}'");
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private String decodeString(final String raw){
        final String unicodeEscape = Java9.Matcher.replaceAll(raw, unicodeMatcher.reset(raw), unicodeReplacer);
        final String slashEscape   = Java9.Matcher.replaceAll(unicodeEscape, escapedMatcher.reset(unicodeEscape), escapedReplacer);
        return slashEscape;
    }

    // objects

    /**
     * Represents map as a json object.
     */
    static class JsonObject {

        private final Map<String,Object> map = new HashMap<>();

        JsonObject(){ }

        public final Object get(final String key){
            return map.get(key);
        }

        public final String getString(final String key){
            final Object value = map.get(key);
            return
                value == null
                ? null
                : value instanceof String
                    ? (String) value
                    : value.toString();
        }

        public final int getInt(final String key){
            final Object value = map.get(key);
            return value instanceof String ? Integer.parseInt((String) value) : ((Number) value).intValue();
        }

        public final double getDouble(final String key){
            final Object value = map.get(key);
            return value instanceof String ? Double.parseDouble((String) value) : ((Number) value).doubleValue();
        }

        public final float getFloat(final String key){
            final Object value = map.get(key);
            return value instanceof String ? Float.parseFloat((String) value) : ((Number) value).floatValue();
        }

        public final long getLong(final String key){
            final Object value = map.get(key);
            return value instanceof String ? Long.parseLong((String) value) : ((Number) value).longValue();
        }

        public final boolean getBoolean(final String key){
            final Object value = map.get(key);
            return value instanceof String ? Boolean.parseBoolean((String) value) : (boolean) value;
        }

        public final JsonObject getJsonObject(final String key){
            return (JsonObject) map.get(key);
        }

        public final String[] getStringArray(final String key){
            final List<?> list = (List<?>) map.get(key);
            final List<String> arr = new ArrayList<>();
            for(final Object o : list)
                arr.add(o == null ? null : o instanceof String ? (String) o : o.toString());
            return arr.toArray(new String[0]);
        }

        public final JsonObject[] getJsonArray(final String key){
            final List<?> list = (List<?>) map.get(key);
            final List<JsonObject> arr = new ArrayList<>();
            for(final Object o : list)
                arr.add((JsonObject) o);
            return arr.toArray(new JsonObject[0]);
        }

        public final boolean containsKey(final String key){
            return map.containsKey(key);
        }

        public final int size(){
            return map.size();
        }

        private void set(final String key, final Object value){
            map.put(key, value);
        }

        @Override
        public String toString(){
            return "JsonObject{" +
                   "map=" + map +
                   '}';
        }

    }

    // exceptions

    /**
     * Thrown then the json is malformed.
     */
    static class JsonSyntaxException extends RuntimeException {

        JsonSyntaxException(final String message){
            super(message);
        }

    }

}
