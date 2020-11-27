package com.kttdevelopment.myanimelist.anime.property;

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

    public final String field(){
        return field;
    }

    public static AnimeSource asEnum(final String string){
        for(final AnimeSource value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString() {
        return "AnimeSource{" +
                "field='" + field + '\'' +
                '}';
    }
}
