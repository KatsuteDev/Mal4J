package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimePreview;
import com.kttdevelopment.myanimelist.anime.property.AnimeSeasonSort;
import com.kttdevelopment.myanimelist.anime.property.time.Season;

import java.util.List;

public abstract class AnimeSeasonQuery extends FieldSearchQuery<AnimeSeasonQuery,List<AnimePreview>>{

    protected final int year;
    protected final Season season;
    protected AnimeSeasonSort sort;

    public AnimeSeasonQuery(final MyAnimeListService service, final int year, final Season season) {
        super(service);
        this.year = year;
        this.season = season;
    }

    public final AnimeSeasonQuery sortBy(final AnimeSeasonSort sort){
        this.sort = sort;
        return this;
    }

}
