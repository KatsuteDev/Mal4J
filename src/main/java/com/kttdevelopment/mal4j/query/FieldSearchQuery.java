/*
 * Copyright (C) 2021 Ktt Development
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

package com.kttdevelopment.mal4j.query;

import com.kttdevelopment.mal4j.MyAnimeList;

import java.util.*;

/**
 * Represents a field query. Returns all fields by default.
 *
 * @param <T> this
 * @param <R> completed request
 *
 * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_ANIME_FIELDS
 * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_MANGA_FIELDS
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings({"unchecked", "UnusedReturnValue"})
abstract class FieldSearchQuery<T extends FieldSearchQuery<T,R>,R> extends SearchQuery<T,R> {

    protected List<String> fields = null;

    FieldSearchQuery() { }

    /**
     * Adds a field to return.
     *
     * @param field a field or comma separated fields that should be returned
     * @return query
     *
     * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_ANIME_FIELDS
     * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_MANGA_FIELDS
     * @see #withFields(String...)
     * @see #withFields(List)
     * @see #withAllFields()
     * @see #withNoFields()
     * @since 1.0.0
     */
    public final T withField(final String field){
        if(fields == null)
            this.fields = new ArrayList<>();
        if(!this.fields.contains(field))
            this.fields.add(field);
        return (T) this;
    }

    /**
     * Adds a list of fields to return.
     *
     * @param fields a comma separated string or a string array of the fields that should be returned
     *
     * @return query
     *
     * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_ANIME_FIELDS
     * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_MANGA_FIELDS
     * @see #withField(String)
     * @see #withFields(List)
     * @see #withAllFields()
     * @see #withNoFields()
     * @since 1.0.0
     */
    public final T withFields(final String... fields){
        if(fields == null)
            this.fields = new ArrayList<>();
        else{
            if(this.fields == null)
                this.fields = new ArrayList<>();
            for(String field : fields)
                withField(field);
        }
        return (T) this;
    }

    /**
     * Adds a list of fields to return.
     *
     * @param fields a list of the fields that should be returned
     *
     * @return query
     *
     * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_ANIME_FIELDS
     * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_MANGA_FIELDS
     * @see #withField(String)
     * @see #withFields(String...)
     * @see #withAllFields()
     * @see #withNoFields()
     * @since 1.0.0
     */
    public final T withFields(final List<String> fields){
        if(fields == null)
            this.fields = new ArrayList<>();
        else{
            if(this.fields == null)
                this.fields = new ArrayList<>();
            for(String field : fields)
                withField(field);
        }
        return (T) this;
    }

    /**
     * Makes query return all possible fields.
     *
     * @return query
     *
     * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_ANIME_FIELDS
     * @see com.kttdevelopment.mal4j.MyAnimeList#ALL_MANGA_FIELDS
     * @see #withField(String)
     * @see #withFields(String...)
     * @see #withFields(List)
     * @see #withNoFields()
     * @since 1.0.0
     */
    public final T withAllFields(){
        this.fields = null;
        return (T) this;
    }

    /**
     * Makes query return only default fields.
     *
     * @return query
     *
     * @see com.kttdevelopment.mal4j.MyAnimeList#NO_FIELDS
     * @see #withField(String)
     * @see #withFields(String...)
     * @see #withFields(List)
     * @see #withAllFields()
     * @since 1.0.0
     */
    public final T withNoFields(){
        this.fields = Arrays.asList(MyAnimeList.NO_FIELDS);
        return (T) this;
    }

}
