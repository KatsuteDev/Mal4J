package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.manga.MangaRanking;
import com.kttdevelopment.myanimelist.manga.property.MangaRankingType;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_ranking_get">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_ranking_get</a> <br>
 * Represents a Manga ranking query.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getMangaRanking(MangaRankingType)
 * @see FieldSearchQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaRankingQuery extends FieldSearchQuery<MangaRankingQuery,MangaRanking> {

    protected final MangaRankingType rankingType;
    protected Boolean nsfw;

    /**
     * Creates a Manga ranking query. Applications do not use this constructor.
     *
     * @param rankingType ranking type
     *
     * @see com.kttdevelopment.myanimelist.MyAnimeList#getMangaRanking(MangaRankingType)
     * @see MangaRankingType
     * @since 1.0.0
     */
    public MangaRankingQuery(final MangaRankingType rankingType) {
        this.rankingType = rankingType;
    }

    /**
     * Sets if the query will return NSFW results.
     *
     * @deprecated The API does not support this option currently
     * @param nsfw nsfw
     * @return ranking query
     *
     * @since 1.0.0
     */
    @Deprecated
    public final MangaRankingQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
