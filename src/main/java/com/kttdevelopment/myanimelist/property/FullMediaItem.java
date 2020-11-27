package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.RelatedAnime;
import com.kttdevelopment.myanimelist.manga.RelatedManga;

public interface FullMediaItem<MediaType extends Enum<?>,Status extends Enum<?>,ListStatus extends com.kttdevelopment.myanimelist.property.ListStatus<?>,Recommendation extends com.kttdevelopment.myanimelist.property.Recommendation,Statistics extends com.kttdevelopment.myanimelist.property.Statistics> extends MediaItem<MediaType,Status,ListStatus> {

    Picture[] getPictures();

    String getBackground();

    RelatedAnime[] getRelatedAnime();

    RelatedManga[] getRelatedManga();

    Recommendation[] getRecommendations();

    Statistics getStatistics();

}
