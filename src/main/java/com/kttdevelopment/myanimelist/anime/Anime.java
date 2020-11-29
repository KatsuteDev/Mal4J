package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.*;
import com.kttdevelopment.myanimelist.property.FullMediaItem;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_anime_id_get</a> <br>
 * Represents an Anime's full details.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getAnime(long)
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getAnime(long, String...)
 * @see AnimePreview
 * @see FullMediaItem
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Anime extends AnimePreview implements FullMediaItem<AnimeType,AnimeAirStatus,AnimeListStatus,AnimeRecommendation,AnimeStatistics> {

    // additional methods

    @Override
    public final Anime getAnime() {
        return this;
    }

}
