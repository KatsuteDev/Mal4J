package com.kttdevelopment.myanimelist.manga;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.property.time.Season;
import com.kttdevelopment.myanimelist.manga.property.Author;
import com.kttdevelopment.myanimelist.manga.property.MangaPublishStatus;
import com.kttdevelopment.myanimelist.manga.property.MangaRetrievable;
import com.kttdevelopment.myanimelist.manga.property.MangaType;
import com.kttdevelopment.myanimelist.property.MediaItem;


/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get</a> <br>
 * Represents a Manga's full details.
 *
 * @see MyAnimeList#getManga()
 * @see Manga
 * @see MediaItem
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaPreview implements MangaRetrievable,MediaItem<MangaType,MangaPublishStatus,MangaListStatus> {

    /**
     * Returns the total amount of volumes.
     *
     * @return total volumes
     *
     * @see #getChapters()
     * @since 1.0.0
     */
    public abstract int getVolumes();

    /**
     * Returns the total amount of chapters.
     *
     * @return total chapters
     *
     * @see #getVolumes()
     * @since 1.0.0
     */
    public abstract int getChapters();

    /**
     * Returns the Manga's authors.
     *
     * @return Manga authors
     *
     * @see Author
     * @since 1.0.0
     */
    public abstract Author[] getAuthors();

}
