package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimePreview;
import com.kttdevelopment.myanimelist.anime.property.AnimeSeasonSort;
import com.kttdevelopment.myanimelist.anime.property.time.Season;

import java.util.List;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/anime_season_year_season_get">https://myanimelist.net/apiconfig/references/api/v2#operation/anime_season_year_season_get</a> <br>
 * Represents an Anime season query.
 *
 * @see MyAnimeList#getAnimeSeason(int, Season)
 * @see FieldSearchQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class AnimeSeasonQuery extends FieldSearchQuery<AnimeSeasonQuery,AnimePreview> {

    protected final int year;
    protected final Season season;
    protected AnimeSeasonSort sort;
    protected Boolean nsfw;

    /**
     * Creates an Anime season query. Applications do not use this constructor.
     *
     * @param service MyAnimeList
     * @param year year
     * @param season season
     *
     * @see MyAnimeList#getAnimeSeason(int, Season)
     * @see Season
     * @since 1.0.0
     */
    public AnimeSeasonQuery(final MyAnimeListService service, final int year, final Season season) {
        super(service);
        this.year = year;
        this.season = season;
    }

    /**
     * Sets the sorting option.
     *
     * @param sort sort
     * @return season query
     *
     * @see AnimeSeasonSort
     * @since 1.0.0
     */
    public final AnimeSeasonQuery sortBy(final AnimeSeasonSort sort){
        this.sort = sort;
        return this;
    }

    /**
     * Sets if the query will return NSFW results.
     *
     * @deprecated The API does not support this option currently
     * @param nsfw nsfw
     * @return season query
     *
     * @since 1.0.0
     */
    @Deprecated
    public final AnimeSeasonQuery includeNSFW(final boolean nsfw){
        this.nsfw = nsfw;
        return this;
    }

}
