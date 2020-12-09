package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.manga.MangaListStatus;
import com.kttdevelopment.myanimelist.manga.property.MangaSort;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;

/**
 * <b>Documentation:</b> <a href="https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_mangalist_get">https://myanimelist.net/apiconfig/references/api/v2#operation/users_user_id_mangalist_get</a> <br>
 *
 * Represents a user Manga list query.
 *
 * @see MyAnimeList#getUserMangaListing()
 * @see MyAnimeList#getUserMangaListing(String)
 * @see FieldSearchQuery
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public abstract class UserMangaListQuery extends FieldSearchQuery<UserMangaListQuery,MangaListStatus> {

    protected final String username;
    protected MangaSort sort;
    protected MangaStatus status;

    /**
     * Creates a user Manga search query. Applications do not use this constructor.
     *
     * @param service MyAnimeList
     * @param username username
     *
     * @see MyAnimeList#getUserMangaListing()
     * @see MyAnimeList#getUserMangaListing(String)
     * @since 1.0.0
     */
    public UserMangaListQuery(final MyAnimeListService service, final String username) {
        super(service);
        this.username = username;
    }

    /**
     * Sets the sorting option.
     *
     * @param sort sort
     * @return list query
     *
     * @see MangaSort
     * @since 1.0.0
     */
    public final UserMangaListQuery sortBy(final MangaSort sort){
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
    public final UserMangaListQuery withStatus(final MangaStatus status){
        this.status = status;
        return this;
    }

}
