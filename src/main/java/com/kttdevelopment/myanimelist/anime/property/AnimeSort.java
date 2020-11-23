package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.property.Sort;

/**
 * Represents the sort order.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeSort {

    Score(Sort.Score.getSort()),
    UpdatedAt(Sort.UpdatedAt.getSort()),

    Title("anime_title"),
    StartDate("anime_start_date"),
    ID("anime_id");

    private final String sort;

    AnimeSort(final String sort){
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
        return "AnimeSort{" +
               "sort='" + sort + '\'' +
               '}';
    }

}
