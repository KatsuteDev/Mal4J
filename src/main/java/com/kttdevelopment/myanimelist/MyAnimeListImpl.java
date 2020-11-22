package com.kttdevelopment.myanimelist;

public class MyAnimeListImpl /*extends MyAnimeList*/{

    private transient final String auth;

    private final MyAnimeListService service = MyAnimeListService.create();

    public MyAnimeListImpl(final String auth){
        this.auth = auth;
    }


}
