package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimeListStatus;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;

import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
public abstract class AnimeListUpdate {

    protected final MyAnimeListService service;
    protected final long id;

    protected AnimeStatus status;
    protected boolean rewatching;
    protected int score, watchedEpisodes, priority, timesRewatched, rewatchValue;
    protected List<String> tags;
    protected String comments;

    public AnimeListUpdate(final MyAnimeListService service, final long id){
        this.service = service;
        this.id = id;
    }

    public final AnimeListUpdate status(final AnimeStatus status){
        this.status = status;
        return this;
    }

    public final AnimeListUpdate rewatching(final boolean rewatching){
        this.rewatching = rewatching;
        return this;
    }

    public final AnimeListUpdate score(final int score){
        this.score = score;
        return this;
    }

    public final AnimeListUpdate episodesWatched(final int watchedEpisodes){
        this.watchedEpisodes = watchedEpisodes;
        return this;
    }

    public final AnimeListUpdate priority(final int priority){
        this.priority = priority;
        return this;
    }

    public final AnimeListUpdate timesRewatched(final int timesRewatched){
        this.timesRewatched = timesRewatched;
        return this;
    }

    public final AnimeListUpdate rewatchValue(final int rewatchValue){
        this.rewatchValue = rewatchValue;
        return this;
    }

    public final AnimeListUpdate tags(final String... tags){
        this.tags = Arrays.asList(tags);
        return this;
    }

    public final AnimeListUpdate setComments(final String comments){
        this.comments = comments;
        return this;
    }

    public abstract AnimeListStatus update();

}
