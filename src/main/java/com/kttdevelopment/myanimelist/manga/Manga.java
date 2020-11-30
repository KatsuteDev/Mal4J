package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.anime.AnimePreview;
import com.kttdevelopment.myanimelist.manga.property.*;
import com.kttdevelopment.myanimelist.property.FullMediaItem;
import com.kttdevelopment.myanimelist.property.MediaItem;
import jdk.jfr.Experimental;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get</a> <br>
 * Represents an Manga's full details.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getManga(long)
 * @see com.kttdevelopment.myanimelist.MyAnimeList#getManga(long, String...)
 * @see MangaPreview
 * @see FullMediaItem
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class Manga extends MangaPreview implements FullMediaItem<MangaType,MangaPublishStatus,MangaListStatus,MangaRecommendation,MangaStatistics> {

    // API methods

    /**
     * Returns the Manga's statistics.
     *
     * @deprecated Does not exist in the API currently
     * @return Manga statistics
     *
     * @see MangaStatistics
     * @since 1.0.0
     */
    // API doesn't return this
    @Override @Deprecated
    public final MangaStatistics getStatistics() {
        return null;
    }

    /**
     * Returns the publishers of the Manga.
     *
     * @return publishers
     *
     * @see Publisher
     * @since 1.0.0
     */
    public abstract Publisher[] getSerialization();

    // additional methods

    @Override
    public final Manga getManga() {
        return this;
    }

}
