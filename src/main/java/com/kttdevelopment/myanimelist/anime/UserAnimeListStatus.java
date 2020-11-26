package com.kttdevelopment.myanimelist.anime;

import com.kttdevelopment.myanimelist.anime.property.AnimePreviewRetrievable;
import com.kttdevelopment.myanimelist.anime.property.AnimeStatus;
import com.kttdevelopment.myanimelist.property.UserListStatus;

public abstract class UserAnimeListStatus extends AnimeListStatus implements UserListStatus<AnimeStatus>, AnimeRetrievable, AnimePreviewRetrievable {

}
