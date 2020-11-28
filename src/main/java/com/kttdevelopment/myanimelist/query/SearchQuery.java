package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;

@SuppressWarnings({"unchecked", "rawtypes"})
abstract class SearchQuery<T extends SearchQuery,B> {

    protected final MyAnimeListService service;

    // offset is essentially the min and the limit is the max
    protected Integer limit;
    protected Integer offset;

    SearchQuery(final MyAnimeListService service) {
        this.service = service;
    }

    public final T withLimit(final int limit){
        // this.limit = limit + (offset != null ? offset : 0); // limit is the max (not including offset)
        this.limit = limit;
        return (T) this;
    }

    public final T withOffset(final int offset){
        // if(limit != null) // limit is the max (not including offset)
        //     limit = limit - (this.offset != null ? this.offset : 0) + offset;
        this.offset = offset;
        return (T) this;
    }

    public abstract B search();

}
