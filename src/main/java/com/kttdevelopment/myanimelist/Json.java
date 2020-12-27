package com.kttdevelopment.myanimelist;

import java.util.HashMap;
import java.util.Map;
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

    // merge below into a single regex and then check content of 2nd capture group

    private static final Pattern number     = Pattern.compile("^\"(.+)\": ?(\\d+\\.?\\d*),?$"); // if contains '.' and doesn't start or end with "
    private static final Pattern string     = Pattern.compile("^\"(.+)\": ?\"(.+)\",?$"); // if starts and ends with ""
    private static final Pattern map_or_arr = Pattern.compile("^\"(.+)\": ?([\\[{])$"); // if equal to exactly [ or {

    static Map<String,?> parse(final String json){
        // use #reset on matcher
        final Map<String,? super Object> response = new HashMap<>();
        json.lines().forEach(s -> {
            final String ln = s.trim();

        });

        return null;
    }

}
