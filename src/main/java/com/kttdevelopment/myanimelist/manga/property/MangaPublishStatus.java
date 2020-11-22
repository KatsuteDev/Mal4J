package com.kttdevelopment.myanimelist.manga.property;

/**
 * Represents a Manga publish status.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum MangaPublishStatus {

    Publishing("publishing"),
    Published("not_yet_published"),
    Finished("finished_publishing");

    private final String status;

    MangaPublishStatus(final String status){
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
