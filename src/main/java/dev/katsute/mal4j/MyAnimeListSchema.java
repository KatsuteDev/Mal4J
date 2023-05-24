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

import dev.katsute.mal4j.Json.JsonObject;
import dev.katsute.mal4j.anime.property.time.Time;
import dev.katsute.mal4j.property.NullableDate;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema {

    protected static <R> R[] adaptList(final JsonObject[] list, final Function<JsonObject,R> adapter, final Class<R> Class){
        if(list == null) return null;

        final int len = list.length;
        @SuppressWarnings("unchecked")
        final R[] array = (R[]) Array.newInstance(Class, len);

        for(int i = 0; i < len; i++)
            array[i] = adapter.apply(list[i]);
        return array;
    }

    //

    private static final String YMD = "yyyy-MM-dd";
    private static final String YM  = "yyyy-MM";
    private static final String Y   = "yyyy";

    protected static Long parseDate(final String date){
        if(date == null) return null;

        try{
            final int len = date.length();
            return new SimpleDateFormat(len == 10 ? YMD : len == 7 ? YM : Y).parse(date).getTime();
        }catch(final ParseException e){
            return null;
        }
    }

    protected static NullableDate parseNullableDate(final String date){
        if(date == null) return null;

        final int len = date.length();
        final String[] parts = date.split("-");

        final long t;
        final Integer y, m, d;
        try{
            t = new SimpleDateFormat(len == 10 ? YMD : len == 7 ? YM : Y).parse(date).getTime();
            y = parts.length >= 1 ? Integer.valueOf(parts[0]) : null;
            m = parts.length >= 2 ? Integer.valueOf(parts[1]) : null;
            d = parts.length >= 3 ? Integer.valueOf(parts[2]) : null;
        }catch(final NumberFormatException | ParseException ignored){
            return null;
        }

        return new NullableDate(){

            private final Long time = t;

            private final Integer year = y;
            private final Integer month = m;
            private final Integer day = d;

            @Override
            public final Integer getYear(){
                return year;
            }

            @Override
            public final Integer getMonth(){
                return month;
            }

            @Override
            public final Integer getDay(){
                return day;
            }

            @Override
            public final Long getMillis(){
                return time;
            }

            @Override
            public final Date getDate(){
                return new Date(time);
            }

            //

            @Override
            public final String toString(){
                return "NullableDate{" +
                       "year=" + year +
                       ", month=" + month +
                       ", day=" + day +
                       '}';
            }

        };
    }

    static String asYMD(final Long millis){
        return millis == null ? null : new SimpleDateFormat(MyAnimeListSchema.YMD).format(new Date(millis));
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static final String ISO8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";

    protected static Long parseISO8601(final String timestamp){
        if(timestamp == null) return null;

        try{
            return new SimpleDateFormat(ISO8601).parse(timestamp).getTime();
        }catch(final ParseException ignored){
            return null;
        }
    }

    static String asISO8601(final Long millis){
        return millis == null ? null : new SimpleDateFormat(MyAnimeListSchema.ISO8601).format(new Date(millis));
    }

    //

    protected static Time asTime(final String time){
        if(time == null || !time.contains(":")) return null;
        @SuppressWarnings("SpellCheckingInspection")
        final String[] hhmm = time.split(":");
        final int h         = Integer.parseInt(hhmm[0]);
        final int m         = Integer.parseInt(hhmm[1]);

        return new Time(){

            private final Integer hour      = h;
            private final Integer hour12    = h > 12 ? h - 12 : h == 0 ? 12 : h;
            private final Boolean am        = hour <= 12;
            private final Integer minute    = m;

            @Override
            public final Integer getHour(){
                return hour;
            }

            @Override
            public final Integer get12Hour(){
                return hour12;
            }

            @Override
            public final Boolean isAM(){
                return am;
            }

            @Override
            public final Boolean isPM(){
                return !am;
            }

            @Override
            public final Integer getMinute(){
                return minute;
            }

            //

            @Override
            public final String toString(){
                return "Time{" +
                       "hour=" + hour +
                       ", hour12=" + hour12 +
                       ", am=" + am +
                       ", minute=" + minute +
                       '}';
            }

        };
    }

}