package com.kttdevelopment.myanimelist.property;

/**
 * Represents the sort order.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum MangaSort {

    Score(Sort.Score.getSort()),
    UpdatedAt(Sort.UpdatedAt.getSort()),

    Title("manga_title"),
    StartDate("manga_start_date"),
    ID("manga_id");

    private final String sort;

    MangaSort(final String sort){
        this.sort = sort;
    }

    /**
     * Returns the type field name
     *
     * @return field name
     *
     * @since 1.0.0
     */
    public final String getSort(){
        return sort;
    }

    @Override
    public String toString(){
        return "MangaSort{" +
               "sort='" + sort + '\'' +
               '}';
    }

}
