/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
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

import java.util.HashSet;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Used for logging operating.
 */
public abstract class Logging {

    // dedicated logger

    private static final Logger logger = Logger.getLogger("dev.katsute.mal4j");

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

    public static Logger getLogger(){
        if(!new Exception().getStackTrace()[1].toString().startsWith("dev.katsute.mal4j."))
            throw new SecurityException("Logging not allowed for this class");
        else
            return logger;
    }

    // debug

    private static boolean debug = false;

    static void setDebug(final boolean debug){
        Logging.debug = debug;
    }

    // debug logging

    private static final HashSet<String> secrets = new HashSet<>();

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