package com.kttdevelopment.myanimelist.anime.property;

public enum AnimeStatus {

    Watching("watching"),
    Completed("completed"),
    OnHold("on_hold"),
    Dropped("dropped"),
    PlanToWatch("plan_to_watch");

    private final String status;

    AnimeStatus(final String status){
        this.status = status;
    }

    public final String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return "AnimeStatus{" +
               "status='" + status + '\'' +
               '}';
    }

}
