package com.kttdevelopment.myanimelist.manga.property;

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
