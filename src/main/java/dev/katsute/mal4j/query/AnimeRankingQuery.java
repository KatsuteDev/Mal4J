/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package dev.katsute.mal4j.query;

import dev.katsute.mal4j.anime.AnimeRanking;
import dev.katsute.mal4j.anime.property.AnimeRankingType;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_ranking_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_ranking_get</a> <br>
 * Represents an Anime ranking query.
 *
 * @see dev.katsute.mal4j.MyAnimeList#getAnimeRanking(AnimeRankingType)
 * @see FieldQuery
 * @since 1.0.0
 * @version 2.9.0
 * @author Katsute
 */
public abstract class AnimeRankingQuery extends FieldQuery<AnimeRankingQuery,AnimeRanking> implements NSFW<AnimeRankingQuery> {

    protected final String rankingType;
    protected Boolean nsfw;

    /**
     * Creates an Anime ranking query.
     * <br>
     * Do not use this constructor, use {@link dev.katsute.mal4j.MyAnimeList#getAnimeRanking(AnimeRankingType)} instead.
     *
     * @param rankingType ranking type
     *
     * @see dev.katsute.mal4j.MyAnimeList#getAnimeRanking(AnimeRankingType)
     * @see AnimeRankingType
     * @since 1.0.0
     */
    public AnimeRankingQuery(final String rankingType) {
        this.rankingType = rankingType;
    }

    @Override
    public final AnimeRankingQuery includeNSFW(){
        return includeNSFW(true);
    }

    @Override
    public final AnimeRankingQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
