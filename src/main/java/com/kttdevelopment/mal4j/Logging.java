package com.kttdevelopment.mal4j;

import java.util.HashSet;
import java.util.logging.*;

/**
 * Used for logging operating.
 */
abstract class Logging {

    // dedicated logger

    @SuppressWarnings("SpellCheckingInspection")
    private static final Logger logger = Logger.getLogger("com.kttdevelopment.mal4j");

    static{
        logger.setUseParentHandlers(false);
        logger.addHandler(new ConsoleHandler(){{
            setFormatter(new Formatter() { // custom formatter to remove timestamp
                @Override
                public String format(final LogRecord record){
                    return record.getLevel() + ": " + record.getMessage() + "\r\n";
                }
            });
        }});
    }

    static Logger getLogger(){
        return logger;
    }

    // debug

    private static boolean debug = false;

    static void setDebug(final boolean debug){
        Logging.debug = debug;
    }

    // debug logging

    private static final transient HashSet<String> secrets = new HashSet<>();

    static void addMask(final String secret){
        if(secret != null)
            secrets.add(secret);
    }

    static void debug(){
        System.out.println();
    }
    
    static void debug(final String message){
        if(message == null) return;
        
        if(debug){
            String buffer = message;
            for(final String secret : secrets)
                buffer = buffer.replace(secret, "***");

            System.out.println(buffer);
        }
    }

}
