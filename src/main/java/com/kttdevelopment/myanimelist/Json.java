package com.kttdevelopment.myanimelist;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A massively simplified json parsing class. Supports the bare minimum read requirements for the REST API responses.
 * 
 * @since ?
 * @version ?
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
abstract class Json {

    /*
     * Notable limitations:
     * - Allows dangling commas on last item in map and list
     * - Inefficient splitting caused by escaped quotes
     * - Does not allow comments
     * - Ignores duplicate keys
     */

    // todo: fix so regex also handles escaped quotes instead of using chained replaceAll
    // (?<=[{\[,]|(?=[}\]]))(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?!$)
    private static final Pattern lineSplit = // fixme
        Pattern.compile("(?<=[{\\[,]|(?=[}\\]]))(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?!$)");

    // \\"
    private static final Pattern escQuote =
        Pattern.compile("\\\\\"");

    // \t
    private static final Pattern tab =
        Pattern.compile("\\t");

    // ^\s*(?<!\\)"(?<key>.+)(?<!\\)": ?((?<double>-?\d+\.\d+) *,?|(?<int>-?\d+) *,?|(?<boolean>\Qtrue\E|\Qfalse\E) *,?|(?<null>\Qnull\E) *,?|(?<!\\)"(?<string>.*)(?<!\\)" *,?|(?<array>\[)|(?<map>\{))\s*$
    private static final Pattern mapType =
        Pattern.compile("^\\s*(?<!\\\\)\"(?<key>.+)(?<!\\\\)\": ?((?<double>-?\\d+\\.\\d+) *,?|(?<int>-?\\d+) *,?|(?<boolean>\\Qtrue\\E|\\Qfalse\\E) *,?|(?<null>\\Qnull\\E) *,?|(?<!\\\\)\"(?<string>.*)(?<!\\\\)\" *,?|(?<array>\\[)|(?<map>\\{))\\s*$");
    // ^\s*} *,?\s*$
    private static final Pattern mapClose =
        Pattern.compile("^\\s*} *,?\\s*$");

    // ^\s*((?<double>-?\d+\.\d+) *,?|(?<int>-?\d+) *,?|(?<boolean>\Qtrue\E|\Qfalse\E) *,?|(?<null>\Qnull\E) *,?|(?<!\\)"(?<string>.*)(?<!\\)" *,?|(?<array>\[)|(?<map>\{))\s*$
    private static final Pattern arrType =
        Pattern.compile("^\\s*((?<double>-?\\d+\\.\\d+) *,?|(?<int>-?\\d+) *,?|(?<boolean>\\Qtrue\\E|\\Qfalse\\E) *,?|(?<null>\\Qnull\\E) *,?|(?<!\\\\)\"(?<string>.*)(?<!\\\\)\" *,?|(?<array>\\[)|(?<map>\\{))\\s*$");

    // ^\s*] *,?\s*$
    private static final Pattern arrClose =
        Pattern.compile("^\\s*] *,?\\s*$");

    /**
     * Returns json as a map or array. <b>Mutable</b>.
     *
     * @param json json string
     * @return parsed json
     *
     * @since ?
     */
    static Object parse(final String json){
        Objects.requireNonNull(json);
        // split json into multiple lines
        final String lines =
            escQuote.matcher(json).find()
            ? tab.matcher( // hacky method if contains escaped quotes
                    lineSplit.matcher( // fixme - causes stack overflow
                        escQuote.matcher(
                            tab.matcher(json)
                            .replaceAll("  ") // replace tabs with two spaces
                        ).replaceAll("\t") // replace escaped quotes with tab
                    ).replaceAll("\n") // replace separator with new line
                ).replaceAll("\\\"") // replace escaped quotes (represented with tabs) with re-escaped quotes
            : lineSplit.matcher(json).replaceAll("\n");

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
        final Matcher strMatcher = escQuote.matcher("");
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
                    list.add(strMatcher.reset(raw).replaceAll("\""));
                else if(matcher.group("array") != null) // open new array
                    list.add(openArray(reader));
                else if(matcher.group("map") != null) // open new map
                    list.add(openMap(reader));
            }else if(arrClose.matcher(ln).matches())
                return list;
            else
                throw new JsonSyntaxException("Unexpected array value syntax: " + ln);
        }
        throw new JsonSyntaxException("Object was missing closing character ']'");
    }

    private static JsonObject openMap(final BufferedReader reader) throws IOException{
        final Matcher matcher = mapType.matcher("");
        final Matcher strMatcher = escQuote.matcher("");
        final JsonObject obj = new JsonObject();
        String ln;
        while((ln = reader.readLine()) != null){
            ln = ln.trim();
            if(matcher.reset(ln).matches()){
                final String key = strMatcher.reset(matcher.group("key")).replaceAll("\"");
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
                    obj.set(key, strMatcher.reset(raw).replaceAll("\""));
                else if(matcher.group("array") != null) // open new array
                    obj.set(key, openArray(reader));
                else if(matcher.group("map") != null) // open new map
                    obj.set(key, openMap(reader));
            }else if(mapClose.matcher(ln).matches())
                return obj;
            else
                throw new JsonSyntaxException("Unexpected object value syntax: " + ln);
        }
        throw new JsonSyntaxException("Object was missing closing character '}'");
    }

    // objects

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

    static class JsonSyntaxException extends RuntimeException {

        JsonSyntaxException(final String message){
            super(message);
        }

    }

}
