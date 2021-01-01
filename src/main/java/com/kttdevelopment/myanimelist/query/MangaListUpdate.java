package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.manga.MangaListStatus;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;
import com.kttdevelopment.myanimelist.query.property.RereadValue;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_manga_id_my_list_status_put</a> <br>
 * Represents a Manga list update.
 *
 * @see com.kttdevelopment.myanimelist.MyAnimeList#updateMangaListing(long)
 * @see ListUpdate
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaListUpdate extends ListUpdate<MangaListUpdate,MangaListStatus,MangaStatus> {

    protected Boolean rereading;
    protected Integer volumesRead, chaptersRead, timesReread;
    protected RereadValue rereadValue;

    /**
     * Creates a Manga list update. Applications do not use this constructor.
     *
     * @param id Manga id
     *
     * @see com.kttdevelopment.myanimelist.MyAnimeList#updateMangaListing(long)
     * @since 1.0.0
     */
    public MangaListUpdate(final long id){
        super( id);
    }

    /**
     * Sets the rereading status.
     *
     * @param rereading rereading
     * @return list update
     *
     * @since 1.0.0
     */
    public final MangaListUpdate rereading(final boolean rereading){
        this.rereading = rereading;
        return this;
    }

    /**
     * Sets the volumes read.
     *
     * @param volumesRead volumes read
     * @return list update
     *
     * @since 1.0.0
     */
    public final MangaListUpdate volumesRead(final int volumesRead){
        this.volumesRead = volumesRead;
        return this;
    }

    /**
     * Sets the chapters read.
     *
     * @param chaptersRead chapters read
     * @return list update
     *
     * @since 1.0.0
     */
    public final MangaListUpdate chaptersRead(final int chaptersRead){
        this.chaptersRead = chaptersRead;
        return this;
    }

    /**
     * Sets the times reread.
     *
     * @param timesReread times reread
     * @return list update
     *
     * @since 1.0.0
     */
    public final MangaListUpdate timesReread(final int timesReread){
        this.timesReread = timesReread;
        return this;
    }

    /**
     * Sets the reread value.
     *
     * @param rereadValue reread value
     * @return list update
     *
     * @see RereadValue
     * @since 1.0.0
     */
    public final MangaListUpdate rereadValue(final RereadValue rereadValue){
        this.rereadValue = rereadValue;
        return this;
    }

}
