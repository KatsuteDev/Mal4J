package com.kttdevelopment.myanimelist.anime.property;

/**
 * Represents an Anime air status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeAirStatus {

    Airing("airing"),
    NotYetAired("not_yet_aired"),
    Finished("finished_airing");

    private final String status;

    AnimeAirStatus(final String status){
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
        return "AnimeStatus{" +
               "status='" + status + '\'' +
               '}';
    }

}
