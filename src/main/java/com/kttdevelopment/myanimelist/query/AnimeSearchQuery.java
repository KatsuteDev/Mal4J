package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimePreview;

import java.util.List;

public abstract class AnimeSearchQuery extends FieldSearchQuery<AnimeSearchQuery,List<AnimePreview>>{

    protected String query;
    protected Boolean nsfw;

    public AnimeSearchQuery(final MyAnimeListService service) {
        super(service);
    }

    public final AnimeSearchQuery withQuery(final String query){
        this.query = query;
        return this;
    }

    public final AnimeSearchQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
