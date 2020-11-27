package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.manga.UserMangaListStatus;
import com.kttdevelopment.myanimelist.manga.property.MangaSort;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;

import java.util.List;

public abstract class UserMangaListQuery extends SearchQuery<UserMangaListQuery,List<UserMangaListStatus>> {

    protected final String username;
    protected MangaSort   sort;
    protected MangaStatus status;

    public UserMangaListQuery(final MyAnimeListService service, final String username) {
        super(service);
        this.username = username;
    }

    public final UserMangaListQuery sortBy(final MangaSort sort){
        this.sort = sort;
        return this;
    }

    public final UserMangaListQuery withStatus(final MangaStatus status){
        this.status = status;
        return this;
    }

}
