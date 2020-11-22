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


    Manga(MangaType.Manga.getType()),
    Novels(MangaType.Novels.getType()),
    OneShots(MangaType.OneShots.getType()),
    Doujin(MangaType.Doujin.getType()),
    Manhwa(MangaType.Manhwa.getType()),
    Manhua(MangaType.Manhua.getType()),

    All(RankingType.All.getType()),
    ByPopularity(RankingType.ByPopularity.getType()),
    Favorite(RankingType.Favorite.getType());

    private final String type;

    MangaRankingType(final String type){
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
        return "AnimeRankingType{" +
               "type='" + type + '\'' +
               '}';
    }
}
