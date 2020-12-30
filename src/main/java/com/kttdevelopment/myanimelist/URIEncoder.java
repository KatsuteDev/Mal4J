package com.kttdevelopment.myanimelist;

import java.util.function.Function;
import java.util.regex.MatchResult;

class URIEncoder implements Function<MatchResult, String> {

    @Override
    public final String apply(final MatchResult matchResult){
        final char ch = matchResult.group().charAt(0);
            switch(ch){
                case '{':
                    return "%7B";
                case '}':
                    return "%7D";
                case '|':
                    return "%7C";
                case '\\':
                    return "%5C";
                case '^':
                    return "%5E";
                case '[':
                    return "%5B";
                case ']':
                    return "%5D";
                case '`':
                    return "%60";
                default:
                    return matchResult.group(0);
            }
    }

}
