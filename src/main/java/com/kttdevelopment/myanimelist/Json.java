package com.kttdevelopment.myanimelist;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A massively simplified json parsing class. Supports the bare minimum read requirements for the REST API responses.
 */
@SuppressWarnings("SpellCheckingInspection")
abstract class Json {

    /*
     * Notable issues:
     * - Allows dangling commas on last item in map and list
     */

    // [\{\}\[\],]
    private static final Pattern split = Pattern.compile("[{}\\[\\],]");

    // (?<!\\)(?:\\\\)*"
    private static final Pattern nonEscQuote = Pattern.compile("(?<!\\\\)(?:\\\\\\\\)*\"");

    // \\"
    private static final Pattern escQuote =
        Pattern.compile("\\\\\"");

    // \\\/
    private static final Pattern escFwdSlash =
        Pattern.compile("\\\\/");

    // \\\\
    private static final Pattern escBackSlash =
        Pattern.compile("\\\\\\\\");

    // ^\s*(?<!\\)"(?<key>.+(?<!\\)(?:\\\\)*)": ?((?<double>-?\d+\.\d+) *,?|(?<int>-?\d+) *,?|(?<boolean>\Qtrue\E|\Qfalse\E) *,?|(?<null>\Qnull\E) *,?|(?<!\\)"(?<string>.*(?<!\\)(?:\\\\)*)" *,?|(?<array>\[)|(?<map>\{))\s*$
    private static final Pattern mapType =
        Pattern.compile("^\\s*(?<!\\\\)\"(?<key>.+(?<!\\\\)(?:\\\\\\\\)*)\": ?((?<double>-?\\d+\\.\\d+) *,?|(?<int>-?\\d+) *,?|(?<boolean>\\Qtrue\\E|\\Qfalse\\E) *,?|(?<null>\\Qnull\\E) *,?|(?<!\\\\)\"(?<string>.*(?<!\\\\)(?:\\\\\\\\)*)\" *,?|(?<array>\\[)|(?<map>\\{))\\s*$");
    // ^\s*} *,?\s*$
    private static final Pattern mapClose =
        Pattern.compile("^\\s*} *,?\\s*$");

    // ^\s*((?<double>-?\d+\.\d+) *,?|(?<int>-?\d+) *,?|(?<boolean>\Qtrue\E|\Qfalse\E) *,?|(?<null>\Qnull\E) *,?|(?<!\\)"(?<string>.*(?<!\\)(?:\\\\)*)" *,?|(?<array>\[)|(?<map>\{))\s*$
    private static final Pattern arrType =
        Pattern.compile("^\\s*((?<double>-?\\d+\\.\\d+) *,?|(?<int>-?\\d+) *,?|(?<boolean>\\Qtrue\\E|\\Qfalse\\E) *,?|(?<null>\\Qnull\\E) *,?|(?<!\\\\)\"(?<string>.*(?<!\\\\)(?:\\\\\\\\)*)\" *,?|(?<array>\\[)|(?<map>\\{))\\s*$");

    // ^\s*] *,?\s*$
    private static final Pattern arrClose =
        Pattern.compile("^\\s*] *,?\\s*$");

    /**
     * Returns json as a JsonObject or List. <b>Mutable</b>.
     *
     * @param json json string
     * @return parsed json
     *
     * @see JsonObject
     */
    static Object parse(final String json){
        Objects.requireNonNull(json);

        // split by symbols {}[], except within non-escaped quotes
        final StringBuilder OUT = new StringBuilder();
        int lastMatch = -1; // the index after the previous match
        final Matcher matcher = split.matcher(json);
        final Matcher quotes = nonEscQuote.matcher("");
        while(matcher.find()){ // while still contains line splitting symbol
            final int index = matcher.end() - 1; // before the comma/split character
            final String after = json.substring(index + 1);
            final long count = quotes.reset(after).results().count();
            if(count %2 == 0){ // even means symbol is not within quotes
                if(lastMatch != -1) // if not first (no before content)
                    OUT.append(json, lastMatch, index); // add content between last match and here
                lastMatch = index + 1;
                final char ch = matcher.group().charAt(0);
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

    private static List<?> openArray(final BufferedReader reader) throws IOException{
        final Matcher matcher = arrType.matcher("");
        final Matcher escQuoteMatcher = escQuote.matcher("");
        final Matcher escFwdSlashMatcher = escFwdSlash.matcher("");
        final Matcher escBackSlashMatcher = escBackSlash.matcher("");

        final List<Object> list = new ArrayList<>();
        String ln;
        while((ln = reader.readLine()) != null){ // while not closing tag
            ln = ln.trim();
            if(matcher.reset(ln).matches()){
                String raw;
                if((raw = matcher.group("double")) != null)
                    try{
                        list.add(Double.parseDouble(raw));
                    }catch(final NumberFormatException ignored){ // only occurs if too large
                        list.add(Long.parseLong(raw));
                    }
                else if((raw = matcher.group("int")) != null)
                    try{
                        list.add(Integer.parseInt(raw));
                    }catch(final NumberFormatException ignored){ // only occurs if too large
                        list.add(Long.parseLong(raw));
                    }
                else if((raw = matcher.group("boolean")) != null)
                    list.add(Boolean.parseBoolean(raw));
                else if(matcher.group("null") != null)
                    list.add(null);
                else if((raw = matcher.group("string")) != null)
                    list.add(
                        escBackSlashMatcher.reset(
                            escFwdSlashMatcher.reset(
                                escQuoteMatcher.reset(raw)
                                .replaceAll("\""))
                            .replaceAll("/"))
                        .replaceAll("\\\\")
                    );
                else if(matcher.group("array") != null) // open new array
                    list.add(openArray(reader));
                else if(matcher.group("map") != null) // open new map
                    list.add(openMap(reader));
            }else if(arrClose.matcher(ln).matches())
                return list;
            else if(!ln.isBlank())
                throw new JsonSyntaxException("Unexpected array value syntax: '" + ln + '\'');
        }
        throw new JsonSyntaxException("Object was missing closing character: ']'");
    }

    private static JsonObject openMap(final BufferedReader reader) throws IOException{
        final Matcher matcher = mapType.matcher("");
        final Matcher escQuoteMatcher = escQuote.matcher("");
        final Matcher escFwdSlashMatcher = escFwdSlash.matcher("");
        final Matcher escBackSlashMatcher = escBackSlash.matcher("");

        final JsonObject obj = new JsonObject();
        String ln;
        while((ln = reader.readLine()) != null){
            ln = ln.trim();
            if(matcher.reset(ln).matches()){
                final String key =
                    escBackSlashMatcher.reset(
                        escFwdSlashMatcher.reset(
                            escQuoteMatcher.reset(matcher.group("key"))
                            .replaceAll("\""))
                        .replaceAll("/"))
                    .replaceAll("\\\\");
                String raw;
                if((raw = matcher.group("double")) != null)
                    try{
                        obj.set(key, Double.parseDouble(raw));
                    }catch(final NumberFormatException ignored){ // only occurs if too large
                        obj.set(key, Long.parseLong(raw));
                    }
                else if((raw = matcher.group("int")) != null)
                    try{
                        obj.set(key, Integer.parseInt(raw));
                    }catch(final NumberFormatException ignored){ // only occurs if too large
                        obj.set(key, Long.parseLong(raw));
                    }
                else if((raw = matcher.group("boolean")) != null)
                    obj.set(key, Boolean.parseBoolean(raw));
                else if(matcher.group("null") != null)
                    obj.set(key, null);
                else if((raw = matcher.group("string")) != null)
                    obj.set(
                        key,
                        escBackSlashMatcher.reset(
                            escFwdSlashMatcher.reset(
                                escQuoteMatcher.reset(raw)
                                .replaceAll("\""))
                            .replaceAll("/"))
                        .replaceAll("\\\\")
                    );
                else if(matcher.group("array") != null) // open new array
                    obj.set(key, openArray(reader));
                else if(matcher.group("map") != null) // open new map
                    obj.set(key, openMap(reader));
            }else if(mapClose.matcher(ln).matches())
                return obj;
            else if(!ln.isBlank())
                throw new JsonSyntaxException("Unexpected object value syntax: '" + ln + '\'');
        }
        throw new JsonSyntaxException("Object was missing closing character: '}'");
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
     * Thrown then the json is malformed
     */
    static class JsonSyntaxException extends RuntimeException {

        JsonSyntaxException(final String message){
            super(message);
        }

    }

}
