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

package dev.katsute.mal4j.query;

import dev.katsute.mal4j.Fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a field query. Returns all fields by default.
 *
 * @param <T> this
 * @param <R> response
 *
 * @see dev.katsute.mal4j.Fields#anime
 * @see dev.katsute.mal4j.Fields#manga
 * @since 1.0.0
 * @version 2.2.0
 * @author Katsute
 */
@SuppressWarnings({"unchecked", "UnusedReturnValue"})
public abstract class FieldQuery<T extends FieldQuery<T,R>,R> extends LimitOffsetQuery<T,R> {

    protected List<String> fields = null;

    FieldQuery(){ }

    /**
     * Adds a field to return.
     *
     * @param field a field or comma separated fields that should be returned
     * @return query
     *
     * @see dev.katsute.mal4j.Fields#anime
     * @see dev.katsute.mal4j.Fields#manga
     * @see #withFields(String...)
     * @see #withFields(List)
     * @see #withAllFields()
     * @see #invertFields()
     * @see #invertFields(boolean)
     * @see #withNoFields()
     * @since 1.0.0
     */
    public final T withField(final String field){
        if(field == null) return (T) this;

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
     * @see dev.katsute.mal4j.Fields#anime
     * @see dev.katsute.mal4j.Fields#manga
     * @see #withField(String)
     * @see #withFields(List)
     * @see #withAllFields()
     * @see #invertFields()
     * @see #invertFields(boolean)
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
     * @see dev.katsute.mal4j.Fields#anime
     * @see dev.katsute.mal4j.Fields#manga
     * @see #withField(String)
     * @see #withFields(String...)
     * @see #withAllFields()
     * @see #invertFields()
     * @see #invertFields(boolean)
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
     * @see dev.katsute.mal4j.Fields#anime
     * @see dev.katsute.mal4j.Fields#manga
     * @see #withField(String)
     * @see #withFields(String...)
     * @see #withFields(List)
     * @see #invertFields()
     * @see #invertFields(boolean)
     * @see #withNoFields()
     * @since 1.0.0
     */
    public final T withAllFields(){
        this.fields = null;
        return (T) this;
    }

    /**
     * Makes the query return all except the fields provided.
     *
     * @return query
     *
     * @see #withField(String)
     * @see #withFields(String...)
     * @see #withFields(List)
     * @see #withAllFields()
     * @see #invertFields(boolean)
     * @see #withNoFields()
     * @since 2.2.0
     */
    public final T invertFields(){
        return withField(Fields.INVERTED);
    }

    /**
     * Makes the query return all except the fields provided.
     *
     * @param inverted whether the fields should be inverted or not
     * @return query
     *
     * @see #withField(String)
     * @see #withFields(String...)
     * @see #withFields(List)
     * @see #withAllFields()
     * @see #invertFields()
     * @see #withNoFields()
     * @since 2.2.0
     */
    public final T invertFields(final boolean inverted){
        if(inverted)
            return withField(Fields.INVERTED);
        else if(fields != null){
            fields.remove(Fields.INVERTED);
            if(fields.isEmpty())
                fields = null;
        }
        return (T) this;
    }

    /**
     * Makes query return only default fields.
     *
     * @return query
     *
     * @see dev.katsute.mal4j.Fields#NO_FIELDS
     * @see #withField(String)
     * @see #withFields(String...)
     * @see #withFields(List)
     * @see #withAllFields()
     * @see #invertFields()
     * @see #invertFields(boolean)
     * @since 1.0.0
     */
    public final T withNoFields(){
        this.fields = Arrays.asList(Fields.NO_FIELDS);
        return (T) this;
    }

}