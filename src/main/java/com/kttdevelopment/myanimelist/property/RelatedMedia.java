package com.kttdevelopment.myanimelist.property;

import com.kttdevelopment.myanimelist.anime.RelatedAnime;
import com.kttdevelopment.myanimelist.manga.RelatedManga;

public abstract class RelatedMedia {

    public abstract RelationType getRelationType();

    public abstract String getRelationTypeFormat();

}
