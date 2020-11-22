package com.kttdevelopment.myanimelist.property;

/**
 * Represents general ranking types.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum RankingType {

    All("all"),
    @SuppressWarnings("SpellCheckingInspection")
    ByPopularity("bypopularity"),
    Favorite("favorite");

    private final String type;

    RankingType(final String type){
        this.type = type;
    }

    /**
     * Returns the type field name
     *
     * @return field name
     *
     * @since 1.0.0
     */
    public final String getType(){
        return type;
    }

    @Override
    public String toString(){
        return "RankingType{" +
               "type='" + type + '\'' +
               '}';
    }
}
