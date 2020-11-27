package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimeRanking;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;

import java.util.List;

public abstract class AnimeRankingQuery extends FieldSearchQuery<AnimeRankingQuery,List<AnimeRanking>> {

    protected final AnimeRankingType rankingType;

    public AnimeRankingQuery(final MyAnimeListService service, final AnimeRankingType rankingType) {
        super(service);
        this.rankingType = rankingType;
    }

}
