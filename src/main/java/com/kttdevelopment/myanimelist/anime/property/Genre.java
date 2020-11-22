package com.kttdevelopment.myanimelist.anime.property;
@SuppressWarnings("SpellCheckingInspection")
public enum Genre {

    Adventure       (1, "Adventure"),
    Action          (2, "Action"),
    Cars            (3, "Cars"),
    Comedy          (4, "Comedy"),
    Dementia        (5, "Dementia"),
    Demons          (6, "Demons"),
    Drama           (7, "Drama"),
    Ecchi           (8, "Ecchi"),
    Fantasy         (9, "Fantasy"),
    Game            (10, "Game"),
    Harem           (11, "Harem"),
    Historical      (12, "Historical"),
    Horror          (13, "Horror"),
    Josei           (14, "Josei"),
    Kids            (15, "Kids"),
    Magic           (16, "Magic"),
    MartialArts     (17, "Martial Arts"),
    Mecha           (18, "Mecha"),
    Military        (19, "Military"),
    Music           (20, "Music"),
    Mystery         (21, "Mystery"),
    Parody          (22, "Parody"),
    Police          (23, "Police"),
    Psychological   (24, "Psychological"),
    Romance         (25, "Romance"),
    Samurai         (26, "Samurai"),
    School          (27, "School"),
    SciFi           (28, "Sci-Fi"),
    Seinen          (29, "Seinen"),
    Shoujo          (30, "Shoujo"),
    ShoujoAi        (31, "Shoujo Ai"),
    Shounen         (32, "Shounen"),
    ShounenAi       (33, "Shounen Ai"),
    SliceOfLife     (34, "Slice of Life"),
    Space           (35, "Space"),
    Sports          (36, "Sports"),
    SuperPower      (37, "Super Power"),
    Supernatural    (38, "Supernatural"),
    Thriller        (39, "Thriller"),
    Vampire         (40, "Vampire");

    private final int id;
    private final String name;

    Genre(final int id, final String name){
        this.id   = id;
        this.name = name;
    }

    public final int getId(){
        return id;
    }

    public final String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Genre{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}