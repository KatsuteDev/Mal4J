package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.UserAnimeListStatus;
import com.kttdevelopment.myanimelist.anime.property.*;

import java.util.List;

public abstract class UserAnimeListQuery extends SearchQuery<UserAnimeListQuery, List<UserAnimeListStatus>> {

    protected final String username;
    protected AnimeSort sort;
    protected AnimeStatus status;

    public UserAnimeListQuery(final MyAnimeListService service, final String username) {
        super(service);
        this.username = username;
    }

    public final UserAnimeListQuery sortBy(final AnimeSort sort){
        this.sort = sort;
        return this;
    }

    public final UserAnimeListQuery withStatus(final AnimeStatus status){
        this.status = status;
        return this;
    }

}
