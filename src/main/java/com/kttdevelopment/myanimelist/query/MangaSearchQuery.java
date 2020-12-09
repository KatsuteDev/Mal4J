package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.manga.MangaPreview;

import java.util.List;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get">https://myanimelist.net/apiconfig/references/api/v2#operation/manga_get</a> <br>
 * Represents a Manga search query.
 *
 * @see MyAnimeList#getManga()
 * @see FieldSearchQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class MangaSearchQuery extends FieldSearchQuery<MangaSearchQuery,MangaPreview> {

    protected String query;
    protected Boolean nsfw;

    /**
     * Creates a Manga search query. Applications do not use this constructor.
     *
     * @param service MyAnimeList
     *
     * @see MyAnimeList#getManga()
     * @since 1.0.0
     */
    public MangaSearchQuery(final MyAnimeListService service) {
        super(service);
    }

    /**
     * Sets the search query.
     *
     * @param query query
     * @return search query
     *
     * @since 1.0.0
     */
    public final MangaSearchQuery withQuery(final String query){
        this.query = query;
        return this;
    }

    /**
     * Sets if the query will return NSFW results.
     *
     * @param nsfw nsfw
     * @return search query
     *
     * @since 1.0.0
     */
    public final MangaSearchQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
