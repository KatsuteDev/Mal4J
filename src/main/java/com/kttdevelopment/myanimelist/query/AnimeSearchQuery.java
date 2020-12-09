package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimePreview;

import java.util.List;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_get</a> <br>
 * Represents a Anime search query.
 *
 * @see MyAnimeList#getAnime()
 * @see FieldSearchQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeSearchQuery extends FieldSearchQuery<AnimeSearchQuery,AnimePreview> {

    protected String query;
    protected Boolean nsfw;

    /**
     * Creates an Anime search query. Applications do not use this constructor.
     *
     * @param service MyAnimeList
     *
     * @see MyAnimeList#getAnime()
     * @since 1.0.0
     */
    public AnimeSearchQuery(final MyAnimeListService service) {
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
    public final AnimeSearchQuery withQuery(final String query){
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
    public final AnimeSearchQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
