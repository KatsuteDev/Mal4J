package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a field query.
 *
 * @param <T> this
 * @param <R> completed request
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings({"unchecked"})
abstract class FieldSearchQuery<T extends FieldSearchQuery<T,R>,R> extends SearchQuery<T,R> {

    protected List<String> fields;

    FieldSearchQuery(MyAnimeListService service) {
        super(service);
    }

    /**
     * Adds a field to return.
     *
     * @param field field
     * @return query
     *
     * @see #withFields(String...)
     * @since 1.0.0
     */
    public final T withField(final String field){
        if(this.fields == null)
            this.fields = new ArrayList<>();
        if(!this.fields.contains(field))
            this.fields.add(field);
        return (T) this;
    }

    /**
     * Adds a list of fields to return.
     *
     * @param fields fields
     *
     * @return query
     *
     * @see #withField(String)
     * @since 1.0.0
     */
    public final T withFields(final String... fields){
        if(this.fields == null)
            this.fields = new ArrayList<>();
        for (String field : fields)
            if(!this.fields.contains(field))
                this.fields.add(field);
        return (T) this;
    }

}
