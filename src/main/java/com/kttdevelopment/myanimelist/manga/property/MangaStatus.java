package com.kttdevelopment.myanimelist.manga.property;

/**
 * Represents a Manga status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
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

    /**
     * Returns the status field name
     *
     * @return field name
     *
     * @since 1.0.0
     */
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
