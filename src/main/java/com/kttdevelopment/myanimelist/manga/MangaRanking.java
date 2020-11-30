package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.AnimePreview;
import com.kttdevelopment.myanimelist.anime.property.AnimeRankingType;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.Ranking;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_ranking_get">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_ranking_get</a> <br>
 * Represents an Anime's ranking.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getMangaRanking(MangaRankingType)
 * @see MangaPreview
 * @see Manga
 * @see Ranking
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaRanking implements MangaPreviewRetrievable, MangaRetrievable, Ranking {

}
