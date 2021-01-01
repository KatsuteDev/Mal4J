package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.anime.AnimeListStatus;
import com.kttdevelopment.myanimelist.anime.property.*;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_animelist_get</a> <br>
 *
 * Represents a user Anime list query.
 *
 * @see MyAnimeList#getUserAnimeListing()
 * @see MyAnimeList#getUserAnimeListing(String)
 * @see FieldSearchQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class UserAnimeListQuery extends FieldSearchQuery<UserAnimeListQuery,AnimeListStatus> {

    protected final String username;
    protected AnimeSort sort;
    protected AnimeStatus status;

    /**
     * Creates a user Anime search query. Applications do not use this constructor.
     *
     * @param username username
     *
     * @see MyAnimeList#getUserAnimeListing()
     * @see MyAnimeList#getUserAnimeListing(String)
     * @since 1.0.0
     */
    public UserAnimeListQuery(final String username) {
        this.username = username;
    }

    /**
     * Sets the sorting option.
     *
     * @param sort sort
     * @return list query
     *
     * @see AnimeSort
     * @since 1.0.0
     */
    public final UserAnimeListQuery sortBy(final AnimeSort sort){
        this.sort = sort;
        return this;
    }

    /**
     * Sets the status filter.
     *
     * @param status status
     * @return list query
     *
     * @since 1.0.0
     */
    public final UserAnimeListQuery withStatus(final AnimeStatus status){
        this.status = status;
        return this;
    }

}
