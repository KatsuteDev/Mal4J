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
abstract class Json {

    /*
     * Notable limitations:
     * - Allows dangling commas on last item in map and list
     * - Does not allow comments
     * - Ignores duplicate keys
     */

    // todo: fix so regex also handles escaped quotes instead of using chained replaceAll
    // (?<=[{\[,])|(?=[}\]])(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?!$)
    private static final Pattern lineSplit = Pattern.compile("(?<=[{\\[,])|(?=[}\\]])(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?!$)");

    // \\"
    private static final Pattern escQuote = Pattern.compile("\\\\\"");

    // \t
    private static final Pattern tab = Pattern.compile("\\t");

    // ^\s*(?<!\\)"(?<key>.+)(?<!\\)": ?((?<double>-?\d+\.?\d+) *,?|(?<int>-?\d+) *,?|(?<!\\)"(?<string>.*)(?<!\\)" *,?|(?<array>\[)|(?<map>\{))\s*$
    private static final Pattern mapType  = Pattern.compile("^\\s*(?<!\\\\)\"(?<key>.+)(?<!\\\\)\": ?((?<double>-?\\d+\\.?\\d+) *,?|(?<int>-?\\d+) *,?|(?<!\\\\)\"(?<string>.*)(?<!\\\\)\" *,?|(?<array>\\[)|(?<map>\\{))\\s*$");
    // ^\s*} *,?\s*$
    private static final Pattern mapClose = Pattern.compile("^\\s*} *,?\\s*$");

    // ^\s*((?<double>-?\d+\.?\d+) *,?|(?<int>-?\d+) *,?|(?<!\\)"(?<string>.*)(?<!\\)" *,?|(?<array>\[)|(?<map>\{))\s*$
    private static final Pattern arrType  = Pattern.compile("^\\s*((?<double>-?\\d+\\.?\\d+) *,?|(?<int>-?\\d+) *,?|(?<!\\\\)\"(?<string>.*)(?<!\\\\)\" *,?|(?<array>\\[)|(?<map>\\{))\\s*$");
    // ^\s*] *,?\s*$
    private static final Pattern arrClose = Pattern.compile("^\\s*] *,?\\s*$");

    /**
     * Returns json as a array. <b>Mutable</b>.
     *
     * @param json json string
     * @return parsed json
     *
     * @see #parse(String)
     * @since ?
     */
    static List<?> parseArray(final String json){
        return (List<?>) parse(json);
    }

    /**
     * Returns json as a map. <b>Mutable</b>.
     *
     * @param json json string
     * @return parsed json
     *
     * @see #parse(String)
     * @since ?
     */
    @SuppressWarnings("unchecked")
    static Map<String,?> parseMap(final String json){
        return (Map<String,?>) parse(json);
    }

    /**
     * Returns json as a map or array. <b>Mutable</b>.
     *
     * @param json json string
     * @return parsed json
     *
     * @see #parseArray(String)
     * @see #parseMap(String)
     * @since ?
     */
    static Object parse(final String json){
        Objects.requireNonNull(json);
        // split json into multiple lines
        final String lines =
            escQuote.matcher(json).matches()
            ? tab.matcher( // hacky method if contains escaped quotes
                    lineSplit.matcher(
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
                    throw new JsonSyntaxException("Unexpected starting character: '" + (ln.length() == 1 ? ln.charAt(0) : "") + "' expected '{' or '['");
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
                    list.add(Double.parseDouble(raw));
                else if((raw = matcher.group("int")) != null)
                    list.add(Integer.parseInt(raw));
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

    private static Map<String,?> openMap(final BufferedReader reader) throws IOException{
        final Matcher matcher = mapType.matcher("");
        final Matcher strMatcher = escQuote.matcher("");
        final Map<String,Object> map = new HashMap<>();
        String ln;
        while((ln = reader.readLine()) != null){
            ln = ln.trim();
            if(matcher.reset(ln).matches()){
                final String key = strMatcher.reset(matcher.group("key")).replaceAll("\"");
                String raw;
                if((raw = matcher.group("double")) != null)
                    map.put(key, Double.parseDouble(raw));
                else if((raw = matcher.group("int")) != null)
                    map.put(key, Integer.parseInt(raw));
                else if((raw = matcher.group("string")) != null)
                    map.put(key, strMatcher.reset(raw).replaceAll("\""));
                else if(matcher.group("array") != null) // open new array
                    map.put(key, openArray(reader));
                else if(matcher.group("map") != null) // open new map
                    map.put(key, openMap(reader));
            }else if(mapClose.matcher(ln).matches())
                return map;
            else
                throw new JsonSyntaxException("Unexpected object value syntax: " + ln);
        }
        throw new JsonSyntaxException("Object was missing closing character '}'");
    }

    static class JsonSyntaxException extends RuntimeException {

        JsonSyntaxException(final String message){
            super(message);
        }

    }

}
