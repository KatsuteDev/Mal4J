package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.Anime;
import com.kttdevelopment.myanimelist.anime.AnimePreview;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_suggestions_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_suggestions_get</a> <br>
 * Represents an Anime suggestion query.
 *
 * @see MyAnimeList#getAnimeSuggestions()
 * @see FieldSearchQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeSuggestionQuery extends FieldSearchQuery<AnimeSuggestionQuery,Anime> {

    protected String query;
    protected Boolean nsfw;

    /**
     * Creates an Anime suggestion query. Applications do not use this constructor.
     *
     * @param service MyAnimeList
     *
     * @see MyAnimeList#getAnimeSuggestions()
     * @since 1.0.0
     */
    public AnimeSuggestionQuery(final MyAnimeListService service) {
        super(service);
    }

    /**
     * Sets the search query.
     *
     * @param query query
     * @return suggestion query
     *
     * @since 1.0.0
     */
    public final AnimeSuggestionQuery withQuery(final String query){
        this.query = query;
        return this;
    }

    /**
     * Sets if the query will return NSFW results.
     *
     * @deprecated The API does not support this option currently
     * @param nsfw nsfw
     * @return suggestion query
     *
     * @since 1.0.0
     */
    @Deprecated
    public final AnimeSuggestionQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
