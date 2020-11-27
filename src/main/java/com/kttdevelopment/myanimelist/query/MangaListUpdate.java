package com.kttdevelopment.myanimelist.query;

import com.kttdevelopment.myanimelist.MyAnimeListService;
import com.kttdevelopment.myanimelist.anime.AnimeListStatus;
import com.kttdevelopment.myanimelist.manga.property.MangaStatus;

import java.util.Arrays;
import java.util.List;

public abstract class MangaListUpdate {

    protected final MyAnimeListService service;
    protected final long id;

    protected MangaStatus status;
    protected boolean rereading;
    protected int score, volumesRead, chaptersRead, priority, rereadValue;
    protected List<String> tags;
    protected String comments;

    public MangaListUpdate(final MyAnimeListService service, final long id){
        this.service = service;
        this.id = id;
    }

    public final MangaListUpdate status(final MangaStatus status){
        this.status = status;
        return this;
    }

    public final MangaListUpdate rereading(final boolean rereading){
        this.rereading = rereading;
        return this;
    }

    public final MangaListUpdate score(final int score){
        this.score = score;
        return this;
    }

    public final MangaListUpdate volumesRead(final int volumesRead){
        this.volumesRead = volumesRead;
        return this;
    }

    public final MangaListUpdate chaptersRead(final int chaptersRead){
        this.chaptersRead = chaptersRead;
        return this;
    }

    public final MangaListUpdate priority(final int priority){
        this.priority = priority;
        return this;
    }

    public final MangaListUpdate rereadValue(final int rereadValue){
        this.rereadValue = rereadValue;
        return this;
    }

    public final MangaListUpdate tags(final String... tags){
        this.tags = Arrays.asList(tags);
        return this;
    }

    public final MangaListUpdate setComments(final String comments){
        this.comments = comments;
        return this;
    }

    public abstract AnimeListStatus update();

}
