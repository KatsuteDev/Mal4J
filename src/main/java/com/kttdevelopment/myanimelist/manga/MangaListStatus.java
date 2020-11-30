package com.kttdevelopment.myanimelist.manga;


import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.property.Editable;
import com.kttdevelopment.myanimelist.property.ListStatus;
import com.kttdevelopment.myanimelist.query.AnimeListUpdate;
import com.kttdevelopment.myanimelist.query.MangaListUpdate;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put</a> <br>
 * Represents a user's Anime list status.
 *
 * @see Manga#getListStatus()
 * @see MyAnimeList#updateMangaListing(long)
 * @see ListStatus
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaListStatus implements ListStatus<MangaStatus> {

    // API methods

    /**
     * Returns the total amount of volumes read.
     *
     * @return total volumes read
     *
     * @since 1.0.0
     */
    public abstract int getVolumesRead();

    /**
     * Returns the total amount of chapters read.
     *
     * @return total chapters read
     *
     * @since 1.0.0
     */
    public abstract int getChaptersRead();

    /**
     * Returns if the user is rereading.
     *
     * @return rereading
     *
     * @since 1.0.0
     */
    public abstract boolean isRereading();

    /**
     * Returns the total times reread.
     *
     * @return times reread
     *
     * @since 1.0.0
     */
    public abstract int getTimesReread();

    /**
     * Returns the reread value.
     *
     * @return reread value
     *
     * @since 1.0.0
     */
    public abstract int getRereadValue();

    // additional methods

    /**
     * Creates a Manga list update object. Used to change the Manga list status.
     *
     * @return list updater
     *
     * @see MangaListUpdate
     * @since 1.0.0
     */
    public abstract MangaListUpdate edit();

}
