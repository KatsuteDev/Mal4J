/*
 * Copyright (C) 2021 Katsute <https://github.com/Katsute>
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
            setFormatter(new Formatter(){
                @Override
                public final String format(final LogRecord record){
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

    static void addMask(final ThrowingSupplier<String> runnable){
        try{ addMask(runnable.get());
        }catch(final Throwable ignored){}
    }

    static void debug(){
        if(debug)
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

    interface ThrowingSupplier<R> {

        @SuppressWarnings("RedundantThrows")
        R get() throws Throwable;

    }

}
