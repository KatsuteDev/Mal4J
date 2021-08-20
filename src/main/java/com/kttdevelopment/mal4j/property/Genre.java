/*
 * Copyright (C) 2021 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.kttdevelopment.mal4j.property;

/**
 * Represents a Genre. Genre IDs are different for Anime and Manga.
 *
 * @since 1.0.0
 * @version 2.1.0
 * @author Katsute
 */
@SuppressWarnings("SpellCheckingInspection")
public enum Genre {

    Adventure       (2  , 2  , "Adventure"),
    Action          (1  , 1  , "Action"),
    Cars            (3  , 3  , "Cars"),
    Comedy          (4  , 4  , "Comedy"),
    Dementia        (5  , 5  , "Dementia"),
    Demons          (6  , 6  , "Demons"),
    Drama           (8  , 8  , "Drama"),
    Doujinshi       (-1 , 43 , "Doujinshi"),
    Ecchi           (9  , 9  , "Ecchi"),
    Fantasy         (10 , 10 , "Fantasy"),
    Game            (11 , 11 , "Game"),
    GenderBender    (-1 , 44 , "Gender Bender"),
    Harem           (35 , 35 , "Harem"),
    Hentai          (12 , 12 , "Hentai"),
    Historical      (13 , 13 , "Historical"),
    Horror          (14 , 14 , "Horror"),
    Josei           (43 , 42 , "Josei"),
    Kids            (15 , 15 , "Kids"),
    Magic           (16 , 16 , "Magic"),
    MartialArts     (17 , 17 , "Martial Arts"),
    Mecha           (18 , 18 , "Mecha"),
    Military        (38 , 38 , "Military"),
    Music           (19 , 19 , "Music"),
    Mystery         (7  , 7  , "Mystery"),
    Parody          (20 , 20 , "Parody"),
    Police          (39 , 39 , "Police"),
    Psychological   (40 , 40 , "Psychological"),
    Romance         (22 , 22 , "Romance"),
    Samurai         (21 , 21 , "Samurai"),
    School          (23 , 23 , "School"),
    SciFi           (24 , 24 , "Sci-Fi"),
    Seinen          (42 , 41 , "Seinen"),
    Shoujo          (25 , 25 , "Shoujo"),
    ShoujoAi        (26 , 26 , "Shoujo Ai"),
    Shounen         (27 , 27 , "Shounen"),
    ShounenAi       (28 , 28 , "Shounen Ai"),
    SliceOfLife     (36 , 36 , "Slice of Life"),
    Space           (29 , 29 , "Space"),
    Sports          (30 , 30 , "Sports"),
    SuperPower      (31 , 31 , "Super Power"),
    Supernatural    (37 , 37 , "Supernatural"),
    Thriller        (41 , 45 , "Thriller"),
    Vampire         (32 , 32 , "Vampire"),
    Yaoi            (33 , 33 , "Yaoi"),
    Yuri            (34 , 34 , "Yuri");

    private final int animeGenreID, mangaGenreID;
    private final String name;

    Genre(final int animeGenreID, final int mangaId, final String name){
        this.animeGenreID = animeGenreID;
        this.mangaGenreID = mangaId;
        this.name    = name;
    }

    /**
     * @deprecated Use {@link #getAnimeGenreID()} or {@link #getMangaGenreID()}
     * @return id
     */
    @Deprecated
    public final int getId(){
        return animeGenreID;
    }

    /**
     * Returns the genre ID for Anime.
     *
     * @throws UnsupportedOperationException if there is no ID associated with this genre for Anime
     * @return Anime genre ID
     *
     * @since 2.1.0
     */
    public final int getAnimeGenreID(){
        if(animeGenreID == -1)
            throw new UnsupportedOperationException("There is no Anime genre ID for this genre. Try getMangaGenreID()");
        return animeGenreID;
    }

    /**
     * Returns the genre ID for Manga.
     *
     * @throws UnsupportedOperationException if there is no ID associated with this genre for Manga
     * @return Manga genre ID
     *
     * @since 2.1.0
     */
    public final int getMangaGenreID(){
        if(mangaGenreID == -1)
            throw new UnsupportedOperationException("There is no Manga genre ID for this genre. Try getAnimeGenreID()");
        return mangaGenreID;
    }

    public final String getName(){
        return name;
    }

    /**
     * @deprecated Use {@link #asAnimeGenre(int)} or {@link #asMangaGenre(int)}
     * @param id id
     * @return genre
     */
    @Deprecated
    public static Genre asEnum(final int id){
        for(final Genre value : values())
            if(value.animeGenreID == id)
                return value;
        return null;
    }

    /**
     * Returns the Anime genre associated with the ID.
     *
     * @param id genre ID
     * @return genre enum
     *
     * @since 2.1.0
     */
    public static Genre asAnimeGenre(final int id){
        for(final Genre value : values())
            if(value.animeGenreID == id)
                return value;
        return null;
    }

    /**
     * Returns the Manga genre associated with the ID.
     *
     * @param id genre ID
     * @return genre enum
     *
     * @since 2.1.0
     */
    public static Genre asMangaGenre(final int id){
        for(final Genre value : values())
            if(value.mangaGenreID == id)
                return value;
        return null;
    }

    /**
     * Returns the field name as an enum.
     *
     * @param string json field name
     *
     * @return enum
     *
     * @since 1.0.0
     */
    public static Genre asEnum(final String string){
        for(final Genre value : values())
            if(value.name.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}