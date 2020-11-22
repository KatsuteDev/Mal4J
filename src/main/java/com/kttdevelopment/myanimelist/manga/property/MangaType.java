package com.kttdevelopment.myanimelist.manga.property;

/**
 * Represents Manga types.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public enum MangaType {

    Manga("manga"),
    Novels("novels"),
    OneShots("oneshots"),
    Doujin("doujin"),
    Manhwa("manhwa"),
    Manhua("manhua");

    private final String type;

    MangaType(final String type){
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
        return "MangaType{" +
               "type='" + type + '\'' +
               '}';
    }
}
