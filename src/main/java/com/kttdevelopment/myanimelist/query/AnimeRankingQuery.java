package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimeRanking;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;

import java.util.List;

public abstract class AnimeRankingQuery extends FieldSearchQuery<AnimeRankingQuery,List<AnimeRanking>> {

    protected final AnimeRankingType rankingType;
    protected Boolean nsfw;

    public AnimeRankingQuery(final MyAnimeListService service, final AnimeRankingType rankingType) {
        super(service);
        this.rankingType = rankingType;
    }

    public final AnimeRankingQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
