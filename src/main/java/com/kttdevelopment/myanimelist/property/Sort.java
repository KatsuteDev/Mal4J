package com.kttdevelopment.myanimelist.property;

/**
 * Represents the sort order.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum Sort {

    Score("list_score"),
    UpdatedAt("updated_at");

    private final String sort;

    Sort(final String sort){
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
        return "Sort{" +
               "sort='" + sort + '\'' +
               '}';
    }

}
