package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimePreview;

import java.util.List;

public abstract class AnimeSuggestionQuery extends FieldSearchQuery<AnimeSuggestionQuery,List<AnimePreview>> {

    protected String query;
    protected Boolean nsfw;

    public AnimeSuggestionQuery(final MyAnimeListService service) {
        super(service);
    }

    public final AnimeSuggestionQuery withQuery(final String query){
        this.query = query;
        return this;
    }

    public final AnimeSuggestionQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
