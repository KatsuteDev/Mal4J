package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
abstract class FieldSearchQuery<T extends FieldSearchQuery,B> extends SearchQuery<T,B> {

    protected List<String> fields;

    FieldSearchQuery(MyAnimeListService service) {
        super(service);
    }

    public final T withField(final String field){
        if(this.fields == null)
            this.fields = new ArrayList<>();
        if(!this.fields.contains(field))
            this.fields.add(field);
        return (T) this;
    }

    public final T withFields(final String... fields){
        if(this.fields == null)
            this.fields = new ArrayList<>();
        for (String field : fields)
            if(!this.fields.contains(field))
                this.fields.add(field);
        return (T) this;
    }

}
