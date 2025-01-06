/*
 * Copyright (C) 2025 Katsute <https://github.com/Katsute>
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

import dev.katsute.mal4j.exception.JsonSyntaxException;

import java.util.*;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.katsute.mal4j.Json.Expect.*;
import static dev.katsute.mal4j.Json.Type.*;

/**
 * A massively simplified json parsing class. Supports the bare minimum read requirements for the REST API responses.
 */
@SuppressWarnings("SpellCheckingInspection")
final class Json {

    private final String json;
    private final int len;

    private Json(final String json){
        this.json = Objects.requireNonNull(json);
        this.len = json.length();
    }

    static Object parse(final String json){
        return new Json(json).parse();
    }

    // tokens

    enum Expect { // which token to expect next

        START_OF_KEY,   // "
        KEY,            // any character except newline
        END_OF_KEY,     // closing "
        START_OF_VALUE, // any TYPE start ↓
        END_OF_VALUE,   // , or } or ]
        LITERAL,        // any TYPE continue ↓
        NUMBER,         // number or decimal

    }

    enum Type { // expected key or value type

        UNKNOWN,    // unknown / any type
        NULL,       // null
        BOOLEAN,    // true | false
        INTEGER,    // number or decimal
        DOUBLE,     // number only
        STRING      // any character except newline

    }

    // unicode

    // (?<!\\)\\u([\da-f]{4})
    private static final Pattern escUnicode = Pattern.compile("(?<!\\\\)\\\\u([\\da-f]{4})");

    private static final Function<MatchResult,String> unicodeReplacer = matchResult -> String.valueOf((char) Integer.parseInt(matchResult.group(1), 16));

    private final Matcher unicodeMatcher = escUnicode.matcher("");

    private String parseString(final String raw){ // replace unicode with unicode and escaped unicode with raw
        return Regex9.replaceAll(raw, unicodeMatcher.reset(raw), unicodeReplacer).replace("\\\\u", "\\u");
    }

    // parse

    // top level parse
    private Object parse(){
        if(len < 1)
            throw new JsonSyntaxException("Json string was empty", json);

        switch(json.charAt(0)){
            case '{': // map type
                if(json.charAt(len - 1) == '}')
                    return parseObject(json, 0, len);
                else
                    throw new JsonSyntaxException("Missing closing bracket '}'", json);
            case '[': // array type
                if(json.charAt(len - 1) == ']')
                    return parseArray(json, 0, len);
                else
                    throw new JsonSyntaxException("Missing closing bracket ']'", json);
            default:
                throw new JsonSyntaxException("Unexpected starting character: '" + json.charAt(0) + "', expected '{' or '['", json);
        }
    }

    // start should include starting token
    private List<Object> parseArray(final String json, final int start, final int end){
        final List<Object> list = new ArrayList<>();

        Expect E = START_OF_VALUE;
        Type T   = UNKNOWN; // expected type

        boolean isEscaped = false;

        String V = null; // current value

        for(int i = start + 1; i < end; i++){
            final char ch = json.charAt(i);
            switch(E){
                case START_OF_VALUE: // expecting beginning of value
                    switch(ch){
                        case ' ': // whitespace
                        case '\t':
                        case '\r':
                        case '\n':
                            continue;
                        case '[': // array
                            list.add(parseArray(json, i, (i = findEndToken(json, i, '[', ']')) + 1));
                            E = END_OF_VALUE;
                            continue;
                        case '{': // object
                            list.add(parseObject(json, i, (i = findEndToken(json, i, '{', '}')) + 1));
                            E = END_OF_VALUE;
                            continue;
                        case '"': // string
                            T = STRING;
                            E = LITERAL;
                            V = "";
                            continue;
                        case 'f': // boolean
                        case 't':
                            T = BOOLEAN;
                            E = LITERAL;
                            V = String.valueOf(ch);
                            continue;
                        case 'n': // null
                            T = NULL;
                            E = LITERAL;
                            V = String.valueOf(ch);
                            continue;
                        case '-': // negative
                            T = INTEGER;
                            E = NUMBER;
                            V = String.valueOf(ch);
                            continue;
                        case '0': // number
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            T = INTEGER;
                            E = LITERAL;
                            V = String.valueOf(ch);
                            continue;
                        case ']':
                            return list;
                        default:
                            throw new JsonSyntaxException("Unexpected literal '" + ch + "'", json);
                    }
                case END_OF_VALUE: // expecting end of value
                    switch(ch){
                        case ' ': // whitespace
                        case '\t':
                        case '\r':
                        case '\n':
                            continue;
                        case ',': // next in array
                        case ']': // end of array
                            E = START_OF_VALUE; // prepare for next
                            switch(T){ // push value
                                case NULL:
                                    list.add(null);
                                    break;
                                case BOOLEAN:
                                    list.add(Boolean.parseBoolean(V));
                                    break;
                                case INTEGER:
                                    try{
                                        list.add(Integer.parseInt(V));
                                    }catch(final NumberFormatException ignored){
                                        list.add(Long.parseLong(V));
                                    }
                                    break;
                                case DOUBLE:
                                    try{
                                        list.add(Double.parseDouble(V));
                                    }catch(final NumberFormatException ignored){
                                        list.add(Long.parseLong(V));
                                    }
                                    break;
                                case STRING:
                                    if(V != null)
                                        list.add(parseString(V));
                                    break;
                                default:
                                    break;
                            }
                            V = null;
                            T = UNKNOWN;
                            if(ch == ']') // end of array
                                return list;
                    }
                    continue;
                case LITERAL: // expecting literal value
                    switch(T){
                        case NULL: // parse null
                            V += ch;
                            if(!"null".startsWith(V)) // looks like null
                                throw new JsonSyntaxException("Unexpected value '" + V + "', expected null", json);
                            else if("null".equals(V)) // is null
                                E = END_OF_VALUE;
                            continue;
                        case BOOLEAN: // parse boolean
                            V += ch;
                            if(!"false".startsWith(V) && !"true".startsWith(V)) // looks like a boolean
                                throw new JsonSyntaxException("Unexpected value '" + V + "', expected a boolean", json);
                            else if("false".equals(V) || "true".equals(V)) // is a boolean
                                E = END_OF_VALUE;
                            continue;
                        case INTEGER:
                            switch(ch){ // parse integer
                                case ' ': // whitespace
                                case '\t':
                                case '\r':
                                case '\n':
                                    E = END_OF_VALUE;
                                    continue;
                                case '.': // convert to decimal
                                    T = DOUBLE;
                                    E = NUMBER;
                                    V += ch;
                                    continue;
                                case '0': // number
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                    V += ch;
                                    continue;
                                case ',': // end of value
                                case ']':
                                    E = END_OF_VALUE;
                                    i--;
                                    continue;
                                default:
                                    throw new JsonSyntaxException("Unexpected token '" + ch + "', expected a number or ','", json);
                            }
                        case DOUBLE:
                            switch(ch){ // parse double
                                case ' ': // whitespace
                                case '\t':
                                case '\r':
                                case '\n':
                                    E = END_OF_VALUE;
                                    continue;
                                case '.': // extra decimal
                                    throw new JsonSyntaxException("Unexpected token '.'", json);
                                case '0': // number
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                    V += ch;
                                    continue;
                                case ',': // end of value
                                case ']':
                                    E = END_OF_VALUE;
                                    i--;
                                    continue;
                                default:
                                    throw new JsonSyntaxException("Unexpected token '" + ch + "', expected a number", json);
                            }
                        case STRING:
                            switch(ch){
                                case '\r': // illegal whitespace
                                case '\n':
                                   throw new JsonSyntaxException("Unexpected token '" + ch + "'", json);
                                case '\\': // escaped
                                    if(isEscaped)
                                        V += ch;
                                    isEscaped = !isEscaped;
                                    continue;
                                case '"': // quote
                                    if(!isEscaped) // end of value
                                        E = END_OF_VALUE;
                                    else{ // escaped quote
                                        V += ch;
                                        isEscaped = false;
                                    }
                                    continue;
                                default:
                                    if(!isEscaped){ // literal
                                        if(ch == 'u' && i > 0 && json.charAt(i - 1) == '\\') // unicode escape
                                            V += '\\'; // add escape slash
                                        V += ch;
                                    }else{ // escaped
                                        isEscaped = false;
                                        switch(ch){
                                            case 't': // tab
                                                V += '\t';
                                                continue;
                                            case 'r': // carriage return
                                                V += '\r';
                                                continue;
                                            case 'n': // new line
                                                V += '\n';
                                                continue;
                                            case 'u': // unicode
                                                V += "\\u";
                                                continue;
                                            case '/': // slash
                                                V += '/';
                                                continue;
                                            default: // unknown
                                                throw new JsonSyntaxException("Unknown escape character '\\" + ch + "'", json);
                                        }
                                    }
                            }
                        continue;
                    }
                case NUMBER:
                    switch(ch){
                        case '0': // number
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            E = LITERAL;
                            V += ch;
                            continue;
                        default:
                            throw new JsonSyntaxException("Unexpected token '" + ch + "', expected a number", json);
                    }
            }
        }
        throw new JsonSyntaxException("Missing closing bracket ']'", json);
    }

    // start should include starting token
    private JsonObject parseObject(final String json, final int start, final int end){
        final JsonObject obj = new JsonObject();

        Expect E = START_OF_KEY;
        Type T   = UNKNOWN; // expected token type

        boolean isEscaped = false;

        String K = null; // current key
        String V = null; // current value

        for(int i = start + 1; i < end; i++){
            final char ch = json.charAt(i);
            switch(E){
                case START_OF_KEY: // expecting beginning of key
                    switch(ch){
                        case ' ': // whitespace
                        case '\t':
                        case '\r':
                        case '\n':
                            continue;
                        case '"': // string
                            E = KEY;
                            K = "";
                            continue;
                        case '}':
                            return obj;
                        default:
                            throw new JsonSyntaxException("Unexpected literal '" + ch + "', expected '\"'", json);
                    }
                case KEY: // expecting key value
                    switch(ch){
                        case '\r': // illegal whitespace
                        case '\n':
                            throw new JsonSyntaxException("Unexpected token '" + ch + "'", json);
                        case '\\':
                            if(isEscaped)
                                K += ch;
                            isEscaped = !isEscaped;
                            continue;
                        case '"': // quote
                            if(!isEscaped) // end of key
                                E = END_OF_KEY;
                            else{ // escaped quote
                                K += ch;
                                isEscaped = false;
                            }
                            continue;
                        default:
                            if(!isEscaped){ // literal
                                if(ch == 'u' && i > 0 && json.charAt(i - 1) == '\\') // unicode escape
                                    K += '\\'; // add escape slash
                                K += ch;
                            }else{ // escaped
                                isEscaped = false;
                                switch(ch){
                                    case 't': // tab
                                        K += '\t';
                                        continue;
                                    case 'r': // carriage return
                                        V += '\r';
                                        continue;
                                    case 'n': // new line
                                        K += '\n';
                                        continue;
                                    case '/': // slash
                                        K += '/';
                                        continue;
                                    case 'u': // unicode
                                        K += "\\u";
                                        continue;
                                    default: // unknown
                                        throw new JsonSyntaxException("Unknown escape character '\\" + ch + "'", json);
                                }
                            }
                    }
                    continue;
                case END_OF_KEY: // expecting end of key
                    switch(ch){
                        case ' ': // whitespace
                        case '\t':
                        case '\r':
                        case '\n':
                            continue;
                        case ':':
                            E = START_OF_VALUE;
                            continue;
                        default:
                            throw new JsonSyntaxException("Unexpected character '" + ch + "', expected ':'", json);
                    }
                case START_OF_VALUE: // expecting beginning of value
                    switch(ch){
                        case ' ': // whitespace
                        case '\t':
                        case '\r':
                        case '\n':
                            continue;
                        case '[': // array
                            obj.set(K, parseArray(json, i, (i = findEndToken(json, i, '[', ']')) + 1));
                            K = null;
                            E = END_OF_VALUE;
                            continue;
                        case '{': // object
                            obj.set(K, parseObject(json, i, (i = findEndToken(json, i, '{', '}')) + 1));
                            K = null;
                            E = END_OF_VALUE;
                            continue;
                        case '"': // string
                            T = STRING;
                            E = LITERAL;
                            V = "";
                            continue;
                        case 'f': // boolean
                        case 't':
                            T = BOOLEAN;
                            E = LITERAL;
                            V = String.valueOf(ch);
                            continue;
                        case 'n': // null
                            T = NULL;
                            E = LITERAL;
                            V = String.valueOf(ch);
                            continue;
                        case '-': // negative
                            T = INTEGER;
                            E = NUMBER;
                            V = String.valueOf(ch);
                            continue;
                        case '0': // number
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            T = INTEGER;
                            E = LITERAL;
                            V = String.valueOf(ch);
                            continue;
                        default:
                            throw new JsonSyntaxException("Unexpected literal '" + ch + "'", json);
                    }
                case END_OF_VALUE: // expecting end of value
                    switch(ch){
                        case ' ': // whitespace
                        case '\t':
                        case '\r':
                        case '\n':
                            continue;
                        case ',': // next in object
                        case '}': // end of object
                            E = START_OF_KEY; // prepare for next
                            switch(T){ // push value
                                case NULL:
                                    obj.set(K, null);
                                    break;
                                case BOOLEAN:
                                    obj.set(K, Boolean.parseBoolean(V));
                                    break;
                                case INTEGER:
                                    try{
                                        obj.set(K, Integer.parseInt(V));
                                    }catch(final NumberFormatException ignored){
                                        obj.set(K, Long.parseLong(V));
                                    }
                                    break;
                                case DOUBLE:
                                    try{
                                        obj.set(K, Double.parseDouble(V));
                                    }catch(final NumberFormatException ignored){
                                        obj.set(K, Long.parseLong(V));
                                    }
                                    break;
                                case STRING:
                                    if(K != null && V != null)
                                        obj.set(parseString(K), parseString(V));
                                    break;
                            }
                            K = null;
                            V = null;
                            T = UNKNOWN;
                            if(ch == '}') // end of object
                                return obj;
                    }
                    continue;
                case LITERAL: // expecting literal value
                    switch(T){
                        case NULL: // parse null
                            V += ch;
                            if(!"null".startsWith(V)) // looks like null
                                throw new JsonSyntaxException("Unexpected value '" + V + "', expected null", json);
                            else if("null".equals(V)) // is null
                                E = END_OF_VALUE;
                            continue;
                        case BOOLEAN: // parse boolean
                            V += ch;
                            if(!"false".startsWith(V) && !"true".startsWith(V)) // looks like a boolean
                                throw new JsonSyntaxException("Unexpected value '" + V + "', expected a boolean", json);
                            else if("false".equals(V) || "true".equals(V)) // is a boolean
                                E = END_OF_VALUE;
                            continue;
                        case INTEGER:
                            switch(ch){ // parse integer
                                case ' ': // whitespace
                                case '\t':
                                case '\r':
                                case '\n':
                                    E = END_OF_VALUE;
                                    continue;
                                case '.': // convert to decimal
                                    T = DOUBLE;
                                    E = NUMBER;
                                    V += ch;
                                    continue;
                                case '0': // number
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                    V += ch;
                                    continue;
                                case ',': // end of value
                                case '}':
                                    E = END_OF_VALUE;
                                    i--;
                                    continue;
                                default:
                                    throw new JsonSyntaxException("Unexpected token '" + ch + "', expected a number or ','", json);
                            }
                        case DOUBLE:
                            switch(ch){ // parse double
                                case ' ': // whitespace
                                case '\t':
                                case '\r':
                                case '\n':
                                    E = END_OF_VALUE;
                                    continue;
                                case '.': // extra decimal
                                    throw new JsonSyntaxException("Unexpected token '.'", json);
                                case '0': // number
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                    V += ch;
                                    continue;
                                case ',': // end of value
                                case '}':
                                    E = END_OF_VALUE;
                                    i--;
                                    continue;
                                default:
                                    throw new JsonSyntaxException("Unexpected token '" + ch + "', expected a number", json);
                            }
                        case STRING:
                            switch(ch){
                                case '\r': // illegal whitespace
                                case '\n':
                                   throw new JsonSyntaxException("Unexpected token '" + ch + "'", json);
                                case '\\': // escaped
                                    if(isEscaped)
                                        V += ch;
                                    isEscaped = !isEscaped;
                                    continue;
                                case '"': // quote
                                    if(!isEscaped) // end of value
                                        E = END_OF_VALUE;
                                    else{ // escaped quote
                                        V += ch;
                                        isEscaped = false;
                                    }
                                    continue;
                                default:
                                    if(!isEscaped){ // literal
                                        if(ch == 'u' && i > 0 && json.charAt(i - 1) == '\\') // unicode escape
                                            V += '\\'; // add escape slash
                                        V += ch;
                                    }else{ // escaped
                                        isEscaped = false;
                                        switch(ch){
                                            case 't': // tab
                                                V += '\t';
                                                continue;
                                            case 'r': // carriage return
                                                V += '\r';
                                                continue;
                                            case 'n': // new line
                                                V += '\n';
                                                continue;
                                            case 'u': // unicode
                                                V += "\\u";
                                                continue;
                                            case '/': // slash
                                                V += '/';
                                                continue;
                                            default: // unknown
                                                throw new JsonSyntaxException("Unknown escape character '\\" + ch + "'", json);
                                        }
                                    }
                            }
                    }
                    continue;
                case NUMBER: // expecting number value
                    switch(ch){
                        case '0': // number
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            E = LITERAL;
                            V += ch;
                            continue;
                        default:
                            throw new JsonSyntaxException("Unexpected token '" + ch + "', expected a number", json);
                    }
            }
        }
        throw new JsonSyntaxException("Missing closing bracket '}'", json);
    }

    // start index should include opening token
    private int findEndToken(final String json, final int start, final char open, final char close){
        boolean isString  = false;
        boolean isEscaped = false;

        int depth = 0;
        for(int i = start + 1; i < len; i++){
            final char ch = json.charAt(i);
            if(ch == '\\' || isEscaped) // escape
                isEscaped = !isEscaped;
            else if(ch == '"') // string
                isString = !isString;
            else if(!isString) // token
                if(ch == open) // open
                    depth++;
                else if(ch == close) // close
                    if(depth == 0) // matching depeth
                        return i;
                    else // exit depth
                        depth--;
        }
        throw new JsonSyntaxException("Missing closing bracket '" + close + "'", json);
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
            return value == null ? null : value instanceof String ? (String) value : value.toString();
        }

        public final Integer getInt(final String key){
            final Object value = map.get(key);
            if(value instanceof String)
                try{
                    return Integer.valueOf((String) value);
                }catch(final NumberFormatException ignored){
                    return null;
                }
            else if(value instanceof Number)
                return ((Number) value).intValue();
            else
                return null;
        }

        public final Double getDouble(final String key){
            final Object value = map.get(key);
            if(value instanceof String)
                try{
                    return Double.valueOf((String) value);
                }catch(final NumberFormatException ignored){
                    return null;
                }
            else if(value instanceof Number)
                return ((Number) value).doubleValue();
            else
                return null;
        }

        public final Float getFloat(final String key){
            final Object value = map.get(key);
            if(value instanceof String)
                try{
                    return Float.valueOf((String) value);
                }catch(final NumberFormatException ignored){
                    return null;
                }
            else if(value instanceof Number)
                return ((Number) value).floatValue();
            else
                return null;
        }

        public final Long getLong(final String key){
            final Object value = map.get(key);
            if(value instanceof String)
                try{
                    return Long.valueOf((String) value);
                }catch(final NumberFormatException ignored){
                    return null;
                }
            else if(value instanceof Number)
                return ((Number) value).longValue();
            else
                return null;
        }

        public final Boolean getBoolean(final String key){
            final Object value = map.get(key);
            if(value instanceof String)
                // manual equality check is required
                // parseBoolean and valueOf always fallsback to false on invalid inputs (which is the WRONG value)
                if(((String) value).equalsIgnoreCase("true"))
                    return true;
                else if(((String) value).equalsIgnoreCase("false"))
                    return false;
                else
                    return null;
            else if(value instanceof Boolean)
                return (Boolean) value;
            else
                return null;
        }

        public final JsonObject getJsonObject(final String key){
            final Object value = map.get(key);
            if(value instanceof JsonObject)
                return (JsonObject) value;
            else
                return new JsonObject(); // never return null, this prevents chained NPE
        }

        public final String[] getStringArray(final String key){
            final List<?> list = (List<?>) map.get(key);
            if(list == null) return null;
            final List<String> arr = new ArrayList<>();
            for(final Object o : list)
                arr.add(o == null ? null : o instanceof String ? (String) o : o.toString());
            return arr.toArray(new String[0]);
        }

        public final JsonObject[] getJsonArray(final String key){
            final List<?> list = (List<?>) map.get(key);
            if(list == null) return null;
            final List<JsonObject> arr = new ArrayList<>();
            for(final Object o : list)
                arr.add(o instanceof JsonObject ? (JsonObject) o : new JsonObject());
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
        public final String toString(){
            return "JsonObject{" +
                   "map=" + map +
                   '}';
        }

    }

}