package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.manga.MangaRanking;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;

import java.util.List;

public abstract class MangaRankingQuery extends FieldSearchQuery<MangaRankingQuery,List<MangaRanking>> {

    protected final MangaRankingType rankingType;
    protected Boolean nsfw;

    public MangaRankingQuery(final MyAnimeListService service, final MangaRankingType rankingType) {
        super(service);
        this.rankingType = rankingType;
    }

    public final MangaRankingQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
