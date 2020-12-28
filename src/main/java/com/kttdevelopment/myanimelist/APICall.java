package com.kttdevelopment.myanimelist;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.kttdevelopment.myanimelist.APIReader.*;

class APICall implements InvocationHandler {

    private final String baseURL;

    public APICall(final String baseURL){
        this.baseURL = baseURL;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable{
        // fixme
        // if(method.getDeclaringClass() != service)
        //     return method.invoke(this, args);

        final Class<?> rtn = method.getReturnType();
        final Object[] params = args != null ? args : new Object[0];

        final ENDPOINT endpoint;
        if((endpoint = method.getAnnotation(ENDPOINT.class)) != null){
            final String rmethod = endpoint.method();



            final Map<String,String> pathParams = new HashMap<>();
            for(final Parameter parameter : method.getParameters()){
                for(final Path apath : parameter.getAnnotationsByType(Path.class)){
                    pathParams.put(apath.value(), null); // fixme param value
                }
            }

            final String URL = baseURL + constructPath(endpoint.value(), null); // todo: query ?&

            // todo HTTP request

            return Json.parse(null);
        }else{
            return method.invoke(this, args);
        }
    }

    // \{(.*?)\}
    private static final Pattern path = Pattern.compile("\\{(.*?)}");

    private static String constructPath(final String path, final Map<String,String> pathValues){
        return APICall.path.matcher(path).replaceAll(matchResult -> pathValues.getOrDefault(matchResult.group(), ""));
    }

    private static String constructQuery(final Map<String,Object> values){
        final StringBuilder OUT = new StringBuilder("");

        values.forEach((k, v) -> {
            // todo, skip null
        });

        return (OUT.length() > 0 ? "?" : "") + OUT.toString();
    }

}
