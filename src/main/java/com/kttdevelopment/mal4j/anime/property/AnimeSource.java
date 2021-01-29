/*
 * Copyright (C) 2021 Ktt Development
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

package com.kttdevelopment.mal4j.anime.property;

import com.kttdevelopment.mal4j.anime.AnimePreview;

/**
 * Represents the source material for an Anime.
 *
 * @see AnimePreview#getSource()
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public enum AnimeSource {

    Other           ("other"),
    Original        ("original"),
    Manga           ("manga"),
    FourKomaManga   ("4_koma_manga"),
    Web_Manga       ("web_manga"),
    Digital_Manga   ("digital_manga"),
    Novel           ("novel"),
    LightNovel      ("light_novel"),
    VisualNovel     ("visual_nodel"),
    Game            ("game"),
    CardGame        ("card_game"),
    Book            ("book"),
    PictureBook     ("picture_book"),
    Radio           ("radio"),
    Music           ("music");

    private final String field;

    AnimeSource(String field) {
        this.field = field;
    }

    /**
     * Returns the json field name.
     *
     * @return json field name
     *
     * @since 1.0.0
     */
    public final String field(){
        return field;
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
    public static AnimeSource asEnum(final String string){
        for(final AnimeSource value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public final String toString(){
        return name();
    }

}
