package com.kttdevelopment.myanimelist.anime.property;

/**
 * Represents Anime types.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
public enum AnimeType {

    TV("tv"),
    OVA("ova"),
    Movie("movie"),
    Special("special");

    private final String type;

    AnimeType(final String type){
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
        return "AnimeType{" +
               "type='" + type + '\'' +
               '}';
    }

}
