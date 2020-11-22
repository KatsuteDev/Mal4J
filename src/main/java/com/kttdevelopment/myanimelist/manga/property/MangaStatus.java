package com.kttdevelopment.myanimelist.manga.property;

public enum MangaStatus {

    Reading("reading"),
    Completed("completed"),
    OnHold("on_hold"),
    Dropped("dropped"),
    PlanToRead("plan_to_read");

    private final String status;

    MangaStatus(final String status){
        this.status = status;
    }

    public final String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return "MangaStatus{" +
               "status='" + status + '\'' +
               '}';
    }

}
