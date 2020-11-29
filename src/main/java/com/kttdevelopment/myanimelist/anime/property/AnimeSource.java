package com.kttdevelopment.myanimelist.anime.property;

import com.kttdevelopment.myanimelist.anime.AnimePreview;

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
