package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;

@SuppressWarnings({"unchecked", "rawtypes"})
abstract class SearchQuery<T extends SearchQuery,B> {

    protected final MyAnimeListService service;

    protected int          limit;
    protected int          offset;

    SearchQuery(final MyAnimeListService service) {
        this.service = service;
    }

    public final T withLimit(final int limit){
        this.limit = limit;
        return (T) this;
    }

    public final T withOffset(final int offset){
        this.offset = offset;
        return (T) this;
    }

    public abstract B search();

}
