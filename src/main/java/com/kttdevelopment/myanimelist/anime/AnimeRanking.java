package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.property.Ranking;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_ranking_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_ranking_get</a> <br>
 * Represents an Anime's ranking.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getAnimeRanking(AnimeRankingType)
 * @see AnimePreview
 * @see Anime
 * @see Ranking
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeRanking implements AnimePreviewRetrievable, AnimeRetrievable, Ranking {

}
