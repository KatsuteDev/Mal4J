package com.kttdevelopment.myanimelist.manga.property;

import com.kttdevelopment.myanimelist.property.RankingType;

/**
 * Represents Manga ranking types.
 *
 * @see MangaType
 * @see RankingType
 * @since 1.0.0
 * @version 1.0.0
 * @author Ktt Development
 */
@SuppressWarnings("SpellCheckingInspection")
public enum MangaRankingType {


    Manga   (MangaType.Manga.field()),
    Novels  (MangaType.Novels.field()),
    OneShots(MangaType.OneShots.field()),
    Doujin  (MangaType.Doujin.field()),
    Manhwa  (MangaType.Manhwa.field()),
    Manhua  (MangaType.Manhua.field()),

    All         (RankingType.All.field()),
    ByPopularity(RankingType.ByPopularity.field()),
    Favorite    (RankingType.Favorite.field());

    private final String field;

    MangaRankingType(final String field){
        this.field = field;
    }

    /**
     * Returns the field name.
     *
     * @return field name
     *
     * @since 1.0.0
     */
    public final String field(){
        return field;
    }

    /**
     * Returns the field as an enum.
     *
     * @param string string
     * @return enum
     *
     * @since 1.0.0
     */
    public static MangaRankingType asEnum(final String string){
        for(final MangaRankingType value : values())
            if(value.field.equalsIgnoreCase(string))
                return value;
        return null;
    }

    @Override
    public String toString(){
        return "MangaRankingType{" +
               "field='" + field + '\'' +
               '}';
    }
}
