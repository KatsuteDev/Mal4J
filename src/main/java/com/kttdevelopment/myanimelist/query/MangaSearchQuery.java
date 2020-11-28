package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.manga.MangaPreview;

import java.util.List;

public abstract class MangaSearchQuery extends FieldSearchQuery<MangaSearchQuery,List<MangaPreview>>{

    protected String query;
    protected Boolean nsfw;

    public MangaSearchQuery(final MyAnimeListService service) {
        super(service);
    }

    public final MangaSearchQuery withQuery(final String query){
        this.query = query;
        return this;
    }

    public final MangaSearchQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
